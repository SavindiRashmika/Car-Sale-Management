package lk.ijse.motorComplex.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarTm {
    private String Car_Id;
    private String modelName;
    private String quantity;
    private String modelColor;
    private String category;
    private String price;
}