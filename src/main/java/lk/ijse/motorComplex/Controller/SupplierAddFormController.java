package lk.ijse.motorComplex.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.SupplierBO;
import lk.ijse.motorComplex.dto.SupplierDTO;
import lk.ijse.motorComplex.util.Navigation;
import lk.ijse.motorComplex.util.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierAddFormController implements Initializable {
  //  SupplierDAO supplierDAO = new SupplierDAOImpl();
  SupplierBO supplierBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    @FXML
    private TextField txtSupplierId;

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
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplier_id(txtSupplierId.getText());
        supplierDTO.setSupplier_Fname(txtFirstName.getText());
        supplierDTO.setSupplier_Lname(txtLastName.getText());
        supplierDTO.setStreet(txtStreet.getText());
        supplierDTO.setCity(txtCity.getText());
        supplierDTO.setLane(txtLane.getText());
        supplierDTO.setContact(txtContactNumber.getText());

        try {
            boolean add = supplierBO.save(supplierDTO);
            if (add){
                SupplierFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Added").show();
                Navigation.close(event);

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
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

}
