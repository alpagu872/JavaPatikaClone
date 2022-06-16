package View;

import Helper.Config;
import Helper.Helper;
import Helper.ScreenHelper;
import Model.Operator;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tabOperator;
    private JLabel lblWelcome;
    private JPanel pnlTop;
    private JButton btnLogout;
    private JPanel lblTabUsers;
    private JScrollPane scrollUserList;
    private JTable tableUserList;
    private JPanel panelUserForm;
    private JTextField textFieldName;
    private JTextField textFieldUserName;
    private JPasswordField passwordFieldUser;
    private JComboBox cmbUserType;
    private JButton btnUserAdd;
    private JLabel lblUser;
    private JLabel lblPass;
    private JLabel lblUserType;
    private JLabel lblName;
    private final Operator operator;
    private DefaultTableModel modelUserList;
    private Object[] rowUserList;

    public OperatorGUI(Operator operator) {
        this.operator = operator;

        add(wrapper);
        setSize(1000, 500);
        setLocation(ScreenHelper.ScreenCoord("x", getSize()), ScreenHelper.ScreenCoord("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);


        lblWelcome.setText(lblWelcome.getText() + " " + operator.getName());

        // ModelUserList
        modelUserList = new DefaultTableModel();
        Object[] colUserList = {"ID","Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        modelUserList.setColumnIdentifiers(colUserList);

        for (User obj: User.getList()){
            Object[] row = new Object[colUserList.length];
            row[0] = obj.getId();
            row[1] = obj.getName();
            row[2] = obj.getUsername();
            row[3] = obj.getPass();
            row[4] = obj.getType();

            modelUserList.addRow(row);
        }

//        Object[] firstRow = {"1", "Alpagu","Alpagudev","12345","operator"};
//        modelUserList.addRow(firstRow);

        tableUserList.setModel(modelUserList);
        tableUserList.getTableHeader().setReorderingAllowed(false);

        btnUserAdd.addActionListener(e -> {
            if (Helper.isFieldEmpty(textFieldName) || Helper.isFieldEmpty(textFieldUserName) || Helper.isFieldEmpty(passwordFieldUser)){
                Helper.showMessage("fill");
            }else {
                Helper.showMessage("done");
            }


        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setName("Alpagu");
        OperatorGUI operatorGUI = new OperatorGUI(op);
    }


}
