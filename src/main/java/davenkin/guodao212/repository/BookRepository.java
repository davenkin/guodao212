package davenkin.guodao212.repository;

import davenkin.guodao212.model.RankBook;

import java.util.List;

/**
 * Created by twer on 5/1/14.
 */
public interface BookRepository {
    public List<RankBook> allBooks();
}
