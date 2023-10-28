package datasource;

import javax.swing.*;
import java.sql.*;

import static java.lang.Integer.parseInt;

public class ConnectionDBH2 {
    public String name_db = "dataBaseH2";
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:C:\\Users\\Barca\\dbProva;AUTO_SERVER=TRUE";
    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "sa";
    private static Connection connectData = null;

    //SINGLETON
    private static final ConnectionDBH2 instance = new ConnectionDBH2();

    private ConnectionDBH2() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            connectData = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("DB connected!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    public Connection getConnectData() {
        return connectData;
    }

    public static ConnectionDBH2 getInstance() {
        return instance;
    }

    public static String getMaxID() {
        PreparedStatement ps;
        String lastID = "";
        int lastIDNum = 0;
        try {
            ps = getInstance().getConnectData().prepareStatement("SELECT MAX(id) FROM person");
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                lastID = r.getString(1);
            }
            lastIDNum = parseInt(lastID) + 1;
            return lastID = String.valueOf(lastIDNum);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            return null;
        }

    }
}
