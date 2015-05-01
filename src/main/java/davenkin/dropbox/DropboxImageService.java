package davenkin.dropbox;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.FluentIterable.from;

/**
 * Created by Davenkin on 9/18/14.
 */
public class DropboxImageService {
    private static Logger logger = org.apache.log4j.Logger.getLogger(DropboxImageService.class);

    private List<String> images = new ArrayList<String>();

    private Resource initImages;


    public List<String> allImages() {
        return images;
    }

    public void init() {
        try {
            images = FileUtils.readLines(initImages.getFile());
        } catch (IOException e) {
            logger.error("Cannot initialise default dropbox images.");
        }
    }

    public void fetchAllImages() {
        logger.info("Fetch dropbox public images.");
        try {
            WebDriver driver = new HtmlUnitDriver();
            driver.get("https://www.dropbox.com/sh/4ktvr3ex5psxe8u/AACmEeOYvBKT-2IAoy2w4d7Ba/images?lst");
            logger.info(driver.getPageSource());
            ImmutableList<String> imageNames = from(driver.findElements(By.xpath("//*[@id='list-view-container']/ol//div[@class='filename']//a")))
                    .transform(new Function<WebElement, String>() {
                        @Override
                        public String apply(WebElement input) {
                            return getImageName(input.getAttribute("href"));
                        }
                    }).toList();
            if (imageNames.size() > 0) {
                images = imageNames;
            }
            driver.quit();
        } catch (Exception e) {
            logger.error("Cannot fetch dropbox images, will use initial images.");
        }
        logger.info("Finished fetch dropbox public images. " + images.size() + " images in total");

    }

    private static String getImageName(String link) {
        int length = link.split("/").length;
        return link.split("/")[length - 1].split("\\?")[0];
    }

    public void setInitImages(Resource initImages) {
        this.initImages = initImages;
    }

    public static void main(String[] args){
        final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
        htmlUnitDriver.get("https://www.dropbox.com/sh/4ktvr3ex5psxe8u/AACmEeOYvBKT-2IAoy2w4d7Ba/images?lst");
        System.out.println(htmlUnitDriver.getPageSource());
    }
}
