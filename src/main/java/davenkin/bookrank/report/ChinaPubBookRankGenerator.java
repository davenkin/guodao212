package davenkin.bookrank.report;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import davenkin.bookrank.model.RankBook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static davenkin.bookrank.model.Merchant.CHINA_PUB;

/**
 * Created by twer on 3/30/14.
 */
public class ChinaPubBookRankGenerator extends AbstractBookRankGenerator {

    public ChinaPubBookRankGenerator() {
        super(CHINA_PUB, "", "//div[@class='xians-list']/ul/li/table/tbody/tr[1]/td/a", 21);
    }


    protected String bookIdentifier(RankBook rankBook) {
        return null;
    }

    @Override
    protected int tryFindRankOnCurrentPage(int pageIndex, HtmlUnitDriver driver, RankBook rankBook) {
        List<WebElement> elementsByXPath = driver.findElementsByXPath(searchXpath);

        ImmutableList<String> links = from(elementsByXPath).transform(new Function<WebElement, String>() {
            @Override
            public String apply(WebElement input) {
                return input.getAttribute("href");
            }
        }).toList();
        int count = links.indexOf("http://product.china-pub.com/3769562") + 1;
        if (count == 0) {
            return NO_RANK;
        }

        return 20 * (pageIndex - 1) + count;

    }

}