package davenkin.bookrank.repository;

import davenkin.bookrank.jdbc.RankBookRecordRowMapper;
import davenkin.bookrank.model.BookRankRecord;
import davenkin.bookrank.model.RankBook;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by Davenkin on 5/1/14.
 */
public class DefaultRankRecordRepository implements RankRecordRepository {

    private static final String SELECT_SQL_All = "SELECT BOOK.BOOK_NAME, CATEGORY.MERCHANT,CATEGORY.CATEGORY_NAME,RANK.RANK_DATE,RANK FROM RANK_BOOK BOOK, CATEGORY CATEGORY,RANK_HISTORY RANK WHERE RANK.BOOK_ID=BOOK.BOOK_ID AND BOOK.CATEGORY_ID=CATEGORY.CATEGORY_ID ORDER BY BOOK.BOOK_NAME,CATEGORY.CATEGORY_NAME,RANK.RANK_DATE DESC";
    private static final String SELECT_SQL_NAME = "SELECT BOOK.BOOK_NAME, CATEGORY.MERCHANT,CATEGORY.CATEGORY_NAME,RANK.RANK_DATE,RANK FROM RANK_BOOK BOOK, CATEGORY CATEGORY,RANK_HISTORY RANK WHERE BOOK.BOOK_NAME=? AND RANK.BOOK_ID=BOOK.BOOK_ID AND BOOK.CATEGORY_ID=CATEGORY.CATEGORY_ID ORDER BY BOOK.BOOK_NAME,CATEGORY.CATEGORY_NAME,RANK.RANK_DATE DESC";
    private static final String SELECT_SQL_CATEGORY = "SELECT BOOK.BOOK_NAME, CATEGORY.MERCHANT,CATEGORY.CATEGORY_NAME,RANK.RANK_DATE,RANK FROM RANK_BOOK BOOK, CATEGORY CATEGORY,RANK_HISTORY RANK WHERE RANK.BOOK_ID=BOOK.BOOK_ID AND BOOK.CATEGORY_ID=CATEGORY.CATEGORY_ID AND CATEGORY.CATEGORY_NAME=? ORDER BY BOOK.BOOK_NAME,CATEGORY.CATEGORY_NAME,RANK.RANK_DATE DESC;";
    private static final String SELECT_SQL_NAME_CATEGORY = "SELECT BOOK.BOOK_NAME, CATEGORY.MERCHANT,CATEGORY.CATEGORY_NAME,RANK.RANK_DATE,RANK FROM RANK_BOOK BOOK, CATEGORY CATEGORY,RANK_HISTORY RANK WHERE RANK.BOOK_ID=BOOK.BOOK_ID AND BOOK.CATEGORY_ID=CATEGORY.CATEGORY_ID AND BOOK.BOOK_NAME=? AND CATEGORY.CATEGORY_NAME=?  ORDER BY BOOK.BOOK_NAME,CATEGORY.CATEGORY_NAME,RANK.RANK_DATE DESC;";
    public static final String ADD_RANK = "INSERT INTO RANK_HISTORY (BOOK_ID,RANK) VALUES(?,?);";

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BookRankRecord> allRecords() {
        return jdbcTemplate.query(SELECT_SQL_All, new RankBookRecordRowMapper());
    }

    @Override
    public List<BookRankRecord> recordsForName(String name) {
        return jdbcTemplate.query(SELECT_SQL_NAME, new Object[]{name}, new RankBookRecordRowMapper());
    }

    @Override
    public List<BookRankRecord> recordsForCategory(String category) {
        return jdbcTemplate.query(SELECT_SQL_CATEGORY, new Object[]{category}, new RankBookRecordRowMapper());
    }

    @Override
    public List<BookRankRecord> recordsForBookAndCategory(String name, String category) {
        return jdbcTemplate.query(SELECT_SQL_NAME_CATEGORY, new Object[]{name, category}, new RankBookRecordRowMapper());
    }

    @Override
    @CacheEvict(value = "records", allEntries = true)
    public void addRecord(RankBook rankBook, int rank) {
        jdbcTemplate.update(ADD_RANK, rankBook.getBookId(), rank);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
