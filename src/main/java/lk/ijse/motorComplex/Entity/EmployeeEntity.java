package lk.ijse.motorComplex.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EmployeeEntity {
    private String employee_id;
    private String employee_Fname;
    private String employee_Lname;
    private String street;
    private String city;
    private String lane;
    private String role;
    private String contact;
}
