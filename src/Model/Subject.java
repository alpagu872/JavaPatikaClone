package Model;

import Helper.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Subject {
    private int id;
    private int userId;
    private int courseId;
    private String name;
    private String lang;

    private Course course;
    private User educator;

    public Subject(int id, int userId, int courseId, String name, String lang) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.name = name;
        this.lang = lang;
        this.course = Course.getFetch(courseId);
        this.educator = User.getFetch(userId);
    }

    public Subject() {
    }

    public static ArrayList<Subject> getList() {
        ArrayList<Subject> subjectArrayList = new ArrayList<>();

        Subject obj;

        try {
            Statement st = DBHelper.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subject ");
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                int courseId = rs.getInt("courseId");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Subject(id, userId, courseId, name, lang);
                subjectArrayList.add(obj);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return subjectArrayList;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }
}
