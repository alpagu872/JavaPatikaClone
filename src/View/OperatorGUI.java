package View;

import Helper.Config;
import Helper.Helper;
import Helper.ScreenHelper;
import Model.Course;
import Model.Operator;
import Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
    private JTextField textFieldShName;
    private JLabel lblShName;
    private JTextField textFieldShUsername;
    private JComboBox comboBoxShUserType;
    private JLabel lblNameSh;
    private JLabel lblUserTypeSh;
    private JButton btnShUser;
    private JScrollPane scrollCourse;
    private JTable tableCourse;
    private JPanel pnlCourseAdd;
    private JTextField textFieldCourseName;
    private JButton btnAddCourse;
    private JPanel pnlSubject;
    private JPanel pnlCourses;
    private JScrollPane scrllSubjectList;
    private JTable tableSubjectList;
    private final Operator operator;
    private DefaultTableModel modelUserList;
    private Object[] rowUserList;

    private DefaultTableModel modelCourseList;
    private Object[] rowCourseList;

    private JPopupMenu courseMenu;


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

        //Menu
        courseMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Güncelle");
        JMenuItem deleteMenu = new JMenuItem("Sil");
        courseMenu.add(updateMenu);
        courseMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int selectId = Integer.parseInt(tableCourse.getValueAt(tableCourse.getSelectedRow(), 0).toString());
            UpdateCourseGUI updateCourseGUI = new UpdateCourseGUI(Course.getFetch(selectId));
            updateCourseGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCourseModel();
                }
            });


        });

        deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectId = Integer.parseInt(tableCourse.getValueAt(tableCourse.getSelectedRow(), 0).toString());
                if (Course.delete(selectId)) {
                    Helper.showMessage("done");
                    loadCourseModel();
                } else {
                    Helper.showMessage("error;");
                }
            }

        });


        //USER LIST
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

        tableUserList.getModel().addTableModelListener(e -> {

            if (e.getType() == TableModelEvent.UPDATE) {
                int userId = Integer.parseInt(String.valueOf(tableUserList.getValueAt(tableUserList.getSelectedRow(), 0)));
                String name = tableUserList.getValueAt(tableUserList.getSelectedRow(), 1).toString();
                String username = tableUserList.getValueAt(tableUserList.getSelectedRow(), 2).toString();
                String pass = tableUserList.getValueAt(tableUserList.getSelectedRow(), 3).toString();
                String type = tableUserList.getValueAt(tableUserList.getSelectedRow(), 4).toString();

                if (User.update(userId, name, username, pass, type)) {
                    Helper.showMessage("done");
                }
                loadUserModel();

            }
        });


        modelCourseList = new DefaultTableModel();
        Object[] columnCourseList = {"ID", "Kurs Adı"};
        tableCourse.getTableHeader().setReorderingAllowed(false);
        modelCourseList.setColumnIdentifiers(columnCourseList);
        rowCourseList = new Object[columnCourseList.length];
        loadCourseModel();

        tableCourse.setModel(modelCourseList);
        tableCourse.setComponentPopupMenu(courseMenu);
        tableCourse.getColumnModel().getColumn(0).setMaxWidth(100);

        tableCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selectedRow = tableCourse.rowAtPoint(point);
                tableCourse.setRowSelectionInterval(selectedRow, selectedRow);
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
                if (Helper.confirm("sure")) {
                    int fieldUserId = Integer.parseInt(textFieldUserIdDel.getText());
                    if (User.delete(fieldUserId)) {
                        Helper.showMessage("done");
                        loadUserModel();
                    } else {
                        Helper.showMessage("error");
                    }
                }
            }
        });
        btnShUser.addActionListener(e -> {

            String name = textFieldShName.getText();
            String username = textFieldShUsername.getText();
            String type = comboBoxShUserType.getSelectedItem().toString();
            String query = User.searchQuery(name, username, type);

            ArrayList<User> searchingUsers = User.searchUserList(query);
            loadUserModel(searchingUsers);


        });
        btnAddCourse.addActionListener(e -> {

            if (Helper.isFieldEmpty(textFieldCourseName)) {
                Helper.showMessage("fill");
            } else {
                if (Course.add(textFieldCourseName.getText())) {
                    Helper.showMessage("done");
                    loadCourseModel();
                    textFieldCourseName.setText(null);
                } else {
                    Helper.showMessage("error");
                }
            }

        });
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tableCourse.getModel();
        clearModel.setRowCount(0);

        int i = 0;
        for (Course obj : Course.getList()) {
            i = 0;
            rowCourseList[i++] = obj.getId();
            rowCourseList[i++] = obj.getName();
            modelCourseList.addRow(rowCourseList);
        }


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

    public void loadUserModel(ArrayList<User> arrayList) {

        DefaultTableModel clearModel = (DefaultTableModel) tableUserList.getModel();
        clearModel.setRowCount(0);

        for (User obj : arrayList) {
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
