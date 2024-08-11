package lk.ijse.motorComplex.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerEntity {
    private String customer_Id;
    private String first_name;
    private String last_name;
    private String street;
    private String city;
    private String lane;
    private String contact_number;
}
