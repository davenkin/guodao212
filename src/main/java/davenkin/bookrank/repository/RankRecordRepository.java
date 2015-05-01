package davenkin.bookrank.repository;

import davenkin.bookrank.model.BookRankRecord;
import davenkin.bookrank.model.RankBook;

import java.util.List;

/**
 * Created by Davenkin on 5/1/14.
 */
public interface RankRecordRepository {

    public List<BookRankRecord> allRecords();

    public List<BookRankRecord> recordsForCategory(String category);

    public List<BookRankRecord> recordsForName(String bookId);

    public List<BookRankRecord> recordsForBookAndCategory(String name, String category);

    public void addRecord(RankBook rankBook, int rank);

}
