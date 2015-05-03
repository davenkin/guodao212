package davenkin.guodao212.report;

import davenkin.guodao212.model.RankBook;
import davenkin.guodao212.repository.RankRecordRepository;
import org.apache.log4j.Logger;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static davenkin.guodao212.report.AbstractBookRankGenerator.NO_RANK;

/**
 * Created by twer on 4/29/14.
 */
public class DatabaseBookRankerService implements BookRankService {
    private static Logger logger = Logger.getLogger(DatabaseBookRankerService.class);

    private List<BookRankGenerator> bookRankGenerators;
    private RankRecordRepository rankRecordRepository;

    @Transactional
    public void reportBook(RankBook rankBook) {
        for (BookRankGenerator bookRankGenerator : bookRankGenerators) {
            if (bookRankGenerator.canHandle(rankBook)) {
                logger.info("Report rank for book " + rankBook.getName() + " using ranker " + bookRankGenerator.getClass().getSimpleName());
                try {
                    int rank = tryFindRank(bookRankGenerator, rankBook);
                    if (rankFound(rank)) {
                        rankRecordRepository.addRecord(rankBook, rank);
                    }
                } catch (Exception e) {
                    logger.error(String.format("Exception occured when finding rank for %s on %s-%s-[%s]:\n%s", rankBook.getName(), rankBook.merchant(), rankBook.category(), rankBook.getBookPageUrl(), e.getMessage()));
                }
                break;
            }
        }
    }


    private int tryFindRank(final BookRankGenerator bookRankGenerator, final RankBook rankBook) throws Exception {
        return retryTemplate().execute(new RetryCallback<Integer>() {
            @Override
            public Integer doWithRetry(RetryContext context) throws Exception {
                return bookRankGenerator.rank(rankBook);
            }
        });
    }

    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy());
        ExponentialBackOffPolicy simpleRetryPolicy = new ExponentialBackOffPolicy();
        simpleRetryPolicy.setInitialInterval(5000l);
        retryTemplate.setBackOffPolicy(simpleRetryPolicy);
        return retryTemplate;
    }


    private boolean rankFound(int rank) {
        return NO_RANK != rank;

    }

    public void setBookRankGenerators(List<BookRankGenerator> bookRankGenerators) {
        this.bookRankGenerators = bookRankGenerators;
    }

    public void setRankRecordRepository(RankRecordRepository rankRecordRepository) {
        this.rankRecordRepository = rankRecordRepository;
    }
}
