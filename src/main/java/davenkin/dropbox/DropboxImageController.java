package davenkin.dropbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Davenkin on 9/18/14.
 */

@Controller
public class DropboxImageController {

    private Random random = new Random();

    @Autowired
    private DropboxImageService dropboxImageService;

    @RequestMapping(value = "api/randomImages", method = RequestMethod.GET)
    @ResponseBody
    public List<String> randomImages() {
        List<String> links = dropboxImageService.allImages();
        ArrayList<String> randomLinks = new ArrayList<String>();

        for (int index = 0; index < 3; index++) {
            randomLinks.add(links.get(random.nextInt(links.size())));
        }

        return randomLinks;
    }

    public void setDropboxImageService(DropboxImageService dropboxImageService) {
        this.dropboxImageService = dropboxImageService;
    }
}
