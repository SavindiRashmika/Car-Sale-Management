package lk.ijse.motorComplex.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerOrderDetailsEntity {
    private String customer_order_id;
    private String model_id;
    private String quantity;
    private String price;
}
