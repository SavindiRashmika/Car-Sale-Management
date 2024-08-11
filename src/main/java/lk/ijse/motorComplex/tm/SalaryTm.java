package lk.ijse.motorComplex.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalaryTm {
    private String employee_id;
    private String salary_id;
    private String date;
    private String salary;
    private String employee_attendance_count;


}


