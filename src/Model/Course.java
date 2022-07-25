package Model;

import Helper.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
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

    public static ArrayList<Course> getList() {
        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        try {
            Statement st = DBHelper.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patika");
            while (rs.next()) {
                obj = new Course(rs.getInt("id"), rs.getString("name"));
                courseList.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courseList;
    }


    public static boolean add(String name) {
        String query = "INSERT INTO patika (name) VALUES (?)";
        try {
            PreparedStatement pr = DBHelper.getInstance().prepareStatement(query);
            pr.setString(1, name);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean update(int id, String name) {
        String query = "UPDATE patika SET name = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBHelper.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static Course getFetch(int id) {
        Course obj = null;
        String query = "SELECT * FROM patika WHERE id = ?";

        try {
            PreparedStatement pr = DBHelper.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Course(rs.getInt("id"), rs.getString("name"));

            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;

    }

    public static boolean delete(int id) {
        String query = "DELETE FROM patika WHERE id = ?";
        try {
            PreparedStatement pr = DBHelper.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
