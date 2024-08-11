package lk.ijse.motorComplex.bo.custom.impl;

import lk.ijse.motorComplex.bo.custom.CarBO;
import lk.ijse.motorComplex.dao.DAOFactory;
import lk.ijse.motorComplex.dao.custom.CarDAO;
import lk.ijse.motorComplex.dto.CarDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarBOImpl implements CarBO {
    CarDAO carDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CAR);

    public  boolean removeCar(String Car_Id) throws SQLException, ClassNotFoundException {
        return carDAO.remove(Car_Id);
    }
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return carDAO.getAllId();
    }
    public  CarDTO getData(String id) throws SQLException, ClassNotFoundException {
        return carDAO.getData(id);
    }
    public  ArrayList<String> getSearchIdsCar(String id) throws SQLException, ClassNotFoundException {
        return carDAO.getSearchIds(id) ;
    }
    public  boolean saveCar(CarDTO carDTO) throws SQLException, ClassNotFoundException {
        return carDAO.save(carDTO);
    }
    public  boolean updateCar(CarDTO carDTO) throws SQLException, ClassNotFoundException {
        return carDAO.update(carDTO);
    }







//    public  boolean CustomerOrderupdateData(CustomerOrderDetailsDTO details) throws SQLException, ClassNotFoundException {
//        return carDAO.CustomerOrderupdateData(details);
//    }
//    public  boolean updateData(SupplierOrderDetailsDTO details) throws SQLException, ClassNotFoundException {
//        return carDAO.updateData(details);
//    }
//
//    public  String getCarCount() throws SQLException, ClassNotFoundException {
//        return carDAO.getCarCount();
//    }
//
//    public  boolean exist(String text) throws SQLException, ClassNotFoundException {
//        return carDAO.exist(text);
//    }
//
//    public  boolean todayExist(String text) throws SQLException, ClassNotFoundException {
//        return carDAO.isCarAddExist(text);
//    }
}
