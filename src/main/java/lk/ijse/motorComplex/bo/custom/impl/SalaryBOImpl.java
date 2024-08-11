package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.SalaryBO;
import lk.ijse.motorComplex.dao.DAOFactory;
import lk.ijse.motorComplex.dao.custom.AttendanceDAO;
import lk.ijse.motorComplex.dao.custom.EmployeeDAO;
import lk.ijse.motorComplex.dao.custom.SalaryDAO;
import lk.ijse.motorComplex.dto.EmployeeDTO;
import lk.ijse.motorComplex.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    AttendanceDAO attendanceDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
    EmployeeDAO employeeDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    SalaryDAO salaryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SALARY);

    public  boolean save(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(salaryDTO);
    }
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return salaryDAO.getAllId();
    }
    public SalaryDTO getData(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.getData(id);
    }
    public  boolean remove(String Salary_id) throws SQLException, ClassNotFoundException {
        return salaryDAO.remove(Salary_id);
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.getSearchIds(id) ;
    }
    public ArrayList<String> getAllIdEmp() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllId();
    }
    public EmployeeDTO getDataEmp(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.getData(id);
    }
    public  String getEmpCount(String id) throws SQLException, ClassNotFoundException {
        return attendanceDAO.getEmpCount(id);
    }
}
