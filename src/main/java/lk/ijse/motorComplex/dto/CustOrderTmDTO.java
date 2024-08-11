package lk.ijse.motorComplex.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustOrderTmDTO {
    private String model_id;
    private String customer_order_id;
    private String Customer_order_date;
    private double price;
    private int quantity;
    private double payment;
}
