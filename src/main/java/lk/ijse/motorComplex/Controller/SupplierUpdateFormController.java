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
import lk.ijse.motorComplex.tm.SupplierTm;
import lk.ijse.motorComplex.util.Navigation;
import lk.ijse.motorComplex.util.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierUpdateFormController implements Initializable {
   // SupplierDAO supplierDAO = new SupplierDAOImpl();
   SupplierBO supplierBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    private static SupplierTm supplierTm;

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
    public void addOnAction(ActionEvent event) {
        try {
            boolean update = supplierBO.update(new SupplierDTO(
                    SupplierUpdateFormController.supplierTm.getSup_Id(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtStreet.getText(),
                    txtCity.getText(),
                    txtLane.getText(),
                    txtContactNumber.getText()
            ));
            if (update){
                SupplierFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Updated").show();
               Navigation.close(event);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getData(SupplierTm supplierTm) {
       SupplierUpdateFormController.supplierTm = supplierTm;
       System.out.println(SupplierUpdateFormController.supplierTm.getCity());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtFirstName.setText(SupplierUpdateFormController.supplierTm.getFistName());
        txtLastName.setText(SupplierUpdateFormController.supplierTm.getLastName());
        txtContactNumber.setText(SupplierUpdateFormController.supplierTm.getContact());
        txtStreet.setText(SupplierUpdateFormController.supplierTm.getStreet());
        txtCity.setText(SupplierUpdateFormController.supplierTm.getCity());
        txtLane.setText(SupplierUpdateFormController.supplierTm.getLane());

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
    void lastKeyRelesaed(KeyEvent event) {
        RegexUtil.regex(btnSave, txtLastName, txtLastName.getText(), "[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void streetKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave, txtStreet, txtStreet.getText(), "[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

}
