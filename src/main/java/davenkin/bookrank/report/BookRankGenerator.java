package davenkin.bookrank.report;

import davenkin.bookrank.model.RankBook;

/**
 * Created by twer on 3/30/14.
 */
public interface BookRankGenerator {
    public int rank(RankBook rankBook);

    public boolean canHandle(RankBook rankBook);
}
