package lk.ijse.motorComplex.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.motorComplex.bo.BOFactory;
import lk.ijse.motorComplex.bo.custom.DashboardBO;
import lk.ijse.motorComplex.bo.custom.impl.DashbordBOImpl;
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


public class DashboardFormController implements Initializable {
//    CarDAO carDAO = new CarDAOImpl();
//    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
//    CustOrderDAO custOrderDAO = new CustOrderDAOImpl();

  //  DashboardBO dashboardBO = new DashbordBOImpl();
    DashboardBO dashboardBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);
    @FXML
    private Label txtTime;

    @FXML
    private Label txtDate;

    @FXML
    private Label txtICarCount;

    @FXML
    private Label txtIncomeCount;

    @FXML
    private Label txtAttCount;

    @FXML
    private Label txtOrderCount;
    public LineChart chart;

    public void setEmpCount() {
        try {
            txtAttCount.setText(dashboardBO.getEmployeeCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setOrderCount() {
        try {
            txtOrderCount.setText(dashboardBO.getCustOrderCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setPaymentCount() {
        try {
            txtIncomeCount.setText(dashboardBO.getCustPaymentCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCarCount() {
        try {
            txtICarCount.setText(dashboardBO.getCarCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void carInventoryOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CarInventoryForm.fxml", event);
    }

    @FXML
    void dashboardOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml", event);
    }

    @FXML
    void employeeOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("EmployeeForm.fxml", event);
    }

    @FXML
    void reportOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("ReportForm.fxml", event);
    }

    @FXML
    void salaryOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("SalaryForm.fxml",event);
    }

    @FXML
    void supplierOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("SupplierForm.fxml", event);
    }

    public void closeOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml", mouseEvent);
    }

    @FXML
    void cashierLogOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("LoginForm.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        setEmpCount();
        setOrderCount();
        setCarCount();
        setPaymentCount();
      txtDate.setText(DateTimeUtil.dateNow());
      txtTime.setText(DateTimeUtil.timeNow());
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
                count= dashboardBO.getCustomerOrder(i);
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
