package lk.ijse.motorComplex.dto;

import lombok.ToString;

@ToString
public class EmployeeDTO {

    private String employee_id;
    private String employee_Fname;
    private String employee_Lname;
    private String street;
    private String city;
    private String lane;
    private String role;
    private String contact;

    public EmployeeDTO(String employee_id, String employee_Fname, String employee_Lname, String street, String city, String lane, String role, String contact) {
        this.employee_id = employee_id;
        this.employee_Fname = employee_Fname;
        this.employee_Lname = employee_Lname;
        this.street = street;
        this.city = city;
        this.lane = lane;
        this.role = role;
        this.contact = contact;
    }

    public EmployeeDTO() {
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_Fname() {
        return employee_Fname;
    }

    public void setEmployee_Fname(String employee_Fname) {
        this.employee_Fname = employee_Fname;
    }

    public String getEmployee_Lname() {
        return employee_Lname;
    }

    public void setEmployee_Lname(String employee_Lname) {
        this.employee_Lname = employee_Lname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
