package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;
import lk.ijse.motorComplex.dto.OrderTmDTO;
import lk.ijse.motorComplex.dto.SupOrderDTO;
import lk.ijse.motorComplex.dto.SupplierOrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupOrderBO extends SuperBO {
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;
    public  boolean remove(String customerId) throws SQLException, ClassNotFoundException ;
    public OrderTmDTO getSupOrderData(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllIdCar() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllIdSup() throws SQLException, ClassNotFoundException;
    public  boolean placeOrder(SupOrderDTO supOrderDTO, SupplierOrderDetailsDTO details) ;
}
