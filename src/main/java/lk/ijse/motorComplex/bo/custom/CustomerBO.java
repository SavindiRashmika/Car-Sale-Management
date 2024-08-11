package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;
import lk.ijse.motorComplex.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
      boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;
      ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
      CustomerDTO getData(String id) throws SQLException, ClassNotFoundException ;
      boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;
      boolean remove(String cust_id) throws SQLException, ClassNotFoundException ;
      ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;
}
