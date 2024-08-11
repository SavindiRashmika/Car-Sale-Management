package lk.ijse.motorComplex.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.LoginBO;
import lk.ijse.motorComplex.dto.LoginDTO;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
   // LoginDAO loginDAO = new LoginDAOImpl();
    LoginBO loginBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);
    @FXML
    private Hyperlink cashier_form_admin;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private Button admin_login;

    @FXML
    private TextField admin_name;

    @FXML
    private TextField admin_password;

    @FXML
    private Hyperlink admin_form_cashier;


    @FXML
    private AnchorPane cashier_form;

    @FXML
    private Button cashier_login;

    @FXML
    private TextField cashier_name;

    @FXML
    private TextField cashier_password;

    @FXML
    private AnchorPane main_form;

    @FXML
    void closeOnAction(MouseEvent event) {
        System.exit(0);
    }


    @FXML
    void adminLoginOnAction(ActionEvent event) throws IOException {

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser_name(admin_name.getText());
        loginDTO.setPassword(admin_password.getText());

       try {
           String role = loginBO.check(loginDTO);

           if (role.equals("Admin")){
               Navigation.switchNavigation("DashboardForm.fxml", event);
         }else {
              new Alert(Alert.AlertType.ERROR, "Invalid username or password ").show();
            }

      } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }



    @FXML
    void cashierLoginOnAction(ActionEvent event) throws IOException {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser_name(cashier_name.getText());
        loginDTO.setPassword(cashier_password.getText());

        try {
            String role = loginBO.check(loginDTO);

            if (role.equals("cashier")){
              Navigation.switchNavigation("CashierDashboardForm.fxml", event);
    }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Invalid username or password ").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void switchForm(ActionEvent event){
        if (event.getSource() == admin_form_cashier) {
            admin_form.setVisible(false);
            cashier_form.setVisible(true);
        }else if(event.getSource()== cashier_form_admin){
            admin_form.setVisible(true);
            cashier_form.setVisible(false);
        }
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
//    private boolean isValidated(){
//        String adminName = admin_name.getText().trim();
//        String adminPassword = admin_password.getText().trim();
//
//        return (adminName.length()<10 || adminPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?!=.*?[#?!@$%^&*-]).{4,10}$"));
//    }
/*try {
            LoginModel.login(login);
            if (!isValidated()) {
                new Alert(Alert.AlertType.CONFIRMATION, "Invalid username or password ").show();
            }else if(isValidated()){
                new Alert(Alert.AlertType.CONFIRMATION, "Login  Succesfull ").show();
                Navigation.switchNavigation("DashboardForm.fxml", event);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            new Alert(Alert.AlertType.CONFIRMATION, "Something went wrong, please try again").show();
            throwables.printStackTrace();
        }*/
}
