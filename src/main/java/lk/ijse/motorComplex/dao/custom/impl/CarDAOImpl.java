package lk.ijse.motorComplex.dao.custom.impl;

import lk.ijse.motorComplex.dao.custom.CarDAO;
import lk.ijse.motorComplex.dao.custom.impl.util.SQLUtil;
import lk.ijse.motorComplex.dto.CarDTO;
import lk.ijse.motorComplex.dto.CustomerOrderDetailsDTO;
import lk.ijse.motorComplex.dto.SupplierOrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDAOImpl implements CarDAO {
    public  boolean save(CarDTO carDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO car_model VALUES(?,?,?,?,?,?)",
                carDTO.getModel_id(),
                carDTO.getModel_name(),
                carDTO.getQuantity(),
                carDTO.getModel_color(),
                carDTO.getCatagory(),
                carDTO.getPrice()
        );
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT model_id FROM car_model ORDER BY LENGTH(model_id),model_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
    public  CarDTO getData(String id) throws SQLException, ClassNotFoundException {
        CarDTO carDTO =new CarDTO();
        ResultSet set = SQLUtil.crudUtil("SELECT * from car_model where model_id=?", id);
        while (set.next()){
            carDTO.setModel_id(set.getString(1));
            carDTO.setModel_name(set.getString(2));
            carDTO.setQuantity(set.getString(3));
            carDTO.setModel_color(set.getString(4));
            carDTO.setCatagory(set.getString(5));
            carDTO.setPrice(set.getString(6));
        }
        return carDTO;
    }
    public  boolean update(CarDTO carDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("UPDATE car_model SET model_name=?,quantity=?,model_color=?,category=?,price=? WHERE model_id=?",
                carDTO.getModel_name(),
                carDTO.getQuantity(),
                carDTO.getModel_color(),
                carDTO.getCatagory(),
                carDTO.getPrice(),
                carDTO.getModel_id()
        );
    }
    public  boolean remove(String Car_Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("DELETE FROM car_model WHERE model_id=?",Car_Id);
    }

    public  boolean CustomerOrderupdateData(CustomerOrderDetailsDTO details) throws SQLException, ClassNotFoundException {
        if (SQLUtil.crudUtil("UPDATE car_model SET quantity=quantity-? WHERE model_id=?",
                details.getQuantity(),
                details.getModel_id()
        )){

        }else {
            return false;
        }

        return true;
    }
    public  boolean updateData(SupplierOrderDetailsDTO details) throws SQLException, ClassNotFoundException {
        if (SQLUtil.crudUtil("UPDATE car_model SET quantity=quantity+? WHERE model_id=?",
                details.getQuantity(),
                details.getModel_id()
        )){

        }else {
            return false;
        }

        return true;
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtil.crudUtil("SELECT model_id from car_model where model_id LIKE ? OR model_name LIKE ? ;",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public  String getCarCount() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtil.crudUtil("SELECT COUNT(model_id) FROM car_model");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

    public  boolean exist(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT * FROM car_model WHERE model_id=?",text);
        if (set.next()){
            return true;
        }
        return false;
    }

    public  boolean isCarAddExist(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.crudUtil("SELECT * FROM car_model WHERE model_id=? AND model_name = ?",text,text);
        if (set.next()){
            return false;
        }
        return true;
    }
}
