package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.EmployeeBO;
import lk.ijse.motorComplex.dao.DAOFactory;
import lk.ijse.motorComplex.dao.custom.EmployeeDAO;
import lk.ijse.motorComplex.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public  boolean remove(String cust_id) throws SQLException, ClassNotFoundException {
        return employeeDAO.remove(cust_id);
    }
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllId();
    }
    public EmployeeDTO getData(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.getData(id);
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.getSearchIds(id) ;
    }
    public  boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(employeeDTO);
    }
    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(employeeDTO);
    }
}
