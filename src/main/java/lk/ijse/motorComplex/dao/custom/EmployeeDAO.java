package lk.ijse.motorComplex.dao.custom;

import lk.ijse.motorComplex.dao.CrudDAO;
import lk.ijse.motorComplex.dto.EmployeeDTO;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<EmployeeDTO,String> {
//    public  boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
//    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
//    public  EmployeeDTO get(String id) throws SQLException, ClassNotFoundException;
//    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;
//    public  boolean remove(String emp_id) throws SQLException, ClassNotFoundException;
//    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;
      String getAllEmp() throws SQLException, ClassNotFoundException ;
}
