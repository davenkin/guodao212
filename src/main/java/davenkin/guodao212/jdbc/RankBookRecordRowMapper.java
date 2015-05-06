package davenkin.guodao212.jdbc;

import davenkin.guodao212.model.BookRankRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Davenkin on 5/1/14.
 */
public class RankBookRecordRowMapper implements RowMapper<BookRankRecord> {
    @Override
    public BookRankRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        String bookName = rs.getString("BOOK_NAME");
        String merchant = rs.getString("MERCHANT");
        String categoryName = rs.getString("CATEGORY_NAME");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());
        Timestamp rankDate = rs.getTimestamp("RANK_DATE", cal);
        int rank = rs.getInt("RANK");
        return new BookRankRecord(bookName, merchant, categoryName, rankDate, rank);
    }
}
