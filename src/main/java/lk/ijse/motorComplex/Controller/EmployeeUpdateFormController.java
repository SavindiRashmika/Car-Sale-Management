package lk.ijse.motorComplex.Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.EmployeeBO;
import lk.ijse.motorComplex.dto.EmployeeDTO;
import lk.ijse.motorComplex.tm.EmployeeTm;
import lk.ijse.motorComplex.util.Navigation;
import lk.ijse.motorComplex.util.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeUpdateFormController implements Initializable {
   // EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    EmployeeBO employeeBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

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
    private JFXComboBox cmbRole;

    @FXML
    private Button btnSave;


    private static EmployeeTm employeeTm;

    public void addOnAction(ActionEvent actionEvent) {
        try {
            boolean update = employeeBO.update(new EmployeeDTO(
                    EmployeeUpdateFormController.employeeTm.getEmp_Id(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtStreet.getText(),
                    txtCity.getText(),
                    txtLane.getText(),
                    (String) cmbRole.getValue(),
                    txtContactNumber.getText()
            ));
            if (update){
                EmployeeFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Updated").show();
                Navigation.close(actionEvent);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getData(EmployeeTm employeeTm) {
        EmployeeUpdateFormController.employeeTm = employeeTm;
        System.out.println(EmployeeUpdateFormController.employeeTm.getCity());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFirstName.setText(EmployeeUpdateFormController.employeeTm.getFistName());
        txtLastName.setText(EmployeeUpdateFormController.employeeTm.getLastName());
        txtCity.setText(EmployeeUpdateFormController.employeeTm.getCity());
        txtContactNumber.setText(EmployeeUpdateFormController.employeeTm.getContact());
        txtLane.setText(EmployeeUpdateFormController.employeeTm.getLane());
        cmbRole.setValue(EmployeeUpdateFormController.employeeTm.getRole());
        txtStreet.setText(EmployeeUpdateFormController.employeeTm.getStreet());
        setDataInComboBox();
    }
    public void setDataInComboBox() {
        ArrayList<String> role = new ArrayList<>();
        role.add("Admin");
        role.add("Cashier");
        role.add("Salesman");
        role.add("Other");
        cmbRole.getItems().addAll(role);
    }
    public String getRole(){
        return String.valueOf(cmbRole.getSelectionModel().getSelectedItem());
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
