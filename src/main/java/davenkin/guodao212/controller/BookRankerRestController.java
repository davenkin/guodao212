package davenkin.guodao212.controller;

import davenkin.guodao212.model.BookRankRecord;
import davenkin.guodao212.model.HighChartsRankRecordTransformer;
import davenkin.guodao212.model.HighChartsRecord;
import davenkin.guodao212.repository.RankRecordRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by twer on 4/27/14.
 */
@Controller
public class BookRankerRestController {
    private static final Logger logger = Logger.getLogger(BookRankerRestController.class);

    @Autowired
    private RankRecordRepository rankRecordRepository;

    @Autowired
    private HighChartsRankRecordTransformer highChartsRankRecordTransformer;

    @RequestMapping(value = "api/ranks", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value = "records")
    public List<HighChartsRecord> bookRanks(@RequestParam(required = false, value = "name") String name,
                                            @RequestParam(required = false, value = "category") String category) {

        return highChartsRankRecordTransformer.transform(getRecords(name, category));
    }

    private List<BookRankRecord> getRecords(final String name, final String category) {
        if (isNullOrEmpty(name) && isNullOrEmpty(category)) {
            return retry(new SearchExcecutor() {
                @Override
                public List<BookRankRecord> execute() {
                    List<BookRankRecord> bookRankRecords = rankRecordRepository.allRecords();
                    return bookRankRecords.subList(bookRankRecords.size() - 200, bookRankRecords.size() - 1);
                }
            });
        }

        if (!isNullOrEmpty(name) && isNullOrEmpty(category)) {
            return retry(new SearchExcecutor() {
                @Override
                public List<BookRankRecord> execute() {
                    return rankRecordRepository.recordsForName(name);
                }
            });
        }


        if (isNullOrEmpty(name) && !isNullOrEmpty(category)) {
            return retry(new SearchExcecutor() {
                @Override
                public List<BookRankRecord> execute() {
                    return rankRecordRepository.recordsForCategory(category);
                }
            });
        }


        return retry(new SearchExcecutor() {
            @Override
            public List<BookRankRecord> execute() {
                return rankRecordRepository.recordsForBookAndCategory(name, category);
            }
        });
    }

    private List<BookRankRecord> retry(final SearchExcecutor executor) {
        try {
            return retryTemplate().execute(new RetryCallback<List<BookRankRecord>>() {
                @Override
                public List<BookRankRecord> doWithRetry(RetryContext context) {
                    return executor.execute();
                }
            });
        } catch (Exception e) {
            logger.error("Exception occured: " + e.getMessage());
        }
        return null;
    }

    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy());
        ExponentialBackOffPolicy simpleRetryPolicy = new ExponentialBackOffPolicy();
        simpleRetryPolicy.setInitialInterval(500l);
        retryTemplate.setBackOffPolicy(simpleRetryPolicy);
        return retryTemplate;
    }


    public void setRankRecordRepository(RankRecordRepository rankRecordRepository) {
        this.rankRecordRepository = rankRecordRepository;
    }

    public void setHighChartsRankRecordTransformer(HighChartsRankRecordTransformer highChartsRankRecordTransformer) {
        this.highChartsRankRecordTransformer = highChartsRankRecordTransformer;
    }

    interface SearchExcecutor {
        List<BookRankRecord> execute();
    }
}
