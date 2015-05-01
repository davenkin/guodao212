package davenkin.bookrank.report;

import davenkin.bookrank.model.RankBook;
import davenkin.bookrank.repository.RankRecordRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static davenkin.bookrank.report.AbstractBookRankGenerator.NO_RANK;

/**
 * Created by twer on 4/29/14.
 */
public class DatabaseBookRankerService implements BookRankService {
    private List<BookRankGenerator> bookRankGenerators;
    private RankRecordRepository rankRecordRepository;

    @Transactional
    public void reportBook(RankBook rankBook) {
        for (BookRankGenerator bookRankGenerator : bookRankGenerators) {
            if (bookRankGenerator.canHandle(rankBook)) {
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
