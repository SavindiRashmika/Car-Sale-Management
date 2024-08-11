package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.SalaryDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.SalaryDTO;
import lk.ijse.motorComplex.util.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    public  boolean save(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO carsale.salary (employee_id, salary_id, date, salary, employee_attandance_count) VALUES (?,?,?,?,?)",
                salaryDTO.getEmployee_id(),
                salaryDTO.getSalary_id(),
                salaryDTO.getDate(),
                salaryDTO.getSalary(),
                salaryDTO.getEmployee_attendance_count()
        );
    }

    @Override
    public boolean update(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        String date= DateTimeUtil.dateNow();
        String[] arDate = date.split("-");
        ResultSet set = SQLUtil.crudUtil("SELECT salary_id FROM salary WHERE date LIKE ?",arDate[0]+"-"+arDate[1]+"%");

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public  SalaryDTO getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT * FROM salary where salary_id=?", id);
        SalaryDTO salaryDTO = new SalaryDTO();
      //  System.out.println("Sal Id : "+ id);
        while (set.next()) {
            salaryDTO.setEmployee_id(set.getString(1));
            salaryDTO.setSalary_id(set.getString(2));
            salaryDTO.setDate(set.getString(3));
            salaryDTO.setSalary(Double.parseDouble(set.getString(4)));
            salaryDTO.setEmployee_attendance_count(Integer.parseInt(set.getString(5)));
        }
        return salaryDTO;
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtil.crudUtil("SELECT salary_id from salary where salary_id LIKE ? or employee_id LIKE ? ",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

  //  public double setTotalSalary() {
//        return 0;
//    }

    public  boolean remove(String Salary_id ) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM salary WHERE salary_id=?",Salary_id);
    }
}
