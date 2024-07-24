package AdvertisementPanel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdScraper {
    public static List<Advertisement> scrapeAds() {
        List<Advertisement> ads = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.adsoftheworld.com/").get();
            Elements adElements = doc.select(".some-css-selector-for-ads"); // Adjust the CSS selector accordingly

            for (Element adElement : adElements) {
                String title = adElement.select(".ad-title").text(); // Adjust the CSS selector accordingly
                String imageUrl = adElement.select("img").attr("src"); // Adjust the CSS selector accordingly
                ads.add(new Advertisement(title, imageUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ads;
    }
}
