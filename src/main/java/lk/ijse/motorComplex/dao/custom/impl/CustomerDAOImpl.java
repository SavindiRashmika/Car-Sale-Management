package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.CustomerDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    public  boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO customer VALUES(?,?,?,?,?,?,?)",
                customerDTO.getCustomer_Id(),
                customerDTO.getFirst_name(),
                customerDTO.getLast_name(),
                customerDTO.getStreet(),
                customerDTO.getCity(),
                customerDTO.getLane(),
                customerDTO.getContact_number()
        );
    }
    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT customer_id FROM customer ORDER BY LENGTH(customer_id),customer_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
    public  CustomerDTO getData(String id) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = new CustomerDTO();
        ResultSet set = SQLUtil.crudUtil("SELECT * from customer where customer_id=?", id);
        if (set.next()) {
            customerDTO.setCustomer_Id(set.getString(1));
            customerDTO.setFirst_name(set.getString(2));
            customerDTO.setLast_name(set.getString(3));
            customerDTO.setStreet(set.getString(4));
            customerDTO.setCity(set.getString(5));
            customerDTO.setLane(set.getString(6));
            customerDTO.setContact_number(set.getString(7));
        }
        return customerDTO;
    }

    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("UPDATE customer SET first_name=?,last_name=?,street=?,city=?,lane=?,contact_number=? WHERE customer_id=?",
                customerDTO.getFirst_name(),
                customerDTO.getLast_name(),
                customerDTO.getStreet(),
                customerDTO.getCity(),
                customerDTO.getLane(),
                customerDTO.getContact_number(),
                customerDTO.getCustomer_Id()
        );
    }

    public  boolean remove(String cust_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM customer WHERE customer_id=?",cust_id);
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtil.crudUtil("SELECT customer_id from Customer where customer_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }
}
