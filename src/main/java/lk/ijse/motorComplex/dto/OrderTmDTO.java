package lk.ijse.motorComplex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderTmDTO {
    private String model_id;
    private String supplier_order_id;
    private String Supplier_order_date;
    private int quantity;
    private double price;
    private double payment;

}
