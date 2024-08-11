package lk.ijse.motorComplex.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.AttendanceBO;
import lk.ijse.motorComplex.dto.AttendanceDTO;
import lk.ijse.motorComplex.tm.AttendanceTm;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttendanceFormController implements Initializable {
//    QuaryDAO quaryDAO = new QuaryDAOImpl();
//    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
//    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    AttendanceBO attendanceBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    private static AttendanceFormController controller;
    @FXML
    private TableView AttendanceTbl;

    @FXML
    private TableColumn tblEmployeeId;

    @FXML
    private TableColumn tblFirstName;

    @FXML
    private TableColumn tblLastName;

    @FXML
    private TableColumn tblTime;

    @FXML
    private TableColumn tblDate;

    @FXML
    private TextField txtAttendanceId;

    @FXML
    private TextField txtSearch;

    @FXML
    private AnchorPane totalEmployee;

    @FXML
    private Label txtEmpCount;

    public void setEmpCount() {
        try {
            txtEmpCount.setText(attendanceBO.getAllIdEmp());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    ObservableList<AttendanceTm> list = FXCollections.observableArrayList();
    private AttendanceTm attendanceTm;

    public AttendanceFormController(){
        controller = this;
    }
    public static AttendanceFormController getInstance(){
        return controller;
    }
    @FXML
    void txtIdOnKeyRelease(KeyEvent event) {

        try {
            if (attendanceBO.existEmployee(txtAttendanceId.getText())){
                if (attendanceBO.todayExistEmployee(txtAttendanceId.getText())){
                    if (attendanceBO.addAttendance(txtAttendanceId.getText())){
                        txtAttendanceId.setText("");
                        loadDataTable();
                        new Alert(Alert.AlertType.CONFIRMATION,"Added Employee Attendance.").show();
                    }
                }else {
                    txtAttendanceId.setText("");
                    new Alert(Alert.AlertType.ERROR,"Error").show();
                }
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadDataTable() {
        list.clear();
        AttendanceTbl.getItems().clear();
        setAllId();
    }

    private void setAllId() {
        try {

            ArrayList<String> ids = attendanceBO.getAllId();
            for (int i = 0; i < ids.size(); i++) {
                setAttendanceData(ids.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setAttendanceData(String id) {
        try {

            AttendanceDTO attendanceDTO = attendanceBO.getData(id);
            AttendanceTm attendanceTm = new AttendanceTm();
            attendanceTm.setEmployee_id(attendanceDTO.getEmployee_id());
            attendanceTm.setFirst_Name(attendanceDTO.getFirst_Name());
            attendanceTm.setLast_Name(attendanceDTO.getLast_Name());
            attendanceTm.setTime(attendanceDTO.getTime());
            attendanceTm.setDate(attendanceDTO.getDate());
            list.addAll(attendanceTm);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            boolean delete = attendanceBO.remove(attendanceTm.getEmployee_id());
            if (delete){
                AttendanceFormController.getInstance().loadDataTable();
                loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Deleted").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CashierDashboardForm.fxml",event);
    }

    public void closeOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void customerOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CustomerForm.fxml",event);
    }

    public void dashboardOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CashierDashboardForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataTable();
        setEmpCount();
        setEmplCount();
        tblEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        tblTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        AttendanceTbl.setItems(list);
    }

    public void searchKeyReleased(KeyEvent keyEvent) {
        AttendanceTbl.getItems().clear();
        list.clear();
        try {

            ArrayList<String> ids= attendanceBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setAttendanceData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void attendanceOnAction(ActionEvent event) throws IOException {
            Navigation.switchNavigation("AttendanceForm.fxml",event);
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
        attendanceTm = (AttendanceTm) AttendanceTbl.getSelectionModel().getSelectedItem();
    }
    @FXML
    void carInventoryOnAction(ActionEvent event) throws IOException {
         Navigation.switchNavigation("CashierCarForm.fxml",event);
    }

    public void setEmplCount() {
        try {
            txtEmpCount.setText(attendanceBO.getEmployeeCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
