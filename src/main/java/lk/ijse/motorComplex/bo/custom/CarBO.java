package lk.ijse.motorComplex.bo.custom;

import lk.ijse.motorComplex.bo.SuperBO;
import lk.ijse.motorComplex.dto.CarDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CarBO extends SuperBO {
      boolean saveCar(CarDTO carDTO) throws SQLException, ClassNotFoundException;
      ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
      CarDTO getData(String id) throws SQLException, ClassNotFoundException;
      boolean updateCar(CarDTO carDTO) throws SQLException, ClassNotFoundException ;
      boolean removeCar(String Car_Id) throws SQLException, ClassNotFoundException ;
  //    boolean CustomerOrderupdateData(CustomerOrderDetailsDTO details) throws SQLException, ClassNotFoundException;
  //    boolean updateData(SupplierOrderDetailsDTO details) throws SQLException, ClassNotFoundException ;
      ArrayList<String> getSearchIdsCar(String id) throws SQLException, ClassNotFoundException ;
//      String getCarCount() throws SQLException, ClassNotFoundException ;
//      boolean exist(String text) throws SQLException, ClassNotFoundException ;
//      boolean todayExist(String text) throws SQLException, ClassNotFoundException ;
}
