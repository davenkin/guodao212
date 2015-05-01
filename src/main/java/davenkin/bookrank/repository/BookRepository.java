package davenkin.bookrank.repository;

import davenkin.bookrank.model.RankBook;

import java.util.List;

/**
 * Created by twer on 5/1/14.
 */
public interface BookRepository {
    public List<RankBook> allBooks();
}
