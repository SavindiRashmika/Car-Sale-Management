package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;

import java.sql.SQLException;

public interface DashboardBO extends SuperBO {
    public  String getCarCount() throws SQLException, ClassNotFoundException;
    public  String getEmployeeCount() throws SQLException, ClassNotFoundException;
    public  String getCustOrderCount() throws SQLException, ClassNotFoundException ;
    public  String getCustPaymentCount() throws SQLException, ClassNotFoundException;
    public  String getCustomerOrder(int i) throws SQLException, ClassNotFoundException;
}
