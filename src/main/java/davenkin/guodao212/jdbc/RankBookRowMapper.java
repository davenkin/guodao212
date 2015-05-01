package davenkin.guodao212.jdbc;

import davenkin.guodao212.model.Category;
import davenkin.guodao212.model.RankBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by twer on 5/1/14.
 */
public class RankBookRowMapper implements RowMapper<RankBook> {
    @Override
    public RankBook mapRow(ResultSet rs, int rowNum) throws SQLException {
        int bookId = rs.getInt("BOOK_ID");
        String bookName = rs.getString("BOOK_NAME");
        String bookPageUrl = rs.getString("BOOK_PAGE_URL");
        int categoryId = rs.getInt("CATEGORY_ID");
        String merchant = rs.getString("MERCHANT");
        String categoryName = rs.getString("CATEGORY_NAME");
        String description = rs.getString("DESCRIPTION");
        String urlTemplate = rs.getString("URL_TEMPLATE");
        int maxNumber = rs.getInt("MAX_NUMBER");
        Category category = new Category(categoryId, merchant, categoryName, description, urlTemplate, maxNumber);
        return new RankBook(bookId, bookName, bookPageUrl, category);
    }
}
