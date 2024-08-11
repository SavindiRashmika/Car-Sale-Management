package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.CustomerBO;
import lk.ijse.motorComplex.dao.DAOFactory;
import lk.ijse.motorComplex.dao.custom.CustomerDAO;
import lk.ijse.motorComplex.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public  boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
    return customerDAO.save(customerDTO);
}
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllId();
    }
    public  CustomerDTO getData(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.getData(id);
    }

    public  boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(customerDTO);
    }

    public  boolean remove(String cust_id) throws SQLException, ClassNotFoundException {
        return customerDAO.remove(cust_id);
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.getSearchIds(id) ;
    }

}
