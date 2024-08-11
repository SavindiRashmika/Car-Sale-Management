package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.SupOrderBO;
import lk.ijse.motorComplex.dao.DAOFactory;
import lk.ijse.motorComplex.dao.custom.*;
import lk.ijse.motorComplex.db.DBConnection;
import lk.ijse.motorComplex.dto.OrderTmDTO;
import lk.ijse.motorComplex.dto.SupOrderDTO;
import lk.ijse.motorComplex.dto.SupplierOrderDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupOrderBOImpl implements SupOrderBO {
    CarDAO carDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CAR);
    QuaryDAO quaryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUARY);
    SupOrderDetailsDAO supOrderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER_DETAILS);
    SupOrderDAO supOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER);
    SupplierDAO supplierDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return supOrderDAO.getAllId();
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return supOrderDAO.getSearchIds(id) ;
    }
    public  boolean remove(String customerId) throws SQLException, ClassNotFoundException {
        return supOrderDetailsDAO.remove(customerId);
    }
    public OrderTmDTO getSupOrderData(String id) throws SQLException, ClassNotFoundException {
        return quaryDAO.getSupOrderData(id);
    }
    public ArrayList<String> getAllIdCar() throws SQLException, ClassNotFoundException {
        return carDAO.getAllId();
    }
    public ArrayList<String> getAllIdSup() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllId();
    }
    public  boolean placeOrder(SupOrderDTO supOrderDTO, SupplierOrderDetailsDTO details) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (supOrderDAO.save(supOrderDTO)) {
                if (supOrderDAO.addSupplierOrderDetails(details)) {
                    if (carDAO.updateData(details)) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            }else {
                connection.rollback();
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }

}
