package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.CashierBordBO;
import lk.ijse.motorComplex.dao.custom.AttendanceDAO;
import lk.ijse.motorComplex.dao.custom.CarDAO;
import lk.ijse.motorComplex.dao.custom.CustOrderDAO;
import lk.ijse.motorComplex.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.motorComplex.dao.custom.impl.CarDAOImpl;
import lk.ijse.motorComplex.dao.custom.impl.CustOrderDAOImpl;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.util.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierBordBOImpl implements CashierBordBO {
    CarDAO carDAO = new CarDAOImpl();
    CustOrderDAO custOrderDAO = new CustOrderDAOImpl();
    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();

    public  String getEmployeeCount() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getEmployeeCount();
    }
    public  String getCustOrderCount() throws SQLException, ClassNotFoundException {
        return custOrderDAO.getCustOrderCount();
    }
    public  String getCarCount() throws SQLException, ClassNotFoundException {
        return carDAO.getCarCount();
    }
    public  String getCustomerOrder(int i) throws SQLException, ClassNotFoundException {
        return custOrderDAO.getCustomerOrder(i);
    }
}
