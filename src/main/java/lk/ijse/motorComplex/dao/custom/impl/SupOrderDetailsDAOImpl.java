package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.SupOrderDetailsDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.OrderTmDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupOrderDetailsDAOImpl implements SupOrderDetailsDAO {
    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT supplier_order_id FROM supplier_order_details ORDER BY LENGTH(supplier_order_id),supplier_order_id");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSearchIds(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderTmDTO getData(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
//    public static boolean update(OrderTmDto supplierOrderDetails) throws SQLException, ClassNotFoundException {
//        return CrudUtil.crudUtil("UPDATE supplier_order_detail SET model_id=?,supplier_order_date=?,supplier_order_id=? WHERE supplier_id=?",
//                supplierOrderDetails.getModel_id(),
//                supplierOrderDetails.getSupplier_order_id(),
//                supplierOrderDetails.getQuantity(),
//                supplierOrderDetails.getPrice()
//
//        );
//    }

    @Override
    public boolean save(OrderTmDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderTmDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  boolean remove(String model_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM supplier_order_details WHERE model_id=?",model_id);
    }

    //Inner Join
//    public  OrderTmDTO getData(String id) throws SQLException, ClassNotFoundException {
//        OrderTmDTO orderTmDto = new OrderTmDTO();
//        ResultSet set = SQLUtil.crudUtil("SELECT d.model_id, d.supplier_order_id ,o.supplier_order_date,d.quantity ,d.price,o.payment FROM supplier_order_details d INNER JOIN supplier_order o ON d.supplier_order_id = o.supplier_order_id where o.supplier_order_id=?",id);
//        if (set.next()){
//            orderTmDto.setModel_id(set.getString(1));
//            orderTmDto.setSupplier_order_id(set.getString(2));
//            orderTmDto.setSupplier_order_date(set.getString(3));
//            orderTmDto.setQuantity(set.getInt(4));
//            orderTmDto.setPrice(Double.parseDouble(set.getString(5)));
//            orderTmDto.setPayment(Double.parseDouble(set.getString(6)));
//        }
//        return orderTmDto;
//    }

}
