package Helper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private Connection connection = null;

    public Connection DBConnect() {

        try {
            this.connection = DriverManager.getConnection(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.connection;

    }

    public static Connection getInstance() {
        DBHelper db = new DBHelper();
        return db.DBConnect();
    }
}
