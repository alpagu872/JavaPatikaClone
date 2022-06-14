package PatikaDev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Example extends JFrame {
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

    public Example() {
        for (UIManager.LookAndFeelInfo feelInfo : UIManager.getInstalledLookAndFeels()){

            System.out.println(feelInfo.getName() + " ==> " + feelInfo.getClassName());

            if ("Nimbus".equals(feelInfo.getName())){
                try {
                    UIManager.setLookAndFeel(feelInfo.getClassName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        setContentPane(wrapper);
        setSize(400, 300);
        setTitle("Örnek Uygulama");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().width) / 2;

        setLocation(x, y);
        setVisible(true);

        btnLogin.addActionListener(e -> {
            if (((fieldUsername.getText().length() == 0)  || (Arrays.toString(fieldPass.getPassword()).isEmpty()))){
                JOptionPane.showMessageDialog(null,"Tüm alanları doldurun.","Hata",JOptionPane.INFORMATION_MESSAGE);

            }

            System.out.println("Username :"  +fieldUsername.getText());
            System.out.println("Pass : " + Arrays.toString(fieldPass.getPassword()));

        });
    }

}
