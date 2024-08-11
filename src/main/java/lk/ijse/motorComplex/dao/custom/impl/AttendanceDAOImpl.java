package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.AttendanceDAO;
import lk.ijse.motorComplex.dto.AttendanceDTO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.util.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    public  boolean addAttendance(String text) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO employee_attendance VALUES(?,?,?)",
                text,
                DateTimeUtil.timeNow(),
                DateTimeUtil.dateNow()
        );
    }

    public  boolean isEmployeeExist(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT * FROM employee WHERE employee_id=?",text);
        if (set.next()){
            return true;
        }
        return false;
    }

    public  boolean isEmployeeTodayExist(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT * FROM employee_attendance WHERE employee_id=? AND date = ?",text,DateTimeUtil.dateNow());
        if (set.next()){
            return false;
        }
        return true;
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT employee_id FROM employee_attendance ORDER BY LENGTH(employee_id),employee_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    //Inner Join
//    public AttendanceDTO getData(String id) throws SQLException, ClassNotFoundException {
//        AttendanceDTO attendanceDTO = new AttendanceDTO();
//        ResultSet set = SQLUtil.crudUtil("SELECT a.employee_id,a.time,a.date,e.first_name,e.last_name FROM employee_attendance a INNER JOIN employee e ON a.employee_id = e.employee_id WHERE a.employee_id=?",id);
//        while (set.next()) {
//            attendanceDTO.setEmployee_id(set.getString(1));
//            attendanceDTO.setTime(set.getString(2));
//            attendanceDTO.setDate(set.getString(3));
//            attendanceDTO.setFirst_Name(set.getString(4));
//            attendanceDTO.setLast_Name(set.getString(5));
//
//        }
//        return attendanceDTO;
//    }

    @Override
    public boolean save(AttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(AttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  boolean remove(String employee_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM employee_attendance WHERE employee_id=?",employee_id);
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtil.crudUtil("SELECT employee_id from Employee where employee_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    @Override
    public AttendanceDTO getData(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  String getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtil.crudUtil("SELECT COUNT(employee_id) FROM employee_attendance");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

    public  String getEmpCount(String id) throws SQLException, ClassNotFoundException {
        String date=DateTimeUtil.dateNow();
        String[] arDate = date.split("-");
        ResultSet  set=  SQLUtil.crudUtil("SELECT COUNT(employee_id) FROM employee_attendance WHERE employee_id=? AND date LIKE ?",id,arDate[0]+"-"+arDate[1]+"%");
        if (set.next()){
            return set.getString(1);
        }
        return null;
    }
}