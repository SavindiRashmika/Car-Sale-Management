package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.CustomerOrderDetailsDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.CustOrderTmDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDetailsDAOImpl implements CustomerOrderDetailsDAO {
    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT customer_order_id FROM customer_order_details ORDER BY LENGTH(customer_order_id),customer_order_id");
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
    public CustOrderTmDTO getData(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
//Inner Join
//    public  CustOrderTmDTO getData(String id) throws SQLException, ClassNotFoundException {
//        CustOrderTmDTO cusOrderTmDto = new CustOrderTmDTO();
//        ResultSet set = SQLUtil.crudUtil("SELECT d.model_id, d.customer_order_id ,o.customer_order_date,d.price,d.quantity ,o.payment FROM customer_order_details d INNER JOIN customer_order o ON d.customer_order_id = o.customer_order_id where o.customer_order_id=?",id);
//        if (set.next()){
//            cusOrderTmDto.setModel_id(set.getString(1));
//            cusOrderTmDto.setCustomer_order_id(set.getString(2));
//            cusOrderTmDto.setCustomer_order_date(set.getString(3));
//            cusOrderTmDto.setPrice(Double.parseDouble(set.getString(4)));
//            cusOrderTmDto.setQuantity(set.getInt(5));
//            cusOrderTmDto.setPayment(Double.parseDouble(set.getString(6)));
//        }
//        return cusOrderTmDto;
//    }

    @Override
    public boolean save(CustOrderTmDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  boolean update(CustOrderTmDTO custOrderTmDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("UPDATE customer_order_details SET customer_order_id=?,model_id=?,quantity=?,price=? WHERE model_id=?",
                custOrderTmDto.getModel_id(),
                custOrderTmDto.getCustomer_order_id(),
                custOrderTmDto.getCustomer_order_date(),
                custOrderTmDto.getPrice(),
                custOrderTmDto.getQuantity()

        );
    }
    public  boolean remove(String customerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM customer_order WHERE customer_id=?",customerId);
    }
}
