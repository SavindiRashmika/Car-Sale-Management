package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.SupplierBO;
import lk.ijse.motorComplex.dao.DAOFactory;
import lk.ijse.motorComplex.dao.custom.SupplierDAO;
import lk.ijse.motorComplex.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    public  boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(supplierDTO);
    }
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllId();
    }
    public  SupplierDTO getData(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.getData(id);
    }

    public  boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(supplierDTO);
    }

    public  boolean remove(String cust_id) throws SQLException, ClassNotFoundException {
        return supplierDAO.remove(cust_id);
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.getSearchIds(id) ;
    }
}
