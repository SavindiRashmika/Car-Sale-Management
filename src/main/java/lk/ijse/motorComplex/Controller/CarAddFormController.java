package lk.ijse.motorComplex.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.CarBO;
import lk.ijse.motorComplex.dto.CarDTO;
import lk.ijse.motorComplex.util.Navigation;
import lk.ijse.motorComplex.util.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarAddFormController implements Initializable {
   // CarDAO carDAO = new CarDAOImpl();
 //  CarBOImpl carBO = new CarBOImpl();
    CarBO carBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CAR);
    @FXML
    private TextField txtCarId;

    @FXML
    private TextField txtModelName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtModelYear;

    @FXML
    private TextField txtColor;

    @FXML
    private ComboBox cmbCategory;

    @FXML
    private Button btnSave;

    @FXML
    public void addOnAction(ActionEvent event) {
        CarDTO carDTO = new CarDTO();
        carDTO.setModel_id(txtCarId.getText());
        carDTO.setModel_name(txtModelName.getText());
        carDTO.setQuantity(txtModelYear.getText());
        carDTO.setModel_color(txtColor.getText());
        carDTO.setCatagory(getCatagory());
        carDTO.setPrice(txtPrice.getText());

        try {
            boolean add = carBO.saveCar(carDTO);
            if (add){
                CarInventoryFormController.getInstance().loadDataTable();
   //             CashierCarFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Added").show();
                Navigation.close(event);

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
                Navigation.close(event);

            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
    public void setDataInComboBox() {
        ArrayList<String> category = new ArrayList<>();
        category.add("Sport Car");
        category.add("Convertible");
        category.add("Hybrid Car");
        category.add("Electric Car");
        category.add("Diesel Car");
        cmbCategory.getItems().addAll(category);
    }
    public String getCatagory(){
        return String.valueOf(cmbCategory.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDataInComboBox();
    }

    @FXML
    void colorKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtColor,txtColor.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void modelIdKeyReleased(KeyEvent event) {}

    @FXML
    void modelNameKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtColor,txtColor.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

    @FXML
    void modelYearKeyReleased(KeyEvent event) {
        RegexUtil.regex(btnSave,txtColor,txtColor.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill: black");
    }

}

