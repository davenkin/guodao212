package davenkin.bookrank.model;

import java.util.List;

/**
 * Created by Davenkin on 9/19/14.
 */
public interface HighChartsRankRecordTransformer {
    public List<HighChartsRecord> transform(List<BookRankRecord> records);
}
