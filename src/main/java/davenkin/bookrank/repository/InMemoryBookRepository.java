package davenkin.bookrank.repository;

import davenkin.bookrank.model.RankBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twer on 4/29/14.
 */
public class InMemoryBookRepository implements BookRepository {
    public List<RankBook> allBooks() {
        ArrayList<RankBook> rankBooks = new ArrayList<RankBook>();
//        RankBook rankBook = new RankBook("IDDD", JingDong, "Jingdong", "http://list.jd.com/1713-3287-3287-0-0-0-0-0-0-0-1-1-%s.html", "http://item.jd.com/11423256.html");
//        rankBook.setBookId(12);
//        rankBooks.add(rankBook);
//        RankBook rankBook1 = new RankBook("IDDD", DangDang, "Dangdang", "http://category.dangdang.com/pg%s-cp01.54.00.00.00.00-srsort_sale_amt_desc.html", "http://product.dangdang.com/23439300.html");
//        rankBook1.setBookId(13);
//        rankBooks.add(rankBook1);
        return rankBooks;
    }
}
