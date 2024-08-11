package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;
import lk.ijse.motorComplex.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException ;
    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
    SupplierDTO getData(String id) throws SQLException, ClassNotFoundException ;
    boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException ;
    boolean remove(String cust_id) throws SQLException, ClassNotFoundException ;
    ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;
}
