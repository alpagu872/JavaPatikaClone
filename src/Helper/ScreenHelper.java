package Helper;

import javax.swing.*;
import java.awt.*;

public class ScreenHelper {
    public static int ScreenCoord(String eksen,Dimension size){
        int point;
        switch (eksen){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.width) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }
}
