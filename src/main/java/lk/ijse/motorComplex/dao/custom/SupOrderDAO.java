package lk.ijse.motorComplex.dao.custom;

import lk.ijse.motorComplex.dao.CrudDAO;
import lk.ijse.motorComplex.dto.SupOrderDTO;
import lk.ijse.motorComplex.dto.SupplierOrderDetailsDTO;

import java.sql.SQLException;

public interface SupOrderDAO extends CrudDAO<SupOrderDTO,String> {
    boolean addSupplierOrderDetails(SupplierOrderDetailsDTO details) throws SQLException, ClassNotFoundException;

//    public  boolean addSupOrder(SupOrderDTO supOrderDTO) throws SQLException, ClassNotFoundException ;
//    public  ArrayList<String> getAll() throws SQLException, ClassNotFoundException ;
//    public  SupOrderDTO get(String id) throws SQLException, ClassNotFoundException;
//    public  boolean update(SupOrderDTO supOrderDTO) throws SQLException, ClassNotFoundException ;
//    public  boolean remove(String supplier_id) throws SQLException, ClassNotFoundException;
//    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;
}
