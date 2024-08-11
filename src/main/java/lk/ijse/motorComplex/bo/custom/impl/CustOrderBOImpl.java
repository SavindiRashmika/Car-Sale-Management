package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.CustOrderBO;
import lk.ijse.motorComplex.dao.DAOFactory;
import lk.ijse.motorComplex.dao.custom.*;
import lk.ijse.motorComplex.db.DBConnection;
import lk.ijse.motorComplex.dto.CustOrderDTO;
import lk.ijse.motorComplex.dto.CustOrderTmDTO;
import lk.ijse.motorComplex.dto.CustomerOrderDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustOrderBOImpl implements CustOrderBO {
    CarDAO carDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CAR);
    QuaryDAO quaryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUARY);
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    CustomerOrderDetailsDAO customerOrderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_ORDER_DETAILS);
    CustOrderDAO custOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_ORDER);

    public  boolean remove(String customerId) throws SQLException, ClassNotFoundException {
        return customerOrderDetailsDAO.remove(customerId);
    }
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return customerOrderDetailsDAO.getAllId();
    }
    public CustOrderTmDTO getCustOrderData(String id) throws SQLException, ClassNotFoundException {
        return quaryDAO.getCustOrderData(id);
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return custOrderDAO.getSearchIds(id) ;
    }
    public ArrayList<String> setcmbcustId() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllId();
    }
    public ArrayList<String> setCmbModelID() throws SQLException, ClassNotFoundException {
        return carDAO.getAllId();
    }
    public boolean placeOrder(CustOrderDTO custOrderDTO, CustomerOrderDetailsDTO details) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (custOrderDAO.save(custOrderDTO)){

                if (custOrderDAO.addCustomerOrderDetail(details)){

                    if (carDAO.CustomerOrderupdateData(details)){

                        connection.commit();
                    }else {
                        connection.rollback();
                        return false;
                    }
                }else {
                    connection.rollback();
                    return false;
                }

            }else {
                connection.rollback();
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally{
            connection.setAutoCommit(true);
        }
        return true;
    }
}
