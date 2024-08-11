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
import lk.ijse.motorComplex.bo.custom.SupOrderBO;
import lk.ijse.motorComplex.dto.SupOrderDTO;
import lk.ijse.motorComplex.dto.SupplierOrderDetailsDTO;
import lk.ijse.motorComplex.util.DateTimeUtil;
import lk.ijse.motorComplex.util.Navigation;
import lk.ijse.motorComplex.util.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupOrderAddFormController implements Initializable {
//    CarDAO carDAO = new CarDAOImpl();
//    SupOrderDAO supOrderDAO = new SupOrderDAOImpl();
  //  SupplierDAO supplierDAO = new SupplierDAOImpl();
    SupOrderBO supOrderBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER_ORDER);

    @FXML
    private TextField txtSupId;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPayment;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXComboBox cmbModelID;

    @FXML
    private JFXComboBox cmbSupplierId;

    @FXML
    private Button btnSave;

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        SupOrderDTO supOrderDTO = new SupOrderDTO();
        supOrderDTO.setSupplier_id(String.valueOf(cmbModelID.getSelectionModel().getSelectedItem()));
        supOrderDTO.setSupplier_order_id(txtOrderId.getText());
        supOrderDTO.setSupplier_order_date(DateTimeUtil.dateNow());
        supOrderDTO.setSupplier_order_time(DateTimeUtil.timeNow());
        supOrderDTO.setPayment(String.valueOf(setTotalPayment()));

        SupplierOrderDetailsDTO details = new SupplierOrderDetailsDTO();
        details.setModel_id(String.valueOf(cmbSupplierId.getSelectionModel().getSelectedItem()));
        details.setSupplier_order_id(txtOrderId.getText());
        details.setQuantity(txtQty.getText());
        details.setPrice(txtPrice.getText());

        boolean  placeOrder = supOrderBO.placeOrder(supOrderDTO,details);
        if (placeOrder){
            SupplierOrderFormController.getInstance().loadDataTable();
            new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Added").show();
            Navigation.close(event);

        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
        }

    }


    public double setTotalPayment(){
        double total = 0.0;
        total = Double.parseDouble(txtQty.getText()) * Double.parseDouble(txtPrice.getText());
        return total;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSupplierId();
        setCmbModelIDId();
    }

    public void setCmbModelIDId(){
        try {
            ArrayList<String> list = supOrderBO.getAllIdCar();
            cmbModelID.getItems().addAll(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void setSupplierId() {
        try {
            ArrayList<String> list = supOrderBO.getAllIdSup();
            cmbSupplierId.getItems().addAll(list);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void orderIdKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtOrderId,txtOrderId.getText(),"^[A-Za-z0-9]*$","-fx-text-fill: black");
    }

    @FXML
    void priceKeyReleased(KeyEvent event) {
     //   RegexUtil.regex(btnSave,txtPrice,txtPrice.getText(),"^0*(\\d{1,9})$ ","-fx-text-fill: black");
    }

    @FXML
    void qtyKeyReleased(KeyEvent event) {
      //  RegexUtil.regex(btnSave,txtQty,txtQty.getText(),"^0*(\\d{1,9})$ ","-fx-text-fill: black");
    }
//    public  boolean placeOrder(SupOrderDTO supOrderDTO, SupplierOrderDetailsDTO details) {
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            if (supOrderDAO.save(supOrderDTO)) {
//                if (supOrderDAO.addSupplierOrderDetails(details)) {
//                    if (carDAO.updateData(details)) {
//                        connection.commit();
//                        return true;
//                    } else {
//                        connection.rollback();
//                        return false;
//                    }
//                } else {
//                    connection.rollback();
//                    return false;
//                }
//            }else {
//                connection.rollback();
//                return false;
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return true;
//    }

}
