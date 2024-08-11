package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;
import lk.ijse.motorComplex.dto.AttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    public  boolean addAttendance(String text) throws SQLException, ClassNotFoundException ;
    public  boolean existEmployee(String text) throws SQLException, ClassNotFoundException ;
    public  boolean todayExistEmployee(String text) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
    public  boolean remove(String employee_id) throws SQLException, ClassNotFoundException;
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;
    public  String getEmployeeCount() throws SQLException, ClassNotFoundException ;
    public AttendanceDTO getData(String id) throws SQLException, ClassNotFoundException ;
    public String getAllIdEmp() throws SQLException, ClassNotFoundException;
}
