package lk.ijse.motorComplex.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.motorComplex.bo.custom.CashierBordBO;
import lk.ijse.motorComplex.bo.custom.impl.CashierBordBOImpl;
import lk.ijse.motorComplex.dao.custom.AttendanceDAO;
import lk.ijse.motorComplex.dao.custom.CarDAO;
import lk.ijse.motorComplex.dao.custom.CustOrderDAO;
import lk.ijse.motorComplex.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.motorComplex.dao.custom.impl.CarDAOImpl;
import lk.ijse.motorComplex.dao.custom.impl.CustOrderDAOImpl;
import lk.ijse.motorComplex.util.DateTimeUtil;
import lk.ijse.motorComplex.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.motorComplex.Controller.CarInventoryFormController.carTm;

public class CashierDashboardFormController implements Initializable {
//    CarDAO carDAO = new CarDAOImpl();
//    CustOrderDAO custOrderDAO = new CustOrderDAOImpl();
//    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();

    CashierBordBO cashierBordBO = new CashierBordBOImpl();

    @FXML
    private Label txtEmpCount;

    @FXML
    private Label txtCarCount;

    @FXML
    private Label txtSales;

    @FXML
    private Label txtTime;

    @FXML
    private Label txtDate;

    public LineChart chart;

    public void setEmpCount() {
        try {
            txtEmpCount.setText(cashierBordBO.getEmployeeCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setOrderCount() {
        try {
            txtSales.setText(cashierBordBO.getCustOrderCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCarCount() {
        try {
            txtCarCount.setText(cashierBordBO.getCarCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void attendOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("AttendanceForm.fxml",event);

    }

    @FXML
    void closeOnAction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void dashboardOnAction(ActionEvent event) {

    }

    public void customerOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CustomerForm.fxml",event);
    }

    public void adminLoginOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("LoginForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        setEmpCount();
        setOrderCount();
        setCarCount();
        txtDate.setText(DateTimeUtil.dateNow());
        txtTime.setText(DateTimeUtil.timeNow());
    }

    public void carOnAction(ActionEvent event) throws IOException {
        CashierCarFormController.getData(carTm);
        Navigation.switchNavigation("CahierCarForm.fxml",event);
    }
    private void setData() {
        XYChart.Series series=new XYChart.Series();
        series.setName("Order");
        try {
            String dateNow = DateTimeUtil.dateNow();
            String[] date = dateNow.split("-");
            int days = DateTimeUtil.getDays(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
            String count=null;
            for (int i = 0; i < days; i++) {
                count= cashierBordBO.getCustomerOrder(i);
                int ii=i;
                ii++;
                series.getData().add(new XYChart.Data(String.valueOf(ii), Double.parseDouble(count)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        chart.getData().add(series);
    }

}
