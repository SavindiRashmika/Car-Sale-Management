package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.AttendanceBO;
import lk.ijse.motorComplex.dao.DAOFactory;
import lk.ijse.motorComplex.dao.custom.AttendanceDAO;
import lk.ijse.motorComplex.dao.custom.EmployeeDAO;
import lk.ijse.motorComplex.dao.custom.QuaryDAO;
import lk.ijse.motorComplex.dto.AttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {
    QuaryDAO quaryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUARY);
    AttendanceDAO attendanceDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
    EmployeeDAO employeeDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public  boolean addAttendance(String text) throws SQLException, ClassNotFoundException {
        return attendanceDAO.addAttendance(text);
    }

    public  boolean existEmployee(String text) throws SQLException, ClassNotFoundException {
        return attendanceDAO.isEmployeeExist(text);
    }

    public  boolean todayExistEmployee(String text) throws SQLException, ClassNotFoundException {
        return attendanceDAO.isEmployeeTodayExist(text);
    }

    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getAllId();
    }

    public  boolean remove(String employee_id) throws SQLException, ClassNotFoundException {
        return attendanceDAO.remove(employee_id);
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return attendanceDAO.getSearchIds(id) ;
    }

    public  String getEmployeeCount() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getEmployeeCount();
    }
    public AttendanceDTO getData(String id) throws SQLException, ClassNotFoundException {
        return quaryDAO.getData(id);
    }
    public String getAllIdEmp() throws SQLException, ClassNotFoundException {
        return String.valueOf(employeeDAO.getAllId());
    }

}
