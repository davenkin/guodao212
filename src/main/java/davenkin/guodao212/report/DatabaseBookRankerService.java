package davenkin.guodao212.report;

import davenkin.guodao212.model.RankBook;
import davenkin.guodao212.repository.RankRecordRepository;
import org.apache.log4j.Logger;
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
                int rank = bookRankGenerator.rank(rankBook);
                if (rankFound(rank)) {
                    rankRecordRepository.addRecord(rankBook, rank);
                }
                break;
            }
        }
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
