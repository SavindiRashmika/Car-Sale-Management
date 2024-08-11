package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.DashboardBO;
import lk.ijse.motorComplex.dao.DAOFactory;
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

public class DashbordBOImpl implements DashboardBO {
    CarDAO carDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CAR);
    AttendanceDAO attendanceDAO =DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
    CustOrderDAO custOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_ORDER);

    public  String getCarCount() throws SQLException, ClassNotFoundException {
        return carDAO.getCarCount();
    }
    public  String getEmployeeCount() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getEmployeeCount();
    }
    public  String getCustOrderCount() throws SQLException, ClassNotFoundException {
        return custOrderDAO.getCustOrderCount();
    }
    public  String getCustPaymentCount() throws SQLException, ClassNotFoundException {
        return custOrderDAO.getCustPaymentCount();
    }
    public  String getCustomerOrder(int i) throws SQLException, ClassNotFoundException {
        return custOrderDAO.getCustomerOrder(i);
    }
}
