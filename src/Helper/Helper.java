package Helper;

import javax.swing.*;

public class Helper {
    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static boolean isFieldEmpty(JTextField textField){
        return textField.getText().trim().isEmpty();

    }

    public static void showMessage(String str){
        optionPaneLangTr();
        String message;
        String title;

        switch (str){
            case "fill":
                message = "Lütfen tüm alanları doldurunuz!";
                title = "Hata!";
                break;
            case "done":
                message = "İşlem Başarılı";
                title = "Başarılı İşlem";
                break;
            case "error":
                message = "Ekleme Başarısız";
                title = "Hata";
                break;
            case "duplicate":
                message = "Aynı kullanıcı adı daha önce kullanılmış.";
                title = "Uyarı";
                break;
            default:
                message = str;
                title = "Mesaj";
        }
        JOptionPane.showMessageDialog(null,message, title,JOptionPane.INFORMATION_MESSAGE);
    }


    public static void optionPaneLangTr(){
        UIManager.put("OptionPane.okButtonText" , "Tamam");

    }
}
