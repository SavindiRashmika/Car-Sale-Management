package lk.ijse.motorComplex.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import lk.ijse.motorComplex.db.DBConnection;
import lk.ijse.motorComplex.util.Navigation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

public class ReportFormController {
    @FXML
    void carInventoryOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CarInventoryForm.fxml", event);
    }

    @FXML
    void salaryOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("SalaryForm.fxml", event);
    }
    @FXML
    void custOrderOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\SAVINDI RASHMITHA\\JaspersoftWorkspace\\MyReports\\CustomerOrder.jrxml");
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(Arrays.asList(new Object()));

            HashMap hm = new HashMap();


            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void closeOnAction(MouseEvent event) {
        System.exit(0);
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
    void employeeReportOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\SAVINDI RASHMITHA\\JaspersoftWorkspace\\MyReports\\Attendance.jrxml");
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(Arrays.asList(new Object()));

            HashMap hm = new HashMap();


            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void reportOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("ReportForm.fxml", event);
    }

    @FXML
    void supplierOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("SupplierForm.fxml", event);
    }

    @FXML
    void supplierReport(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\SAVINDI RASHMITHA\\JaspersoftWorkspace\\MyReports\\SupplierOrder.jrxml");
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(Arrays.asList(new Object()));

            HashMap hm = new HashMap();


            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
