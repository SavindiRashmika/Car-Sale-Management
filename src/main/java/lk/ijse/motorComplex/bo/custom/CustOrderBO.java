package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;
import lk.ijse.motorComplex.dto.CustOrderDTO;
import lk.ijse.motorComplex.dto.CustOrderTmDTO;
import lk.ijse.motorComplex.dto.CustomerOrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustOrderBO extends SuperBO {
    boolean remove(String customerId) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;

    CustOrderTmDTO getCustOrderData(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> setcmbcustId() throws SQLException, ClassNotFoundException;

    ArrayList<String> setCmbModelID() throws SQLException, ClassNotFoundException;

    boolean placeOrder(CustOrderDTO custOrderDTO, CustomerOrderDetailsDTO details) throws SQLException;
}
