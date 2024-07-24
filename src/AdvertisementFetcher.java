import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementFetcher {
    private static final String DB_URL = "jdbc:sqlite:ads.db";

    public static List<Advertisement> fetchAds() {
        List<Advertisement> ads = new ArrayList<>();
        String sql = "SELECT title, imageUrl FROM Advertisements";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String title = rs.getString("title");
                String imageUrl = rs.getString("imageUrl");
                ads.add(new Advertisement(title, imageUrl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ads;
    }
}
