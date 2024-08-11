package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.CustOrderDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.CustOrderDTO;
import lk.ijse.motorComplex.dto.CustomerOrderDetailsDTO;
import lk.ijse.motorComplex.util.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustOrderDAOImpl implements CustOrderDAO {
    public  boolean save(CustOrderDTO custOrderDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO customer_order VALUES(?,?,?,?,?)",
                custOrderDTO.getCustomer_id(),
                custOrderDTO.getCustomer_order_id(),
                custOrderDTO.getCustomer_order_date(),
                custOrderDTO.getCustomer_order_time(),
                custOrderDTO.getPayment()

        );
    }
    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT customer_id FROM customer_order ORDER BY LENGTH(customer_id),customer_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
    public  CustOrderDTO getData(String id) throws SQLException, ClassNotFoundException {
           CustOrderDTO custOrderDTO = new CustOrderDTO();
    ResultSet set = SQLUtil.crudUtil("SELECT * from customer_order where customer_id=?", id);
        if (set.next()){
            custOrderDTO.setCustomer_id(set.getString(1));
            custOrderDTO.setCustomer_order_id(set.getString(2));
            custOrderDTO.setCustomer_order_date(set.getString(3));
            custOrderDTO.setCustomer_order_time(set.getString(4));
            custOrderDTO.setPayment(set.getString(5));

        }
        return custOrderDTO;
    }

    public  boolean update(CustOrderDTO custOrderDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("UPDATE customer_order SET customer_order_id=?,customer_order_date=?,customer_order_time=?,payment=? WHERE customer_id=?",
                custOrderDTO.getCustomer_order_id(),
                custOrderDTO.getCustomer_order_date(),
                custOrderDTO.getCustomer_order_time(),
                custOrderDTO.getPayment(),
                custOrderDTO.getCustomer_id()
        );
    }
    public  boolean remove(String customerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM customer_order WHERE customer_id=?",customerId);
    }

    public  boolean addCustomerOrderDetail(CustomerOrderDetailsDTO details) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO customer_order_details VALUES(?,?,?,?)",
                details.getCustomer_order_id(),
                details.getModel_id(),
                details.getQuantity(),
                details.getPrice()
        );
    }
//    public  boolean placeOrder(CustOrderDTO custOrderDTO, CustomerOrderDetailsDTO details) throws SQLException {
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            if (CustOrderDAOImpl.addCustOrder(custOrderDTO)){
//
//                if (CustOrderDAOImpl.addCustomerOrderDetail(details)){
//
//                    if (CarDAOImpl.CustomerOrderupdateData(details)){
//
//                        connection.commit();
//                    }else {
//                        connection.rollback();
//                        return false;
//                    }
//                }else {
//                    connection.rollback();
//                    return false;
//                }
//
//            }else {
//                connection.rollback();
//                return false;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }finally{
//            connection.setAutoCommit(true);
//        }
//        return true;
//    }


    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtil.crudUtil("SELECT customer_id from customer_order where customer_id LIKE ? or customer_order_id LIKE ? or customer_order_date LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public  String getCustOrderCount() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtil.crudUtil("SELECT COUNT(customer_order_id) FROM customer_order");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

    public  String getCustPaymentCount() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtil.crudUtil("SELECT SUM(payment) FROM customer_order");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

    public  String getCustomerOrder(int i) throws SQLException, ClassNotFoundException {
        String dateNow = DateTimeUtil.dateNow();
        String[] date = dateNow.split("-");
        String currentDate=null;
        if (String.valueOf(i).length()==1){
            currentDate="0"+i;
        }else {
            currentDate= String.valueOf(i);
        }
        ResultSet set= SQLUtil.crudUtil("SELECT payment FROM customer_order WHERE customer_order_date =?",date[0]+"-"+date[1]+"-"+currentDate);

        while (set.next()){
            return set.getString(1);
        }
        return "0";
    }

}
