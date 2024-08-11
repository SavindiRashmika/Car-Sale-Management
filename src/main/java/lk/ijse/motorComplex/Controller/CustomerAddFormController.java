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
import lk.ijse.motorComplex.util.Navigation;
import lk.ijse.motorComplex.util.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerAddFormController implements Initializable {
   // CustomerDAO customerDAO = new CustomerDAOImpl();
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

    @FXML
    void addOnAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_Id(txtCustId.getText());
        customerDTO.setFirst_name(txtFirstName.getText());
        customerDTO.setLast_name(txtLastName.getText());
        customerDTO.setStreet(txtStreet.getText());
        customerDTO.setCity(txtCity.getText());
        customerDTO.setLane(txtLane.getText());
        customerDTO.setContact_number(txtContactNumber.getText());

//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Added Customer");
//        alert.showAndWait();
//        Navigation.close(event);

      //  if (alert.getResult().equals(ButtonType.OK)) {
            try {
                boolean add = customerBO.save(customerDTO);
                if (add) {
                    CustomerFormController.getInstance().loadDataTable();
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added").show();
                    Navigation.close(event);

                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Error Added").show();
                }

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
    //    }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    void custKeyReleased(KeyEvent event) {

    }

    @FXML
    void firstKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtFirstName,txtFirstName.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void laneKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtLane,txtLane.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void lastKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtLastName,txtLastName.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void streetKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtStreet,txtStreet.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }
}
