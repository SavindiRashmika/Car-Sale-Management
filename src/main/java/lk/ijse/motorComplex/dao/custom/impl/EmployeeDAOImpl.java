
package lk.ijse.motorComplex.dao.custom.impl;
import lk.ijse.motorComplex.dao.custom.EmployeeDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public  boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
            return SQLUtil.crudUtil("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?)",
                    employeeDTO.getEmployee_id(),
                    employeeDTO.getStreet(),
                    employeeDTO.getCity(),
                    employeeDTO.getLane(),
                    employeeDTO.getRole(),
                    employeeDTO.getEmployee_Fname(),
                    employeeDTO.getEmployee_Lname(),
                    employeeDTO.getContact()
            );
        }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT employee_id FROM employee ORDER BY LENGTH(employee_id),employee_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public  EmployeeDTO getData(String id) throws SQLException, ClassNotFoundException {
        EmployeeDTO employeeDTO =new EmployeeDTO();
        ResultSet set = SQLUtil.crudUtil("SELECT * from employee where employee_id=?", id);
        if (set.next()){
            employeeDTO.setEmployee_id(set.getString(1));
            employeeDTO.setStreet(set.getString(2));
            employeeDTO.setCity(set.getString(3));
            employeeDTO.setLane(set.getString(4));
            employeeDTO.setRole(set.getString(5));
            employeeDTO.setEmployee_Fname(set.getString(6));
            employeeDTO.setEmployee_Lname(set.getString(7));
            employeeDTO.setContact(set.getString(8));
        }
        return employeeDTO;
    }

    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("UPDATE employee SET street=?,city=?,lane=?,role=?,first_name=?,last_name=?,contact_number=? WHERE employee_id=?",
                employeeDTO.getStreet(),
                employeeDTO.getCity(),
                employeeDTO.getLane(),
                employeeDTO.getRole(),
                employeeDTO.getEmployee_Fname(),
                employeeDTO.getEmployee_Lname(),
                employeeDTO.getContact(),
                employeeDTO.getEmployee_id()
        );
    }
    public  boolean remove(String emp_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM employee WHERE employee_id=?",emp_id);
    }


    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtil.crudUtil("SELECT employee_id from Employee where employee_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }
    public  String getAllEmp() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtil.crudUtil("SELECT COUNT(employee_id) FROM employee");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
}
