package Model;

import Helper.DBHelper;
import Helper.Helper;

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
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;

    }

    public static boolean add(String name, String username, String pass, String type) {

        String query = "INSERT INTO user (name,username,pass,type) VALUES (?,?,?,?)";
        boolean key = true;

        User findUser = User.getFetch(username);
        if (findUser != null) {
            Helper.showMessage("duplicate");
            return false;
        }
        try {
            PreparedStatement pr = DBHelper.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, username);
            pr.setString(3, pass);
            pr.setString(4, type);

            int response = pr.executeUpdate();


            if (response == -1) {
                Helper.showMessage("error");
            }

            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


        return true;

    }

    public static boolean delete(int id) {
        String query = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement pr = DBHelper.getInstance().prepareStatement(query);
            pr.setInt(1, id);

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;

    }

    public static boolean update(int id, String name, String username, String pass, String type) {

        User findUser = User.getFetch(username);
        if (findUser != null && findUser.getId() != id) {
            Helper.showMessage("duplicate");
            return false;
        }
        String query = "UPDATE user SET name = ?, userName = ?, pass = ?, type = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBHelper.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, username);
            pr.setString(3, pass);
            pr.setString(4, type);
            pr.setInt(5, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return true;

    }

    public static User getFetch(String username) {


        User obj = null;
        String query = "SELECT * FROM user WHERE userName = ?";
        try {
            PreparedStatement pr = DBHelper.getInstance().prepareStatement(query);
            pr.setString(1, username);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("userName"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return obj;
    }

    public static User getFetch(int id) {


        User obj = null;
        String query = "SELECT * FROM user WHERE id = ?";
        try {
            PreparedStatement pr = DBHelper.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("userName"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return obj;
    }

    public static ArrayList<User> searchUserList(String query) {
        ArrayList<User> userList = new ArrayList<>();

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
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userList;
    }

    public static String searchQuery(String name, String username, String type) {
        String query = "SELECT * FROM user WHERE userName LIKE '%{{username}}%' AND name LIKE '%{{name}}%'";
        query = query.replace("{{username}}", username);
        query = query.replace("{{name}}", name);
        if (!type.isEmpty()) {

            query += "AND type='{{type}}'";
            query = query.replace("{{type}}", type);

        }
        query = query.replace("{{type}}", type);

        return query;
    }
}
