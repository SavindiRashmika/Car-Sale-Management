package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.SupplierDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    public  boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO supplier VALUES(?,?,?,?,?,?,?)",
                supplierDTO.getSupplier_id(),
                supplierDTO.getSupplier_Fname(),
                supplierDTO.getSupplier_Lname(),
                supplierDTO.getStreet(),
                supplierDTO.getCity(),
                supplierDTO.getLane(),
                supplierDTO.getContact()
        );
    }
    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT supplier_id FROM supplier ORDER BY LENGTH(supplier_id),supplier_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
    public  SupplierDTO getData(String id) throws SQLException, ClassNotFoundException {
        SupplierDTO supplierDTO =new SupplierDTO();
        ResultSet set = SQLUtil.crudUtil("SELECT * from supplier where supplier_id=?", id);
        if (set.next()){
            supplierDTO.setSupplier_id(set.getString(1));
            supplierDTO.setSupplier_Fname(set.getString(2));
            supplierDTO.setSupplier_Lname(set.getString(3));
            supplierDTO.setStreet(set.getString(4));
            supplierDTO.setCity(set.getString(5));
            supplierDTO.setLane(set.getString(6));
            supplierDTO.setContact(set.getString(7));
        }
        return supplierDTO;
    }

    public  boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("UPDATE supplier SET street=?,city=?,lane=?,first_name=?,last_name=?,contact_number=? WHERE supplier_id=?",
                supplierDTO.getStreet(),
                supplierDTO.getCity(),
                supplierDTO.getLane(),
                supplierDTO.getSupplier_Fname(),
                supplierDTO.getSupplier_Lname(),
                supplierDTO.getContact(),
                supplierDTO.getSupplier_id()
        );
    }
    public  boolean remove(String sup_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM supplier WHERE supplier_id=?",sup_id);
    }


    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtil.crudUtil("SELECT supplier_id from Supplier where supplier_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }
}
