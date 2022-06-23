package View;

import Helper.Helper;
import Helper.ScreenHelper;
import Model.Operator;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginScreen extends JFrame {
    private JPanel wrapper;
    private JPanel wrapperTop;
    private JPanel wrapperBottom;
    private JLabel alpaguDevJpanel;
    private JLabel titleLabel;
    private JLabel usernameJLabel;
    private JTextField fieldUsername;
    private JPasswordField fieldPass;
    private JLabel passLabel;
    private JButton btnLogin;

    public LoginScreen() {
        setContentPane(wrapper);
        setSize(400, 300);
        setTitle("Alpagu.DEV");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setLocation(ScreenHelper.ScreenCoord("x", getSize()), ScreenHelper.ScreenCoord("y", getSize()));
        setVisible(true);

        btnLogin.addActionListener(e -> {
            System.out.println(User.getFetch(fieldUsername.getText()));
            if (Helper.isFieldEmpty(fieldUsername) || Helper.isFieldEmpty(fieldPass)) {
                Helper.showMessage("fill");

            } else {
            }

            System.out.println("Username :" + fieldUsername.getText());
            System.out.println("Pass : " + Arrays.toString(fieldPass.getPassword()));

        });


    }


}
