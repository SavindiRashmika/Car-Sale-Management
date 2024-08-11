package lk.ijse.motorComplex.dto;

import lombok.ToString;

@ToString
public class CustOrderDTO {
    private String customer_id;
    private String customer_order_id;
    private String customer_order_date;
    private String customer_order_time;
    private String payment;

    public CustOrderDTO(String customer_id, String customer_order_id, String customer_order_date, String customer_order_time, String payment) {
        this.customer_id = customer_id;
        this.customer_order_id = customer_order_id;
        this.customer_order_date = customer_order_date;
        this.customer_order_time = customer_order_time;
        this.payment = payment;
    }

    public CustOrderDTO() {
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_order_id() {
        return customer_order_id;
    }

    public void setCustomer_order_id(String customer_order_id) {
        this.customer_order_id = customer_order_id;
    }

    public String getCustomer_order_date() {
        return customer_order_date;
    }

    public void setCustomer_order_date(String customer_order_date) {
        this.customer_order_date = customer_order_date;
    }

    public String getCustomer_order_time() {
        return customer_order_time;
    }

    public void setCustomer_order_time(String customer_order_time) {
        this.customer_order_time = customer_order_time;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
