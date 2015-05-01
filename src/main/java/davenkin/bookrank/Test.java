package davenkin.bookrank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Davenkin on 5/2/14.
 */
public class Test {


    public static void main(String[] args) {
//        System.out.println(2/3);
//        LastPageIndexCache.cachePageIndex(2,27);
//        for(int i = 1;i<=33;i++)
//        {
//            int i1 = LastPageIndexCache.pageIndexFromCache(i, 2, 33);
//            System.out.println(i1);
//        }
        String var = "http://item.jd.com/11423256.html";
        dd(var);

        String dd = "//div[@class='zg_title']/a[contains(@href,'%s')]/../../div[@class='zg_rankLine']";
        String wewew = String.format(dd, "wewew");
        System.out.println(wewew);
        String ddd = "http://www.amazon.cn/%E5%AE%9E%E7%8E%B0%E9%A2%86%E5%9F%9F%E9%A9%B1%E5%8A%A8%E8%AE%BE%E8%AE%A1-%E5%BC%97%E5%86%9C/dp/B00IYTVWA6/";
        String[] split = ddd.split("dp/");
        System.out.println(split.length);
        String[] split1 = split[1].split("/");
        System.out.println(split1[0]);
        String ddf = "图书商品里排第33,474名";
        Pattern ds = Pattern.compile("图书商品里排第(.*?)名");
        Matcher matcher = ds.matcher(ddf);
        boolean b = matcher.find();
        String group = matcher.group(1).replaceAll("\\D+", "");
        int aa = Integer.parseInt(group);
        System.out.println(aa);

    }

    private static void dd(String var) {
        String[] split = var.split("/");
        System.out.println(split.length);
        System.out.println(split[3]);
        String[] split1 = split[3].split(".html");
        System.out.println(split1.length);
        System.out.println(split1[0]);
    }
}
