package davenkin.guodao212.report;

import java.util.HashMap;

/**
 * Created by Davenkin on 5/2/14.
 */
public class LastPageIndexCache {
    private static final HashMap<Integer, Integer> lastPageIndexCache = new HashMap<Integer, Integer>();

    public static Integer cachePageIndex(int bookId, int currentPageIndex) {
        return lastPageIndexCache.put(bookId, currentPageIndex);
    }

    public static int pageIndexFromCache(int currentCountIndex, int bookId, int maxPageNumber) {
        if (!lastPageIndexCache.containsKey(bookId)) {
            return currentCountIndex;
        }

        int baseIndex = lastPageIndexCache.get(bookId);
        int swingRange = Math.min(baseIndex - 1, maxPageNumber - baseIndex) * 2 + 1;
        if (currentCountIndex <= swingRange)
            return calculateCurrentPageIndex(currentCountIndex, currentCountIndex, baseIndex);
        return (baseIndex - maxPageNumber / 2) > 0 ? maxPageNumber - currentCountIndex + 1 : currentCountIndex;

    }

    private static int calculateCurrentPageIndex(int power, int countingIndex, int baseIndex) {
        return (((int) Math.pow(-1, (power + 1))) * countingIndex) / 2 + baseIndex;
    }

}
