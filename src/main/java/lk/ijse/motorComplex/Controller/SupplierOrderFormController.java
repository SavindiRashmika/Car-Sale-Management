package lk.ijse.motorComplex.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.SupOrderBO;
import lk.ijse.motorComplex.dto.OrderTmDTO;
import lk.ijse.motorComplex.tm.SupOrderTm;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierOrderFormController implements Initializable {
//    QuaryDAO quaryDAO = new QuaryDAOImpl();
//    SupOrderDetailsDAO supOrderDetailsDAO = new SupOrderDetailsDAOImpl();
//    SupOrderDAO supOrderDAO = new SupOrderDAOImpl();
SupOrderBO supOrderBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER_ORDER);

    private static SupplierOrderFormController controller;
    @FXML
    private TableView orderTbl;

    @FXML
    private TableColumn tblModelId;

    @FXML
    private TableColumn tblOrderId;

    @FXML
    private TableColumn tblDate;

    @FXML
    private TableColumn tblPayment;

    @FXML
    private TableColumn tblQty;

    @FXML
    private TableColumn tblPrice;

    @FXML
    private TextField txtSearch;


    ObservableList<SupOrderTm> list= FXCollections.observableArrayList();
    private SupOrderTm supOrderTm;

    public SupplierOrderFormController(){
        controller = this;
    }
    public static SupplierOrderFormController getInstance(){
        return controller;
    }


    @FXML
   public void deleteOnAction(ActionEvent event) {
        try {
            boolean delete = supOrderBO.remove(supOrderTm.getModel_id());
            if (delete){
                SupplierOrderFormController.getInstance().loadDataTable();
                loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Deleted").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void saveOnAction(ActionEvent event) throws IOException {
        Navigation.popupNavigation("SupOrderAddForm.fxml");
    }
    @FXML
    void updateOnAction(ActionEvent event) throws IOException {
        Navigation.popupNavigation("SupOrderUpdateForm.fxml");
    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("SupplierForm.fxml",event);
    }

    public void carInventoryOnAction(ActionEvent event) throws IOException {
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
        Navigation.switchNavigation("SupplierForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllIds();
        tblModelId.setCellValueFactory(new PropertyValueFactory<>("model_id"));
        tblOrderId.setCellValueFactory(new PropertyValueFactory<>("supplier_order_id"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("Supplier_order_date"));
        tblQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));

        orderTbl.setItems(list);
    }
    private void getAllIds() {
        try {
            ArrayList<String> list= supOrderBO.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setSupOrderData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setSupOrderData(String id) {

        try {

            OrderTmDTO orderTmDto = supOrderBO.getSupOrderData(id);
            SupOrderTm tm=new SupOrderTm();

            if (orderTmDto.getModel_id()!=null) {
                tm.setModel_id(orderTmDto.getModel_id());
                tm.setSupplier_order_id(orderTmDto.getSupplier_order_id());
                tm.setSupplier_order_date(orderTmDto.getSupplier_order_date());
                tm.setPrice(orderTmDto.getPrice());
                tm.setQuantity(orderTmDto.getQuantity());
                tm.setPayment(orderTmDto.getPayment());
                list.add(tm);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void loadDataTable() {
        list.clear();
        orderTbl.getItems().clear();
        getAllIds();
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
        supOrderTm = (SupOrderTm) orderTbl.getSelectionModel().getSelectedItem();
    }

    @FXML
    void searchKeyReleased(KeyEvent event) {
        orderTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= supOrderBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setSupOrderData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
