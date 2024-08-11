package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;
import lk.ijse.motorComplex.dto.EmployeeDTO;
import lk.ijse.motorComplex.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    public  boolean save(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
    public SalaryDTO getData(String id) throws SQLException, ClassNotFoundException;
    public  boolean remove(String Salary_id) throws SQLException, ClassNotFoundException ;
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllIdEmp() throws SQLException, ClassNotFoundException;
    public EmployeeDTO getDataEmp(String id) throws SQLException, ClassNotFoundException ;
    public  String getEmpCount(String id) throws SQLException, ClassNotFoundException;
}
