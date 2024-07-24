package AdvertisementPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AdDatabaseHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/subway_screen";
    private static final String USER = "root";
    private static final String PASS = "";

    // Method to create the database and table
    public static void createDatabaseAndTable() {
    	try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                String sql = "CREATE TABLE IF NOT EXISTS Advertisements (\n"
                        + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " title TEXT NOT NULL,\n"
                        + " imageUrl TEXT NOT NULL,\n"
                        + " displayTime INTEGER DEFAULT 10\n"
                        + ");";
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert advertisements into the database
    public static void insertAds(List<Advertisement> ads) {
        String sql = "INSERT INTO Advertisements(title, imageUrl) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Advertisement ad : ads) {
                pstmt.setString(1, ad.getTitle());
                pstmt.setString(2, ad.getImageUrl());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
