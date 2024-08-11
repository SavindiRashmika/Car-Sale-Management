package lk.ijse.motorComplex.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupplierEntity {
    private String supplier_id;
    private String supplier_Fname;
    private String supplier_Lname;
    private String street;
    private String city;
    private String lane;
    private String contact;
}
