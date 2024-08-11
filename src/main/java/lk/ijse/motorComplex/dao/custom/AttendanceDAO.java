package lk.ijse.motorComplex.dao.custom;

import lk.ijse.motorComplex.dao.CrudDAO;
import lk.ijse.motorComplex.dto.AttendanceDTO;

import java.sql.SQLException;

public interface AttendanceDAO extends CrudDAO<AttendanceDTO,String> {
    boolean addAttendance(String text) throws SQLException, ClassNotFoundException;
    boolean isEmployeeExist(String text) throws SQLException, ClassNotFoundException;
    boolean isEmployeeTodayExist(String text) throws SQLException, ClassNotFoundException;
    String getEmployeeCount() throws SQLException, ClassNotFoundException;
    String getEmpCount(String id) throws SQLException, ClassNotFoundException;

    //    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
//    public AttendanceDTO getData(String id) throws SQLException, ClassNotFoundException ;
//    public  boolean remove(String employee_id) throws SQLException, ClassNotFoundException ;
//    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;
}
