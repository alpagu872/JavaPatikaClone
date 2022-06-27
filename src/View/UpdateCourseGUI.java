package View;

import Helper.Config;
import Helper.Helper;
import Helper.ScreenHelper;
import Model.Patika;

import javax.swing.*;

public class UpdateCourseGUI extends JFrame {
    private JPanel wrapper;
    private JTextField textFieldCourseName;
    private JLabel lblCourseName;
    private JButton btnUpdateCourse;
    private Patika patika;

    public UpdateCourseGUI(Patika patika) {
        this.patika = patika;
        add(wrapper);
        setSize(300, 150);
        setLocation(ScreenHelper.ScreenCoord("x", getSize()), ScreenHelper.ScreenCoord("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        textFieldCourseName.setText(patika.getName());

        btnUpdateCourse.addActionListener(e -> {

            if (Helper.isFieldEmpty(textFieldCourseName)) {
                Helper.showMessage("fill");
            } else {
                if (Patika.update(patika.getId(), textFieldCourseName.getText())) {
                    Helper.showMessage("done");

                }
                dispose();
            }
        });
    }

}
