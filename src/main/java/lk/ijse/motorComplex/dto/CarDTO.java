package lk.ijse.motorComplex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarDTO {
    private String model_id;
    private String model_name;
    private String quantity;
    private String model_color;
    private String catagory;
    private String price;

}