package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;
import lk.ijse.motorComplex.dto.LoginDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public  String check(LoginDTO loginDTO) throws SQLException, ClassNotFoundException;
}
