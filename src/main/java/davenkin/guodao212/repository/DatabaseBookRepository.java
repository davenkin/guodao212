package davenkin.guodao212.repository;

import davenkin.guodao212.model.RankBook;
import davenkin.guodao212.jdbc.RankBookRowMapper;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by twer on 5/1/14.
 */
public class DatabaseBookRepository implements BookRepository {
    private final Logger logger = Logger.getLogger(this.getClass());

    public static final String SELECT_SQL = "SELECT BOOK.BOOK_ID,BOOK.BOOK_NAME,BOOK.BOOK_PAGE_URL,BOOK.CATEGORY_ID,CATEGORY.MERCHANT,CATEGORY.CATEGORY_NAME,CATEGORY.DESCRIPTION,CATEGORY.URL_TEMPLATE,CATEGORY.MAX_NUMBER FROM RANK_BOOK BOOK,CATEGORY CATEGORY WHERE BOOK.CATEGORY_ID=CATEGORY.CATEGORY_ID;";
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RankBook> allBooks() {
        List<RankBook> rankBooks = jdbcTemplate.query(SELECT_SQL, new RankBookRowMapper());
        logger.info("=============Found "+rankBooks.size()+" books to report=========");
        return rankBooks;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
