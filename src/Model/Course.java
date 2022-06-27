package Model;

import Helper.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int userId;
    private int courseId;
    private String name;
    private String lang;

    private Patika patika;
    private User educator;

    public Course(int id, int userId, int courseId, String name, String lang) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.name = name;
        this.lang = lang;
        this.patika = Patika.getFetch(courseId);
        this.educator = User.getFetch(userId);
    }

    public Course() {
    }

    public static ArrayList<Course> getList() {
        ArrayList<Course> courseArrayList = new ArrayList<>();

        Course obj;

        try {
            Statement st = DBHelper.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subject ");
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                int courseId = rs.getInt("courseId");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id, userId, courseId, name, lang);
                courseArrayList.add(obj);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courseArrayList;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patika getCourse() {
        return patika;
    }

    public void setCourse(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }
}
