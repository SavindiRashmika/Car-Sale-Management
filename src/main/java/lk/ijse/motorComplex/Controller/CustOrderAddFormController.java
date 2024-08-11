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
import lk.ijse.motorComplex.bo.custom.CustOrderBO;
import lk.ijse.motorComplex.dto.CustOrderDTO;
import lk.ijse.motorComplex.dto.CustomerOrderDetailsDTO;
import lk.ijse.motorComplex.util.DateTimeUtil;
import lk.ijse.motorComplex.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustOrderAddFormController implements Initializable {
//    CarDAO carDAO = new CarDAOImpl();
//    CustOrderDAO custOrderDAO = new CustOrderDAOImpl();
//    CustomerDAO customerDAO = new CustomerDAOImpl();

    CustOrderBO custOrderBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER_ORDER);


    @FXML
    private TextField txtOrderId;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXComboBox cmbModelID;

    @FXML
    private JFXComboBox cmbCustID;


    @FXML
    void saveOnAction(ActionEvent event) throws SQLException {
        CustOrderDTO custOrderDTO = new CustOrderDTO();
        custOrderDTO.setCustomer_id(String.valueOf(cmbCustID.getSelectionModel().getSelectedItem()));
        custOrderDTO.setCustomer_order_id(txtOrderId.getText());
        custOrderDTO.setCustomer_order_date(DateTimeUtil.dateNow());
        custOrderDTO.setCustomer_order_time(DateTimeUtil.timeNow());
        custOrderDTO.setPayment(String.valueOf(setTotalPayment()));

        CustomerOrderDetailsDTO details = new CustomerOrderDetailsDTO();
        details.setCustomer_order_id(txtOrderId.getText());
        details.setModel_id(String.valueOf(cmbModelID.getSelectionModel().getSelectedItem()));
        details.setQuantity(txtQty.getText());
        details.setPrice(txtPrice.getText());

        boolean  placeOrder = custOrderBO.placeOrder(custOrderDTO,details);
        if (placeOrder){
            CustomerOrderFormController.getInstance().loadDataTable();
            new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Added").show();
            Navigation.close(event);

        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setcmbcustId();
        setCmbModelID();
    }

    public void setcmbcustId(){
        try {
            ArrayList<String> list = custOrderBO.setcmbcustId();
            cmbCustID.getItems().addAll(list);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void setCmbModelID(){
        try {
            ArrayList<String> list = custOrderBO.setCmbModelID();
            cmbModelID.getItems().addAll(list);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public double setTotalPayment(){
        double total = 0.0;
        total = Double.parseDouble(txtQty.getText()) * Double.parseDouble(txtPrice.getText());
        return total;
    }

    @FXML
    void custOrderKeyReleased(KeyEvent event) {
    //    RegexUtil.regex(btnSave,txtOrderId,txtOrderId.getText(),"^[A-Za-z0-9]*$","-fx-text-fill: black");
    }

    @FXML
    void priceKeyReleased(KeyEvent event) {
    //    RegexUtil.regex(btnSave,txtPrice,txtPrice.getText(),"^0*(\\d{1,9})$ ","-fx-text-fill: black");
    }

    @FXML
    void qtyKeyReleased(KeyEvent event) {
     //   RegexUtil.regex(btnSave,txtQty,txtQty.getText(),"^0*(\\d{1,9})$ ","-fx-text-fill: black");
    }
//    public  boolean placeOrder(CustOrderDTO custOrderDTO, CustomerOrderDetailsDTO details) throws SQLException {
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            if (custOrderDAO.save(custOrderDTO)){
//
//                if (custOrderDAO.addCustomerOrderDetail(details)){
//
//                    if (carDAO.CustomerOrderupdateData(details)){
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
