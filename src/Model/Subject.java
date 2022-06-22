package Model;

public class Subject {
    private int id;
    private int userId;
    private int courseId;
    private String name;
    private String lang;

    private Course course;
    private User user;

    public Subject(int id, int userId, int courseId, String name, String lang) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.name = name;
        this.lang = lang;
        this.course = Course.getFetch(courseId);
        this.user = User.getFetch(userId);
    }

    public Subject() {
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
}
