package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.QuaryDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.AttendanceDTO;
import lk.ijse.motorComplex.dto.CustOrderTmDTO;
import lk.ijse.motorComplex.dto.OrderTmDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuaryDAOImpl implements QuaryDAO {
    public AttendanceDTO getData(String id) throws SQLException, ClassNotFoundException {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        ResultSet set = SQLUtil.crudUtil("SELECT a.employee_id,a.time,a.date,e.first_name,e.last_name FROM employee_attendance a INNER JOIN employee e ON a.employee_id = e.employee_id WHERE a.employee_id=?",id);
        while (set.next()) {
            attendanceDTO.setEmployee_id(set.getString(1));
            attendanceDTO.setTime(set.getString(2));
            attendanceDTO.setDate(set.getString(3));
            attendanceDTO.setFirst_Name(set.getString(4));
            attendanceDTO.setLast_Name(set.getString(5));

        }
        return attendanceDTO;
    }
    public OrderTmDTO getSupOrderData(String id) throws SQLException, ClassNotFoundException {
        OrderTmDTO orderTmDto = new OrderTmDTO();
        ResultSet set = SQLUtil.crudUtil("SELECT d.model_id, d.supplier_order_id ,o.supplier_order_date,d.quantity ,d.price,o.payment FROM supplier_order_details d INNER JOIN supplier_order o ON d.supplier_order_id = o.supplier_order_id where o.supplier_order_id=?",id);
        if (set.next()){
            orderTmDto.setModel_id(set.getString(1));
            orderTmDto.setSupplier_order_id(set.getString(2));
            orderTmDto.setSupplier_order_date(set.getString(3));
            orderTmDto.setQuantity(set.getInt(4));
            orderTmDto.setPrice(Double.parseDouble(set.getString(5)));
            orderTmDto.setPayment(Double.parseDouble(set.getString(6)));
        }
        return orderTmDto;
    }
    public CustOrderTmDTO getCustOrderData(String id) throws SQLException, ClassNotFoundException {
        CustOrderTmDTO cusOrderTmDto = new CustOrderTmDTO();
        ResultSet set = SQLUtil.crudUtil("SELECT d.model_id, d.customer_order_id ,o.customer_order_date,d.price,d.quantity ,o.payment FROM customer_order_details d INNER JOIN customer_order o ON d.customer_order_id = o.customer_order_id where o.customer_order_id=?",id);
        if (set.next()){
            cusOrderTmDto.setModel_id(set.getString(1));
            cusOrderTmDto.setCustomer_order_id(set.getString(2));
            cusOrderTmDto.setCustomer_order_date(set.getString(3));
            cusOrderTmDto.setPrice(Double.parseDouble(set.getString(4)));
            cusOrderTmDto.setQuantity(set.getInt(5));
            cusOrderTmDto.setPayment(Double.parseDouble(set.getString(6)));
        }
        return cusOrderTmDto;
    }
}
