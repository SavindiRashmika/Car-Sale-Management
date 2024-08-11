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
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.EmployeeBO;
import lk.ijse.motorComplex.dto.EmployeeDTO;
import lk.ijse.motorComplex.tm.EmployeeTm;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
  //  EmployeeDAO employeeDAO = new EmployeeDAOImpl();
  EmployeeBO employeeBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @FXML
    public TableView EmployeeTbl;

    @FXML
    public TableColumn tblId;

    @FXML
    public TableColumn tblFirstName;
    @FXML
    public TableColumn tblLastName;

    @FXML
    public TableColumn tblCity;

    @FXML
    public TableColumn tblContact;

    @FXML
    private TableColumn tblRole;

    @FXML
    private TextField txtSearch;

    private static EmployeeTm employeeTm;

    private static EmployeeFormController controller;

    ObservableList<EmployeeTm> list= FXCollections.observableArrayList();

    public EmployeeFormController(){
        controller = this;
    }
    public static EmployeeFormController getInstance(){
        return controller;
    }

    @FXML
    void addOnAction(ActionEvent event) throws IOException {
        Navigation.popupNavigation("EmployeeAddForm.fxml");
    }
    @FXML
    void updateOnAction(ActionEvent event) throws IOException {
        EmployeeUpdateFormController.getData(employeeTm);
        Navigation.popupNavigation("EmployeeUpdateForm.fxml");
    }
    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure delete");

        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            try {
                boolean delete = employeeBO.remove(employeeTm.getEmp_Id());
                if (delete) {
                    EmployeeFormController.getInstance().loadDataTable();
                    loadDataTable();
                    new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Deleted").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void searchOnAction(ActionEvent event) {
    }



    public void dashboardOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",event);
    }

    public void carInventoryOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CarInventoryForm.fxml",event);
    }

    public void supplierOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("SupplierForm.fxml",event);
    }

    public void employeeOnAction(ActionEvent event) throws IOException {

    }

    public void closeOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllIds();
        tblId.setCellValueFactory(new PropertyValueFactory<>("Emp_Id"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("fistName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        EmployeeTbl.setItems(list);
    }

    private void getAllIds() {
        try {
            ArrayList<String> list= employeeBO.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setEmployeeData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setEmployeeData(String id) {

        try {
            EmployeeDTO employeeDTO = employeeBO.getData(id);
            EmployeeTm tm=new EmployeeTm();
            tm.setEmp_Id(employeeDTO.getEmployee_id());
            tm.setFistName(employeeDTO.getEmployee_Fname());
            tm.setLastName(employeeDTO.getEmployee_Lname());
            tm.setCity(employeeDTO.getCity());
            tm.setContact(employeeDTO.getContact());
            tm.setLane(employeeDTO.getLane());
            tm.setRole(employeeDTO.getRole());
            tm.setStreet(employeeDTO.getStreet());
            list.add(tm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadDataTable() {
        list.clear();
        EmployeeTbl.getItems().clear();
        getAllIds();
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
        employeeTm = (EmployeeTm) EmployeeTbl.getSelectionModel().getSelectedItem();
    }
    @FXML
    void searchKeyReleased(KeyEvent event) {
        EmployeeTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= employeeBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setEmployeeData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
