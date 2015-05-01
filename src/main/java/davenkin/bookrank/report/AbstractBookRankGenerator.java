package davenkin.bookrank.report;

import davenkin.bookrank.model.RankBook;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import static davenkin.bookrank.report.LastPageIndexCache.cachePageIndex;
import static davenkin.bookrank.report.LastPageIndexCache.pageIndexFromCache;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by twer on 3/30/14.
 */
@Component
public abstract class AbstractBookRankGenerator implements BookRankGenerator {
    public static final int NO_RANK = -1;
    private static final int DEFAULT_TIME_OUT = 30;
    protected final String merchant;
    protected final String pageReadyElementId;
    protected final String searchXpath;
    protected final int bookNumberPerPage;
    private final Logger logger = Logger.getLogger(this.getClass());

    protected AbstractBookRankGenerator(String merchant, String pageReadyElementId, String searchXpath, int bookPerPage) {
        this.merchant = merchant;
        this.pageReadyElementId = pageReadyElementId;
        this.searchXpath = searchXpath;
        this.bookNumberPerPage = bookPerPage;
    }

    @Override
    public int rank(RankBook rankBook) {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        try {
            int maxPageNumber = maxPageNumber(rankBook);

            for (int currentCountIndex = 1; currentCountIndex <= maxPageNumber; currentCountIndex++) {
                String currentUrl = null;
                try {
                    int currentPageIndex = pageIndexFromCache(currentCountIndex, rankBook.getBookId(), maxPageNumber);
                    currentUrl = urlForPage(rankBook.urlTemplate(), currentPageIndex);
                    logger.info("Trying to find rank for " + rankBook + " on page " + currentPageIndex + ":" + currentUrl);
                    int rank = rankOnCurrentPage(rankBook, currentPageIndex, currentUrl, driver);
                    if (rankFound(rank)) {
                        cachePageIndex(rankBook.getBookId(), currentPageIndex);
                        logger.info("Found rank for " + rankBook + " on page " + currentPageIndex + ":rank=" + rank);
                        return rank;
                    }
                } catch (Exception e) {
                    logger.error(String.format("Exception found when finding rank for %s on %s-%s-[%s]:\n%s", rankBook.getName(), rankBook.merchant(), rankBook.category(), currentUrl, e.getMessage()));
                }
            }
        } finally {
            driver.quit();
        }

        logger.info("Could not Find rank for " + rankBook);

        return NO_RANK;
    }

    private int rankOnCurrentPage(RankBook rankBook, int currentPageIndex, String currentUrl, HtmlUnitDriver driver) {
        driver.get(currentUrl);
        waitPageLoad(driver);

        return tryFindRankOnCurrentPage(currentPageIndex, driver, rankBook);
    }

    private int maxPageNumber(RankBook rankBook) {
        return (rankBook.maxCount() - 1) / bookNumberPerPage + 1;
    }

    @Override
    public boolean canHandle(RankBook rankBook) {
        return merchant.equals(rankBook.merchant().trim());
    }


    protected WebElement waitPageLoad(HtmlUnitDriver driver) {
        return new WebDriverWait(driver, DEFAULT_TIME_OUT).until(presenceOfElementLocated(id(pageReadyElementId)));
    }

    protected boolean rankFound(int count) {
        return NO_RANK != count;
    }


    private String urlForPage(String urlTemplate, int pageIndex) {
        return String.format(urlTemplate, pageIndex);
    }

    protected abstract int tryFindRankOnCurrentPage(int pageIndex, HtmlUnitDriver driver, RankBook rankBook);
}
