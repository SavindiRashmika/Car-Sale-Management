package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.SupOrderDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.SupOrderDTO;
import lk.ijse.motorComplex.dto.SupplierOrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupOrderDAOImpl implements SupOrderDAO {
    public  boolean save(SupOrderDTO supOrderDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO supplier_order VALUES(?,?,?,?,?)",
                supOrderDTO.getSupplier_id(),
                supOrderDTO.getSupplier_order_id(),
                supOrderDTO.getSupplier_order_date(),
                supOrderDTO.getSupplier_order_time(),
                supOrderDTO.getPayment()
        );
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT supplier_id FROM supplier_order ORDER BY LENGTH(supplier_id),supplier_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
    public  SupOrderDTO getData(String id) throws SQLException, ClassNotFoundException {
        SupOrderDTO supOrderDTO =new SupOrderDTO();
        ResultSet set = SQLUtil.crudUtil("SELECT * from supplier_order where supplier_id=?", id);
        if (set.next()){
            supOrderDTO.setSupplier_id(set.getString(1));
            supOrderDTO.setSupplier_order_id(set.getString(2));
            supOrderDTO.setSupplier_order_date(set.getString(3));
            supOrderDTO.setSupplier_order_time(set.getString(4));
            supOrderDTO.setPayment(set.getString(5));

        }
        return supOrderDTO;
    }

    public  boolean update(SupOrderDTO supOrderDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("UPDATE supplier_order SET supplier_order_time=?,supplier_order_date=?,supplier_order_id=? WHERE supplier_id=?",
                supOrderDTO.getSupplier_id(),
                supOrderDTO.getSupplier_order_id(),
                supOrderDTO.getSupplier_order_date(),
                supOrderDTO.getPayment()

        );
    }
    public  boolean remove(String supplier_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM supplier_order WHERE supplier_id=?",supplier_id);
    }

    public  boolean addSupplierOrderDetails(SupplierOrderDetailsDTO details) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO supplier_order_details VALUES(?,?,?,?)",
                details.getSupplier_order_id(),
                details.getModel_id(),
                details.getQuantity(),
                details.getPrice()
                );
    }
//    public  boolean placeOrder(SupOrderDTO supOrderDTO, SupplierOrderDetailsDTO details) {
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            if (SupOrderDAOImpl.addSupOrder(supOrderDTO)) {
//                if (SupOrderDAOImpl.addSupplierOrderDetails(details)) {
//                    if (CarDAOImpl.updateData(details)) {
//                        connection.commit();
//                        return true;
//                    } else {
//                        connection.rollback();
//                        return false;
//                    }
//                } else {
//                    connection.rollback();
//                    return false;
//                }
//            }else {
//                connection.rollback();
//                return false;
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return true;
//    }


    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtil.crudUtil("SELECT supplier_id from supplier_order where supplier_id LIKE ? or supplier_order_id LIKE ? or supplier_order_date LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }
}
