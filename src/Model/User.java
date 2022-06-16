package Model;

import Helper.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String username;
    private String pass;
    private String type;

    public User() {
    }

    public User(int id, String name, String username, String pass, String type) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.pass = pass;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        User obj;

        try {
            Statement st = DBHelper.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("userName"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;

    }

    public static boolean add(String name, String username, String pass, String type) {

        String query = "INSERT INTO user (name,username,pass,type) VALUES (?,?,?,?)";
        boolean key = true;

        try {
            PreparedStatement preparedStatement = DBHelper.getInstance().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, pass);
            preparedStatement.setString(4, type);
            key = preparedStatement.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;

    }
}
