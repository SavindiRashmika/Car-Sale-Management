package lk.ijse.motorComplex.dto;

import lombok.ToString;

@ToString
public class SupplierDTO {

    private String supplier_id;
    private String supplier_Fname;
    private String supplier_Lname;
    private String street;
    private String city;
    private String lane;
    private String contact;

    public SupplierDTO(String supplier_id, String supplier_Fname, String supplier_Lname, String street, String city, String lane, String contact) {
        this.supplier_id = supplier_id;
        this.supplier_Fname = supplier_Fname;
        this.supplier_Lname = supplier_Lname;
        this.street = street;
        this.city = city;
        this.lane = lane;
        this.contact = contact;
    }

    public SupplierDTO() {
    }

    public String getSupplier_id() { return supplier_id; }

    public void setSupplier_id(String supplier_id) { this.supplier_id = supplier_id; }

    public String getSupplier_Fname() { return supplier_Fname; }

    public void setSupplier_Fname(String supplier_Fname) { this.supplier_Fname = supplier_Fname; }

    public String getSupplier_Lname() { return supplier_Lname; }

    public void setSupplier_Lname(String supplier_Lname) { this.supplier_Lname = supplier_Lname; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getLane() { return lane; }

    public void setLane(String lane) { this.lane = lane; }

    public String getContact() { return contact; }

    public void setContact(String contact) { this.contact = contact; }
}
