package davenkin.bookrank.report;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import davenkin.bookrank.model.RankBook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static davenkin.bookrank.model.Merchant.JING_DONG;

/**
 * Created by twer on 4/21/14.
 */
public class JDBookRankGenerator extends AbstractBookRankGenerator {

    public static final int NUMBER_PER_PAGE = 15;

    public JDBookRankGenerator() {
        super(JING_DONG, "footer-2013", "//*[@id='plist']/div", NUMBER_PER_PAGE);
    }

    private String bookIdentifier(RankBook rankBook) {
        return rankBook.getBookPageUrl().split("/")[3].split(".html")[0];
    }

    @Override
    protected int tryFindRankOnCurrentPage(int pageIndex, HtmlUnitDriver driver, RankBook rankBook) {
        List<WebElement> elementsByXPath = driver.findElementsByXPath(searchXpath);
        ImmutableList<String> links = from(elementsByXPath).transform(new Function<WebElement, String>() {
            @Override
            public String apply(WebElement input) {
                return input.getAttribute("sku");
            }
        }).toList();

        int indexInSinglePage = links.indexOf(bookIdentifier(rankBook)) + 1;
        if (indexInSinglePage == 0) {
            return NO_RANK;
        }

        return NUMBER_PER_PAGE * (pageIndex - 1) + indexInSinglePage;
    }
}
