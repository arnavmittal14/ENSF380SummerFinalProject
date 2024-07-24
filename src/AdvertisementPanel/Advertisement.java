package AdvertisementPanel;
public class Advertisement {
    private String title;
    private String imageUrl;

    public Advertisement(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
