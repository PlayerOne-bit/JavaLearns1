import java.sql.*;
import javax.swing.*;

public class sqlconnect {
    private static Connection attach = null;

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            attach = DriverManager.getConnection("jdbc:sqlite:Practice.db");
            JOptionPane.showMessageDialog(null, "Connection successful!");
            return attach;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}


