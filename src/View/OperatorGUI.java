package View;

import Helper.Config;
import Helper.Helper;
import Helper.ScreenHelper;
import Model.Operator;
import Model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JTextField textFieldUserIdDel;
    private JButton btnUserDel;
    private JLabel kullanıcıIDLabel;
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
        modelUserList = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] colUserList = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        modelUserList.setColumnIdentifiers(colUserList);
        rowUserList = new Object[colUserList.length];

        loadUserModel();


        tableUserList.setModel(modelUserList);
        tableUserList.getTableHeader().setReorderingAllowed(false);


        tableUserList.getSelectionModel().addListSelectionListener(e -> {

            try {
                String selectedUserId = tableUserList.getValueAt(tableUserList.getSelectedRow(), 0).toString();
                textFieldUserIdDel.setText(selectedUserId);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        });


        btnUserAdd.addActionListener(e -> {
            if (Helper.isFieldEmpty(textFieldName) || Helper.isFieldEmpty(textFieldUserName) || Helper.isFieldEmpty(passwordFieldUser)) {
                Helper.showMessage("fill");
            } else {
                String name = textFieldName.getText();
                String username = textFieldUserName.getText();
                String pass = String.valueOf(passwordFieldUser.getPassword());
                String type = cmbUserType.getSelectedItem().toString();
                if (User.add(name, username, pass, type)) {
                    Helper.showMessage("done");
                    loadUserModel();
                    textFieldName.setText(null);
                    textFieldUserName.setText(null);
                    passwordFieldUser.setText(null);

                } else {
                    Helper.showMessage("error");
                }
            }


        });
        btnUserDel.addActionListener(e -> {

            if (Helper.isFieldEmpty(textFieldUserIdDel)) {
                Helper.showMessage("fill");
            } else {
                int fieldUserId = Integer.parseInt(textFieldUserIdDel.getText());
                if (User.delete(fieldUserId)) {
                    Helper.showMessage("done");
                    loadUserModel();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setName("Alpagu");
        OperatorGUI operatorGUI = new OperatorGUI(op);


    }

    public void loadUserModel() {

        DefaultTableModel clearModel = (DefaultTableModel) tableUserList.getModel();
        clearModel.setRowCount(0);

        for (User obj : User.getList()) {
            int i = 0;
            rowUserList[i++] = obj.getId();
            rowUserList[i++] = obj.getName();
            rowUserList[i++] = obj.getUsername();
            rowUserList[i++] = obj.getPass();
            rowUserList[i++] = obj.getType();
            modelUserList.addRow(rowUserList);
        }
    }


}
