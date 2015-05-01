package davenkin.bookrank.report;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import davenkin.bookrank.model.RankBook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static davenkin.bookrank.model.Merchant.DANG_DANG;

/**
 * Created by twer on 4/21/14.
 */
public class DangDangBookRankGenerator extends AbstractBookRankGenerator {

    public static final int NUMBER_PER_PAGE = 72;

    public DangDangBookRankGenerator() {
        super(DANG_DANG, "footer", "//li/div/a[@class='pic']", NUMBER_PER_PAGE);
    }

    @Override
    protected int tryFindRankOnCurrentPage(int pageIndex, HtmlUnitDriver driver, RankBook rankBook) {
        List<WebElement> elementsByXPath = driver.findElementsByXPath(searchXpath);
        ImmutableList<String> links = from(elementsByXPath).transform(new Function<WebElement, String>() {
            @Override
            public String apply(WebElement input) {
                return input.getAttribute("href").split("#")[0];
            }
        }).toList();

        int indexInSinglePage = links.indexOf(rankBook.getBookPageUrl()) + 1;
        if (indexInSinglePage == 0) {
            return NO_RANK;
        }

        return NUMBER_PER_PAGE * (pageIndex - 1) + indexInSinglePage;

    }

}
