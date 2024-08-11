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
import lk.ijse.motorComplex.bo.custom.CustOrderBO;
import lk.ijse.motorComplex.dto.CustOrderTmDTO;
import lk.ijse.motorComplex.tm.CustOrderTm;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerOrderFormController implements Initializable {
//    QuaryDAO quaryDAO = new QuaryDAOImpl();
//    CustomerOrderDetailsDAO customerOrderDetailsDAO = new CustomerOrderDetailsDAOImpl();
//    CustOrderDAO custOrderDAO = new CustOrderDAOImpl();

    CustOrderBO custOrderBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER_ORDER);

    private static CustomerOrderFormController controller;
    @FXML
    private TableView custOrderTbl;

    @FXML
    private TableColumn tblModelId;

    @FXML
    private TableColumn tblOrderId;

    @FXML
    private TableColumn tblDate;

    @FXML
    private TableColumn tblPrice;

    @FXML
    private TableColumn tblQty;

    @FXML
    private TableColumn tblPayment;

    @FXML
    private TextField txtSearch;


    ObservableList<CustOrderTm> list= FXCollections.observableArrayList();
    private CustOrderTm custOrderTm;

    public CustomerOrderFormController(){
        controller = this;
    }
    public static CustomerOrderFormController getInstance(){
        return controller;
    }
    @FXML
    void addOnAction(ActionEvent event) throws IOException {
        Navigation.popupNavigation("CustOrderAddForm.fxml");
    }

    @FXML
    void updateOnAction(ActionEvent event) throws IOException {
//        CustOrderUpdateFormController.getData(custOrderTm);
//        Navigation.popupNavigation("CustOrderUpdateForm.fxml");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure delete");

        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            try {
                boolean delete = custOrderBO.remove(custOrderTm.getModel_id());
                if (delete) {
                    CustomerOrderFormController.getInstance().loadDataTable();
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

    public void dashboardOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CashierDashboardForm.fxml",event);
    }

    public void closeOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CustomerForm.fxml",event);
    }

    public void loadDataTable() {
        list.clear();
        custOrderTbl.getItems().clear();
        getAllIds();
    }

    private void getAllIds() {
        try {
            ArrayList<String> list= custOrderBO.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setCustOrderData(list.get(i));

            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCustOrderData(String id) {

        try {
            CustOrderTmDTO custOrderTmDto = custOrderBO.getCustOrderData(id);
            CustOrderTm custOrderTm = new CustOrderTm();

            custOrderTm.setModel_id(custOrderTmDto.getModel_id());
            custOrderTm.setCustomer_order_id(custOrderTmDto.getCustomer_order_id());
            custOrderTm.setCustomer_order_date(custOrderTmDto.getCustomer_order_date());
            custOrderTm.setPrice(custOrderTmDto.getPrice());
            custOrderTm.setQuantity(custOrderTmDto.getQuantity());
            custOrderTm.setPayment(custOrderTmDto.getPayment());
            list.addAll(custOrderTm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblMouseClick(MouseEvent event) {
        custOrderTm = (CustOrderTm) custOrderTbl.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     //   loadDataTable();
        getAllIds();
        tblModelId.setCellValueFactory(new PropertyValueFactory<>("model_id"));
        tblOrderId.setCellValueFactory(new PropertyValueFactory<>("customer_order_id"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("Customer_order_date"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        custOrderTbl.setItems(list);
    }
    @FXML
    void searchKeyReleased(KeyEvent event) {
        custOrderTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= custOrderBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setCustOrderData(ids.get(i));
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
//    public  boolean placeOrder(CustOrderDTO custOrderDTO, CustomerOrderDetailsDTO details) throws SQLException {
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            if (custOrderDAO.addCustOrder(custOrderDTO)){
//
//                if (custOrderDAO.addCustomerOrderDetail(details)){
//
//                    if (CarDAOImpl.CustomerOrderupdateData(details)){
//
//                        connection.commit();
//                    }else {
//                        connection.rollback();
//                        return false;
//                    }
//                }else {
//                    connection.rollback();
//                    return false;
//                }
//
//            }else {
//                connection.rollback();
//                return false;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }finally{
//            connection.setAutoCommit(true);
//        }
//        return true;
//    }
}
