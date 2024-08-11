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
import lk.ijse.motorComplex.bo.custom.CustomerBO;
import lk.ijse.motorComplex.dto.CustomerDTO;
import lk.ijse.motorComplex.tm.CustomerTm;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    CustomerBO customerBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
  //  CustomerDAO customerDAO = new CustomerDAOImpl();
    private static CustomerFormController controller;
    private  CustomerTm customerTm;


    @FXML
    private TableView CustomerTbl;

    @FXML
    private TableColumn tblCustId;

    @FXML
    private TableColumn tblFirstName;

    @FXML
    private TableColumn tblLastName;

    @FXML
    private TableColumn tblContactNumber;

    @FXML
    private TableColumn tblCity;

    @FXML
    private TextField txtSearch;

    ObservableList<CustomerTm> list= FXCollections.observableArrayList();


    public CustomerFormController(){
        controller = this;
    }
    public static CustomerFormController getInstance(){
        return controller;
    }

    @FXML
    void addOnAction(ActionEvent event) throws IOException {
        Navigation.popupNavigation("CustomerAddForm.fxml");
    }

    @FXML
    void updateOnAction(ActionEvent event) throws IOException {
        CustomerUpdateFormController.getData(customerTm);
        Navigation.popupNavigation("CustomerUpdateForm.fxml");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure delete");

        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            try {
                boolean delete = customerBO.remove(customerTm.getCust_id());
                if (delete) {
                    getInstance().loadDataTable();
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

    public void loadDataTable() {
        list.clear();
        CustomerTbl.getItems().clear();
        getAllIds();
    }

    private void getAllIds() {
        try {
            ArrayList<String> list= customerBO.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setCustomerData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCustomerData(String id) {
        try {
            CustomerDTO customerDTO = customerBO.getData(id);
            CustomerTm tm=new CustomerTm();
            tm.setCust_id(customerDTO.getCustomer_Id());
            tm.setFistName(customerDTO.getFirst_name());
            tm.setLastName(customerDTO.getLast_name());
            tm.setCity(customerDTO.getCity());
            tm.setContact_number(customerDTO.getContact_number());
            tm.setLane(customerDTO.getLane());
            tm.setStreet(customerDTO.getStreet());
            list.add(tm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void tblMouseClick(MouseEvent mouseEvent) {
        customerTm = (CustomerTm) CustomerTbl.getSelectionModel().getSelectedItem();
    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }
    @FXML
    void CustomerOrderOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CustomerOrderForm.fxml",event);
    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CashierDashboardForm.fxml",event);
    }

    @FXML
    void closeOnAction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void dashboardOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CashierDashboardForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllIds();
        tblCustId.setCellValueFactory(new PropertyValueFactory<>("Cust_id"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("fistName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblContactNumber.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        tblCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        CustomerTbl.setItems(list);
    }

    public void attendanceOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("AttendanceForm.fxml",event);
    }
    @FXML
    void searchKeyReleased(KeyEvent event) {
        CustomerTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= customerBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setCustomerData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
