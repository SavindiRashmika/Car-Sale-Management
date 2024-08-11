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
import lk.ijse.motorComplex.bo.custom.SupplierBO;
import lk.ijse.motorComplex.dto.SupplierDTO;
import lk.ijse.motorComplex.tm.SupplierTm;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
   // SupplierDAO supplierDAO = new SupplierDAOImpl();
   SupplierBO supplierBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    private static SupplierFormController controller;
    private SupplierTm supplierTm;

    @FXML
    private TableView SupplierTbl;

    @FXML
    private TableColumn tblId;

    @FXML
    private TableColumn tblFirstName;

    @FXML
    private TableColumn tblLastName;

    @FXML
    private TableColumn tblContact;

    @FXML
    private TableColumn tblCity;

    @FXML
    private TextField txtSearch;

    @FXML
    void addOnAction(ActionEvent event) throws IOException {
        Navigation.popupNavigation("SupplierAddForm.fxml");
    }
    @FXML
    void updateOnAction(ActionEvent event) throws IOException {
        SupplierUpdateFormController.getData(supplierTm);
        Navigation.popupNavigation("SupplierUpdateForm.fxml");
    }
    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure delete");

        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            try {
                boolean delete = supplierBO.remove(supplierTm.getSup_Id());
                if (delete) {
                    SupplierFormController.getInstance().loadDataTable();
                    loadDataTable();
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Deleted").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadDataTable() {
        list.clear();
        SupplierTbl.getItems().clear();
        getAllIds();
    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }


    ObservableList<SupplierTm> list= FXCollections.observableArrayList();
    public SupplierFormController(){
        controller = this;
    }
    public static SupplierFormController getInstance(){
        return controller;
    }


    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",event);
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
    void supplierOnAction(ActionEvent event) throws IOException {

    }

    public void supplierDetailOnAction(ActionEvent event) throws IOException {
          Navigation.switchNavigation("SupplierOrderForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllIds();
        tblId.setCellValueFactory(new PropertyValueFactory<>("Sup_Id"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("fistName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        SupplierTbl.setItems(list);
    }

    private void getAllIds() {
        try {
            ArrayList<String> list= supplierBO.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setSupplierData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setSupplierData(String id) {
        try {
            SupplierDTO supplierDTO = supplierBO.getData(id);
            SupplierTm tm =new SupplierTm();
            tm.setSup_Id(supplierDTO.getSupplier_id());
            tm.setFistName(supplierDTO.getSupplier_Fname());
            tm.setLastName(supplierDTO.getSupplier_Lname());
            tm.setCity(supplierDTO.getCity());
            tm.setContact(supplierDTO.getContact());
            tm.setLane(supplierDTO.getLane());
            tm.setStreet(supplierDTO.getStreet());
            list.add(tm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void tblMouseClick(MouseEvent mouseEvent) {
        supplierTm = (SupplierTm) SupplierTbl.getSelectionModel().getSelectedItem();
    }
    @FXML
    void searchKeyReleased(KeyEvent event) {
        SupplierTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= supplierBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setSupplierData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
