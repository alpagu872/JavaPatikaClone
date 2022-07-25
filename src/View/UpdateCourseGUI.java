package View;

import Helper.Config;
import Helper.Helper;
import Helper.ScreenHelper;
import Model.Course;

import javax.swing.*;

public class UpdateCourseGUI extends JFrame {
    private JPanel wrapper;
    private JTextField textFieldCourseName;
    private JLabel lblCourseName;
    private JButton btnUpdateCourse;
    private Course course;

    public UpdateCourseGUI(Course course) {
        this.course = course;
        add(wrapper);
        setSize(300, 150);
        setLocation(ScreenHelper.ScreenCoord("x", getSize()), ScreenHelper.ScreenCoord("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        textFieldCourseName.setText(course.getName());

        btnUpdateCourse.addActionListener(e -> {

            if (Helper.isFieldEmpty(textFieldCourseName)) {
                Helper.showMessage("fill");
            } else {
                if (Course.update(course.getId(), textFieldCourseName.getText())) {
                    Helper.showMessage("done");

                }
                dispose();
            }
        });
    }

}
