package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;
import lk.ijse.motorComplex.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;
    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
    EmployeeDTO getData(String id) throws SQLException, ClassNotFoundException ;
    boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;
    boolean remove(String emp_id) throws SQLException, ClassNotFoundException ;
    ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;
}
