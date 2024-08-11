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
import lk.ijse.motorComplex.tm.CarTm;
import lk.ijse.motorComplex.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarUpdateFormController implements Initializable {
   // CarDAO carDAO = new CarDAOImpl();
 //  CarBOImpl carBO = new CarBOImpl();
   CarBO carBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CAR);
    private static CarTm carTm;
    @FXML
    private TextField txtCarId;

    @FXML
    private TextField txtModelName;

    @FXML
    private TextField txtModelYear;

    @FXML
    private TextField txtColor;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox cmbCategory;

    @FXML
    private TextField txtPrice;



    @FXML
    void doneOnAction(ActionEvent event)  {
        try {
//        CarDTO carDTO = new CarDTO();
//        carDTO.setModel_id(carTm.getCar_Id());
//        carDTO.setModel_name(txtModelName.getText());
//        carDTO.setQuantity(txtModelYear.getText());
//        carDTO.setModel_color(txtColor.getText());
//        carDTO.setCatagory(String.valueOf(cmbCategory.getValue()));
//        carDTO.setPrice(txtPrice.getText());
//
//
//            boolean update = carBO.updateCar(carDTO);

            boolean update = carBO.updateCar(new CarDTO(
                    CarUpdateFormController.carTm.getCar_Id(),
                    txtModelName.getText(),
                    txtModelYear.getText(),
                    txtColor.getText(),
                    (String) cmbCategory.getValue(),
                    txtPrice.getText()
            ));

        if (update) {
            CarInventoryFormController.getInstance().loadDataTable();
            new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
            Navigation.close(event);
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Error").show();
        }
        } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }

}
    public static void getData(CarTm carTm) {
        CarUpdateFormController.carTm = carTm;
       System.out.println(CarUpdateFormController.carTm.getModelColor());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCarId.setText(CarUpdateFormController.carTm.getCar_Id());
        txtModelName.setText(CarUpdateFormController.carTm.getModelName());
        txtModelYear.setText(CarUpdateFormController.carTm.getQuantity());
        txtColor.setText(CarUpdateFormController.carTm.getModelColor());
        cmbCategory.setValue(CarUpdateFormController.carTm.getCategory());
        txtPrice.setText(CarUpdateFormController.carTm.getPrice());
        setDataInComboBox();
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
    public String getCategory(){
        return String.valueOf(cmbCategory.getSelectionModel().getSelectedItem());
    }

    @FXML
    void colorKeyReleased(KeyEvent event) {

    }

    @FXML
    void modelIdKeyReleased(KeyEvent event) {

    }

    @FXML
    void modelNameKeyReleased(KeyEvent event) {

    }

    @FXML
    void modelYearKeyReleased(KeyEvent event) {

    }

}
