package lk.ijse.motorComplex.dto;

import lombok.ToString;

@ToString
public class AttendanceDTO {
    private String employee_id;
    private String first_Name;
    private String last_Name;
    private String time;
    private String date;

    public AttendanceDTO(String employee_id, String first_Name, String last_Name, String time, String date){
        this.employee_id=employee_id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.time = time;
        this.date = date;
    }

    public AttendanceDTO(){}


    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
