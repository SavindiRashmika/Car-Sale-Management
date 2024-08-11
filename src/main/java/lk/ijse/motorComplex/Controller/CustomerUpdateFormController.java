package lk.ijse.motorComplex.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.CustomerBO;
import lk.ijse.motorComplex.dto.CustomerDTO;
import lk.ijse.motorComplex.tm.CustomerTm;
import lk.ijse.motorComplex.util.Navigation;
import lk.ijse.motorComplex.util.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerUpdateFormController implements Initializable {
    private static CustomerTm customerTm;
    // CustomerDAO customerDAO = new CustomerDAOImpl();
    //   CustomerBO customerBO = new CustomerBOImpl();
    CustomerBO customerBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtLane;

    @FXML
    private Button btnSave;

     // CustomerTm customerTm = new CustomerTm();

    @FXML
    void addOnAction(ActionEvent event) {
        try {
            boolean update = customerBO.update(new CustomerDTO(
                    customerTm.getCust_id(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtStreet.getText(),
                    txtCity.getText(),
                    txtLane.getText(),
                    txtContactNumber.getText()
            ));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure update");
            alert.showAndWait();
            if (update){
               CustomerFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Updated").show();
                Navigation.close(event);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static CustomerFormController getInstance() {
        return null;
    }


    public static void getData(CustomerTm customerTm) {

        CustomerUpdateFormController.customerTm = customerTm;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCustId.setText(customerTm.getCust_id());
        txtFirstName.setText(customerTm.getFistName());
        txtLastName.setText(customerTm.getLastName());
        txtContactNumber.setText(customerTm.getContact_number());
        txtStreet.setText(customerTm.getStreet());
        txtCity.setText(customerTm.getCity());
        txtLane.setText(customerTm.getLane());
    }



    @FXML
    void cityKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtCity,txtCity.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void contactKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave, txtContactNumber, txtContactNumber.getText(), "^(?:7|0|(?:\\+94))[0-9]{9,10}$","-fx-text-fill: black");
    }

    @FXML
    void firstKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtFirstName,txtFirstName.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void laneKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave, txtLane, txtLane.getText(), "[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void lastKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave, txtLastName, txtLastName.getText(), "[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void streetKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave, txtStreet, txtStreet.getText(), "[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    public void custKeyReleased(KeyEvent keyEvent) {

    }
}