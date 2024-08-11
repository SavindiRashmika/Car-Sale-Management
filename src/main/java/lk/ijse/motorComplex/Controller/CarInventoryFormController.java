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
import lk.ijse.motorComplex.bo.custom.CarBO;
import lk.ijse.motorComplex.dto.CarDTO;
import lk.ijse.motorComplex.tm.CarTm;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarInventoryFormController implements Initializable {
    CarBO carBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CAR);
   // CarBOImpl carBO = new CarBOImpl();
    //CarDAO carDAO = new CarDAOImpl();
    protected static CarTm carTm;
    private static CarInventoryFormController controller;

    @FXML
    private TableColumn tblCarId;

    @FXML
    private TableColumn tblCarName;

    @FXML
    private TableColumn tblYear;

    @FXML
    private TableColumn tblColor;

    @FXML
    private TableView CarTbl;

    @FXML
    private TableColumn tblCategory;

    @FXML
    private TableColumn tblPrice;

    @FXML
    private TextField txtSearch;


    ObservableList<CarTm> list= FXCollections.observableArrayList();

    public static CarInventoryFormController getInstance(){
        return controller;
    }
    public CarInventoryFormController(){
        controller = this;
    }

    @FXML
    void addOnAction(ActionEvent event) throws IOException {
        Navigation.popupNavigation("CarAddForm.fxml");
    }

    @FXML
    void updateOnAction(ActionEvent event) throws IOException {
        CarUpdateFormController.getData(carTm);
        Navigation.popupNavigation("CarUpdateForm.fxml");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure delete");

        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            try {
                boolean delete = carBO.removeCar(carTm.getCar_Id());
                if (delete) {
                    CarInventoryFormController.getInstance().loadDataTable();
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
    void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",event);
    }

    @FXML
    void carInventoryOnAction(ActionEvent event) {

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
        Navigation.switchNavigation("SupplierForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllIds();
        tblCarId.setCellValueFactory(new PropertyValueFactory<>("Car_Id"));
        tblCarName.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        tblYear.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblColor.setCellValueFactory(new PropertyValueFactory<>("modelColor"));
        tblCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        CarTbl.setItems(list);
    }
    private void getAllIds() {
        try {
            ArrayList<String> list= carBO.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setCarData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCarData(String id) {

        try {
            CarDTO carDTO = carBO.getData(id);
            CarTm tm=new CarTm();
            tm.setCar_Id(carDTO.getModel_id());
            tm.setModelName(carDTO.getModel_name());
            tm.setQuantity(carDTO.getQuantity());
            tm.setModelColor(carDTO.getModel_color());
            tm.setCategory(carDTO.getCatagory());
            tm.setPrice(carDTO.getPrice());
            list.add(tm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadDataTable() {
        list.clear();
        CarTbl.getItems().clear();
        getAllIds();
    }

    public void tblMouseClick(MouseEvent mouseEvent) {

        carTm = (CarTm) CarTbl.getSelectionModel().getSelectedItem();
    }

    public void searchKeyReleased(KeyEvent keyEvent) {
        CarTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= carBO.getSearchIdsCar(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setCarData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
