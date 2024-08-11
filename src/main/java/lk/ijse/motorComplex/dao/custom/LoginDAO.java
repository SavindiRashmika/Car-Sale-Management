package lk.ijse.motorComplex.dao.custom;

import lk.ijse.motorComplex.dao.CrudDAO;
import lk.ijse.motorComplex.dto.LoginDTO;

import java.sql.SQLException;

public interface LoginDAO extends CrudDAO<LoginDTO,String> {
//    public  boolean login(LoginDTO loginDTO) throws SQLException, ClassNotFoundException ;
      LoginDTO get(String user_name, String password) throws SQLException, ClassNotFoundException;
      String check(LoginDTO loginDTO) throws SQLException, ClassNotFoundException;
}
