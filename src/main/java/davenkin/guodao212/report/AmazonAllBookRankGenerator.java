package davenkin.guodao212.report;

import davenkin.guodao212.model.RankBook;
import org.apache.log4j.Logger;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static davenkin.guodao212.model.Merchant.AMAZON;

/**
 * Created by twer on 3/30/14.
 */
public class AmazonAllBookRankGenerator extends AbstractBookRankGenerator {
    private static final Logger logger = Logger.getLogger(AmazonAllBookRankGenerator.class);
    public static final String ALL_AMAZON_BOOK = "亚马逊全部图书";
    private final Pattern pattern = Pattern.compile("图书商品里排第(.*?)名");

    protected AmazonAllBookRankGenerator() {
        super(AMAZON, "navFooter", "//*[@id='SalesRank']", 1);
    }

    public boolean canHandle(RankBook rankBook) {
        return AMAZON.equals(rankBook.merchant()) && ALL_AMAZON_BOOK.equals(rankBook.category());
    }

    public int rank(RankBook rankBook) {
        logger.info("Trying to find rank for " + rankBook + " on page :" + rankBook.getBookPageUrl());

        HtmlUnitDriver driver = new HtmlUnitDriver();
        try {
            driver.get(rankBook.getBookPageUrl());
            waitPageLoad(driver);
            Matcher matcher = pattern.matcher(driver.findElementByXPath(searchXpath).getText());
            if (matcher.find()) {
                int rank = Integer.parseInt(matcher.group(1).replaceAll("\\D+", ""));
                if (rankFound(rank)) {
                    logger.info("Found rank for " + rankBook + " on page " + rankBook.getBookPageUrl() + ":rank" + rank);
                    return rank;
                }
            }
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


