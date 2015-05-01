package davenkin.bookrank.report;

import davenkin.bookrank.model.RankBook;
import org.apache.log4j.Logger;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static davenkin.bookrank.model.Merchant.AMAZON;

/**
 * Created by twer on 3/30/14.
 */
public class AmazonSelfBookRankGenerator extends AbstractBookRankGenerator {
    private static final Logger logger = Logger.getLogger(AmazonSelfBookRankGenerator.class);
    public static final String AMAZON_SELF_BOOK = "亚马逊图书本页";

    public boolean canHandle(RankBook rankBook) {
        return (AMAZON.equals(rankBook.merchant()) && AMAZON_SELF_BOOK.equals(rankBook.category()));
    }


    protected AmazonSelfBookRankGenerator() {
        super(AMAZON, "navFooter", "//span[@class='zg_hrsr_rank']", 1);
    }

    public int rank(RankBook rankBook) {
        logger.info("Trying to find rank for " + rankBook + " on page :" + rankBook.getBookPageUrl());

        HtmlUnitDriver driver = new HtmlUnitDriver();
        try {
            driver.get(rankBook.getBookPageUrl());
            waitPageLoad(driver);

            int rank = Integer.parseInt(driver.findElementByXPath(searchXpath).getText().replaceAll("\\D+", ""));
            if (rankFound(rank)) {
                logger.info("Found rank for " + rankBook + " on page " + rankBook.getBookPageUrl() + ":rank" + rank);
                return rank;
            }
        } catch (Exception e) {
            logger.error(String.format("Exception found when finding rank for %s on %s-%s-[%s]:\n%s", rankBook.getName(), rankBook.merchant(), rankBook.category(), rankBook.getBookPageUrl(), e.getMessage()));
        } finally {
            driver.quit();
        }
        return NO_RANK;
    }

    @Override
    protected int tryFindRankOnCurrentPage(int pageIndex, HtmlUnitDriver driver, RankBook rankBook) {
        throw new RuntimeException("Not supported operation.");
    }
}


