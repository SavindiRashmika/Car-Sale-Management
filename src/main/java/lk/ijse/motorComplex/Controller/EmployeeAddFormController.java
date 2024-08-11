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
import lk.ijse.motorComplex.util.Navigation;
import lk.ijse.motorComplex.util.RegexUtil;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAddFormController implements Initializable {
   // EmployeeDAO employeeDAO = new EmployeeDAOImpl();
   EmployeeBO employeeBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @FXML
    private TextField txtEmployeeId;

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

    public void addOnAction(ActionEvent actionEvent) {
        if (txtCity.getText().isEmpty() ||
                txtContactNumber.getText().isEmpty() ||
                txtLane.getText().isEmpty() ||
                txtFirstName.getText().isEmpty() ||
                txtLastName.getText().isEmpty() ||
                txtEmployeeId.getText().isEmpty() ||
                txtStreet.getText().isEmpty()
        ) {
            new Alert(Alert.AlertType.WARNING, "Something wrong ").show();
            Navigation.close(actionEvent);
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_id(txtEmployeeId.getText());
        employeeDTO.setEmployee_Fname(txtFirstName.getText());
        employeeDTO.setEmployee_Lname(txtLastName.getText());
        employeeDTO.setStreet(txtStreet.getText());
        employeeDTO.setCity(txtCity.getText());
        employeeDTO.setLane(txtLane.getText());
        employeeDTO.setRole(getRole());
        employeeDTO.setContact(txtContactNumber.getText());

        try {
            boolean add = employeeBO.save(employeeDTO);
            if (add) {
                EmployeeFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Added").show();
                Navigation.close(actionEvent);

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Error Added").show();
                Navigation.close(actionEvent);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDataInComboBox();
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
    void empKeyReleased(KeyEvent event) {

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
