package lk.ijse.motorComplex.dao.custom;

import lk.ijse.motorComplex.dao.CrudDAO;
import lk.ijse.motorComplex.dto.CarDTO;
import lk.ijse.motorComplex.dto.CustomerOrderDetailsDTO;
import lk.ijse.motorComplex.dto.SupplierOrderDetailsDTO;

import java.sql.SQLException;

public interface CarDAO extends CrudDAO<CarDTO,String> {
      boolean CustomerOrderupdateData(CustomerOrderDetailsDTO details) throws SQLException, ClassNotFoundException;
      boolean updateData(SupplierOrderDetailsDTO details) throws SQLException, ClassNotFoundException;
      String getCarCount() throws SQLException, ClassNotFoundException;
      boolean exist(String text) throws SQLException, ClassNotFoundException;
      boolean isCarAddExist(String text) throws SQLException, ClassNotFoundException;

      //    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;
      //    public  boolean addCar(CarDTO carDTO) throws SQLException, ClassNotFoundException ;
//    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
//    public  CarDTO get(String id) throws SQLException, ClassNotFoundException ;
//    public  boolean update(CarDTO carDTO) throws SQLException, ClassNotFoundException ;
//    public  boolean remove(String Car_Id) throws SQLException, ClassNotFoundException;
}
