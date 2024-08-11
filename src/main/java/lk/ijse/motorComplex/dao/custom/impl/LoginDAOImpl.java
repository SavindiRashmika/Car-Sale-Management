package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.LoginDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.LoginDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAOImpl implements LoginDAO {

    public  boolean save(LoginDTO loginDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO admin VALUES(?,?,?,?)",
                loginDTO.getEmployee_id(),
                loginDTO.getUser_name(),
                loginDTO.getPassword(),
                loginDTO.getRole()
        );
    }

    public  LoginDTO get(String user_name, String password) throws SQLException, ClassNotFoundException {
        LoginDTO loginDTO = new LoginDTO();
        ResultSet set = SQLUtil.crudUtil("SELECT * from admin where user_name=? and password =?", user_name, password);
        if (set.next()) {
            loginDTO.setEmployee_id(set.getString(1));
            loginDTO.setUser_name(set.getString(2));
            loginDTO.setPassword(set.getString(3));
            loginDTO.setRole(set.getString(4));

        }
        return loginDTO;
    }

    public  String check(LoginDTO loginDTO) throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtil.crudUtil("SELECT role FROM admin WHERE user_name=? AND password=? ", loginDTO.getUser_name(), loginDTO.getPassword());
        while (set.next()){
          return  set.getString(1);
        }
        return "XXX";
    }

    @Override
    public boolean update(LoginDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean remove(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getSearchIds(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public LoginDTO getData(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

}
