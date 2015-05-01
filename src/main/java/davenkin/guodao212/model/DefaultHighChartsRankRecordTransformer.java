package davenkin.guodao212.model;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;

import java.util.*;

/**
 * Created by Davenkin on 9/19/14.
 */
public class DefaultHighChartsRankRecordTransformer implements HighChartsRankRecordTransformer {

    @Override
    public List<HighChartsRecord> transform(List<BookRankRecord> records) {
        ImmutableMap<String, Collection<BookRankRecord>> nameGroupedRecords = groupByBookName(records);
        ArrayList<HighChartsRecord> highChartsRecords = new ArrayList<HighChartsRecord>();

        for (Map.Entry<String, Collection<BookRankRecord>> recordsPerName : nameGroupedRecords.entrySet()) {

            String name = recordsPerName.getKey();
            Collection<BookRankRecord> recordsByName = recordsPerName.getValue();
            ImmutableMap<String, Collection<BookRankRecord>> categoryGroupedRecords = groupByCategory(recordsByName);

            for (Map.Entry<String, Collection<BookRankRecord>> recordsPerCategory : categoryGroupedRecords.entrySet()) {

                String category = recordsPerCategory.getKey();
                Collection<BookRankRecord> recordsByCategory = recordsPerCategory.getValue();

                highChartsRecords.add(createSingleHighChartsLine(name, category, recordsByCategory));
            }
        }
        return highChartsRecords;
    }

    private HighChartsRecord createSingleHighChartsLine(String name, String category, Collection<BookRankRecord> recordsByCategory) {
        HighChartsRecord highChartsRecord = new HighChartsRecord();
        List<List<Long>> singleBookRecords = transformSingleHightChartsLine(recordsByCategory);

        highChartsRecord.setData(singleBookRecords);
        highChartsRecord.setName(String.format("%s-%s(%s)", name, category, latestRank(singleBookRecords)));

        return highChartsRecord;
    }

    private Long latestRank(List<List<Long>> data) {
        List<Long> latestPoint = data.get(data.size() - 1);
        return latestPoint.get(latestPoint.size() - 1);
    }

    private String latestRank() {
        return "dsfsd";

    }

    private ImmutableMap<String, Collection<BookRankRecord>> groupByCategory(Collection<BookRankRecord> recordsByName) {
        return Multimaps.index(recordsByName, new Function<BookRankRecord, String>() {
            @Override
            public String apply(BookRankRecord input) {
                return input.getCategory();
            }
        }).asMap();
    }

    private ImmutableMap<String, Collection<BookRankRecord>> groupByBookName(List<BookRankRecord> records) {
        return Multimaps.index(records, new Function<BookRankRecord, String>() {
            @Override
            public String apply(BookRankRecord input) {
                return input.getBookName();
            }
        }).asMap();
    }

    private List<List<Long>> transformSingleHightChartsLine(Collection<BookRankRecord> records) {
        ArrayList<List<Long>> pointsPerLine = new ArrayList<List<Long>>();

        for (BookRankRecord bookRankRecord : records) {
            pointsPerLine.add(createSinglePoint(bookRankRecord));
        }

        oderByTime(pointsPerLine);

        return pointsPerLine;
    }

    private void oderByTime(ArrayList<List<Long>> pointsPerLine) {
        Collections.sort(pointsPerLine, new Ordering<List<Long>>() {
            @Override
            public int compare(List<Long> left, List<Long> right) {
                return Long.compare(left.get(0), right.get(0));
            }
        });
    }

    private ArrayList<Long> createSinglePoint(BookRankRecord bookRankRecord) {
        ArrayList<Long> longs = new ArrayList<Long>();
        longs.add(bookRankRecord.getDate().getTime() + 8 * 3600 * 1000);
        longs.add((long) bookRankRecord.getRank());
        return longs;
    }
}
