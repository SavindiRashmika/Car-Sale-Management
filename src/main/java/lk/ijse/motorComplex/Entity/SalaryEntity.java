package lk.ijse.motorComplex.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SalaryEntity {
    private String employee_id;
    private String salary_id;
    private String date;
    private double salary;
    private int employee_attendance_count;
}
