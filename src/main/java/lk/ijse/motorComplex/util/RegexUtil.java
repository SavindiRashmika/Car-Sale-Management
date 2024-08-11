package lk.ijse.motorComplex.util;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static boolean regex(Button button, TextField textField, String type , String regex,String style){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(type);
        if (matcher.matches()){
            textField.requestFocus();
            textField.setStyle(style);
            button.setDisable(false);
        }else {
            button.setDisable(true);
            textField.setStyle("-fx-text-fill: Red");
        }
        return matcher.matches();
    }
}
