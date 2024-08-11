package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.LoginBO;
import lk.ijse.motorComplex.dao.DAOFactory;
import lk.ijse.motorComplex.dao.custom.LoginDAO;
import lk.ijse.motorComplex.dto.LoginDTO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);
    public  String check(LoginDTO loginDTO) throws SQLException, ClassNotFoundException {
        return loginDAO.check(loginDTO);
    }
}
