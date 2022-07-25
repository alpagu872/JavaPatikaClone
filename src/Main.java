import Helper.Helper;
import Helper.ScreenHelper;
import Model.Operator;
import View.LoginScreen;
import View.OperatorGUI;

public class Main {
    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setName("Alpagu");
        OperatorGUI operatorGUI = new OperatorGUI(op);
    }
}