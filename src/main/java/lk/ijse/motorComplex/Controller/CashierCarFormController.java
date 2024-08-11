package lk.ijse.motorComplex.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class CashierCarFormController implements Initializable {
   // CarDAO carDAO = new CarDAOImpl();
   CarBO carBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CAR);
    @FXML
    private TableView CashierCarTbl;

    @FXML
    private TableColumn tblModelId;

    @FXML
    private TableColumn tblModelName;

    @FXML
    private TableColumn tblModelYear;

    @FXML
    private TableColumn tblModelColor;

    @FXML
    private TableColumn tblCategory;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn tblPrice;

    @FXML
    private TextField txtCarId;

    ObservableList<CarTm> list = FXCollections.observableArrayList();

    @FXML
    void attendOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("AttendanceForm.fxml",event);
    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CashierDashboardForm.fxml",event);
    }

    @FXML
    void carOnAction(ActionEvent event) {}

    @FXML
    void closeOnAction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CustomerForm.fxml",event);
    }

    @FXML
    void dashboardOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CashierDashboard.fxml",event);
    }

    @FXML
    public void searchKeyReleased(KeyEvent event) {
        CashierCarTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= carBO.getSearchIdsCar(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setCashierCarData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getAllIds() {
        try {
            ArrayList<String> list= carBO.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setCashierCarData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCashierCarData(String id) {
        try {
            CarDTO carDTO = carBO.getData(id);
            CarTm tm=new CarTm();
            tm.setCar_Id(carDTO.getModel_id());
            tm.setModelName(carDTO.getModel_name());
            tm.setModelColor(carDTO.getModel_color());
            tm.setQuantity(carDTO.getQuantity());
            tm.setCategory(carDTO.getCatagory());
            tm.setPrice(carDTO.getPrice());
            list.add(tm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDataTable() {
        list.clear();
        CashierCarTbl.getItems().clear();
        getAllIds();
    }

    @FXML
    public void tblMouseClick(MouseEvent event) {}

    public static void getData(CarTm carTm) { }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataTable();
     //   getData(carTm);
        tblModelId.setCellValueFactory(new PropertyValueFactory<>("Car_Id"));
        tblModelName.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        tblModelYear.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblModelColor.setCellValueFactory(new PropertyValueFactory<>("modelColor"));
        tblCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        CashierCarTbl.setItems(list);
    }
}
