package lk.ijse.motorComplex.Controller;


import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.SalaryBO;
import lk.ijse.motorComplex.dto.EmployeeDTO;
import lk.ijse.motorComplex.dto.SalaryDTO;
import lk.ijse.motorComplex.tm.SalaryTm;
import lk.ijse.motorComplex.util.DateTimeUtil;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SalaryFormController implements Initializable {
//    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
//    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
//    SalaryDAO salaryDAO = new SalaryDAOImpl();
    SalaryBO salaryBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALARY);

    private static SalaryFormController controller;
    @FXML
    private TableView salaryTbl;
    @FXML
    private Label txtName;
    @FXML
    private TableColumn tblSalaryId;

    @FXML
    private TableColumn tblEmployeeID;

    @FXML
    private TableColumn tblName;

    @FXML
    private TableColumn tblSalary;

    @FXML
    private TableColumn tblDate;

    @FXML
    private TableColumn tblAttendance;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXComboBox cmbEmployeeID;

    @FXML
    private TextField txtdailySalary;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSalaryID;

    @FXML
    private TextField txtAttendance;
    static String employeeId = null;
    int count=0;

    ObservableList<SalaryTm> list = FXCollections.observableArrayList();
    private SalaryTm salaryTm;

    public SalaryFormController(){
        controller = this;
    }
    public static SalaryFormController getInstance(){
        return controller;
    }

    public void setEmployeeId() {
        try {
            ArrayList<String> list = salaryBO.getAllIdEmp();
            cmbEmployeeID.getItems().addAll(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setEmployee_id(String.valueOf(cmbEmployeeID.getSelectionModel().getSelectedItem()));
        salaryDTO.setSalary_id(txtSalaryID.getText());
        salaryDTO.setDate(DateTimeUtil.dateNow());
        salaryDTO.setSalary(setTotalSalary());
        salaryDTO.setEmployee_attendance_count(count);

        try {

            boolean add = salaryBO.save(salaryDTO);
            if (add){
                SalaryFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }

    public double setTotalSalary() {
        double total = 0.0;
        count = attendanceCount();
        System.out.println(employeeId);
        if (txtSalary.getText().equals("")) {
            int deilySalary = Integer.parseInt(((txtdailySalary.getText())));
            total = deilySalary * attendanceCount();
            txtSalary.setText(String.valueOf(total));

        } else {
            txtSalary.setText("");
            int deilySalary = Integer.parseInt(txtdailySalary.getText());
            total = deilySalary * attendanceCount();
            txtSalary.setText(String.valueOf(total));

        }
        return total;

    }

    private void setAllId() {
        try {
            ArrayList<String> ids = salaryBO.getAllId();
            for (int i = 0; i < ids.size(); i++) {
                setSalaryData(ids.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setSalaryData(String id) {
        try {
            SalaryDTO salaryDTO = salaryBO.getData(id);
            SalaryTm salaryTm = new SalaryTm();
            salaryTm.setEmployee_id(salaryDTO.getEmployee_id());
            salaryTm.setSalary_id(salaryDTO.getSalary_id());
            salaryTm.setDate(salaryDTO.getDate());
            salaryTm.setSalary(String.valueOf((setTotalSalary())));
            salaryTm.setSalary(String.valueOf((salaryDTO.getSalary())));
            salaryTm.setEmployee_attendance_count(String.valueOf(salaryDTO.getEmployee_attendance_count()));

            list.addAll(salaryTm);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadDataTable() {
        list.clear();
        salaryTbl.getItems().clear();
        setAllId();
    }

    public void searchKeyReleased(KeyEvent keyEvent) {
        salaryTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= salaryBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setSalaryData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEmployeeId();
        tblEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        tblSalaryId.setCellValueFactory(new PropertyValueFactory<>("salary_id"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
      //  tblAttendance.setCellValueFactory(new PropertyValueFactory<>("employee_attendance_count"));
        salaryTbl.setItems(list);
    }
    @FXML
    void carInventoryOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CarInventoryForm.fxml",event);
    }

    @FXML
    void closeOnAction(MouseEvent event) {
            System.exit(0);
    }

    @FXML
    void dashboardOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",event);
    }

    @FXML
    void employeeOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("EmployeeForm.fxml",event);
    }

    @FXML
    void reportOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("ReportForm.fxml",event);
    }

    @FXML
    void supplierOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("SupplierForm.fxml",event);

    }

    @FXML
    void tblMouseClick(MouseEvent event) {
        salaryTm = (SalaryTm) salaryTbl.getSelectionModel().getSelectedItem();
    }


    public int attendanceCount() {
        String employeeCount = null;
        try {
            employeeCount = salaryBO.getEmpCount(String.valueOf(cmbEmployeeID.getValue()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Integer.parseInt(employeeCount);
    }

    public void cmbEmployeeIDOnAction(ActionEvent event)throws SQLException, ClassNotFoundException {
        EmployeeDTO employeeDTO = salaryBO.getDataEmp(String.valueOf(cmbEmployeeID.getValue()));
        txtName.setText(employeeDTO.getEmployee_Fname() + " " + employeeDTO.getEmployee_Lname());
        txtAttendance.setText(String.valueOf(attendanceCount()));
        employeeId= String.valueOf(cmbEmployeeID.getValue());
    }
    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",event);
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure delete");

        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            try {
                if (salaryBO.remove(salaryTm.getSalary_id())) {
                    loadDataTable();
                    new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
