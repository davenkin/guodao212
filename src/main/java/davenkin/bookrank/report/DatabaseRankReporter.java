package davenkin.bookrank.report;

import davenkin.bookrank.model.RankBook;
import davenkin.bookrank.repository.BookRepository;
import org.apache.log4j.Logger;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.List;

/**
 * Created by twer on 4/29/14.
 */
public class DatabaseRankReporter implements BookRankReporter {
    private BookRepository bookRepository;
    private BookRankService bookRankerService;
    private static Logger logger = Logger.getLogger(DatabaseRankReporter.class);

    @Override
    public void report() {
        logger.info("Reporting job start running...");
        try {
            for (RankBook rankBook : getAllBooks()) {
                bookRankerService.reportBook(rankBook);
            }
        } catch (Exception e) {
            logger.error("Error while reporting book rankings: " + e.getMessage());
        }

        logger.info("Reporting job ends.");

    }

    private List<RankBook> getAllBooks() throws Exception {
        return retryTemplate().execute(new RetryCallback<List<RankBook>>() {
            @Override
            public List<RankBook> doWithRetry(RetryContext context) throws Exception {
                return bookRepository.allBooks();
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

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void setBookRankerService(BookRankService bookRankerService) {
        this.bookRankerService = bookRankerService;
    }
}
