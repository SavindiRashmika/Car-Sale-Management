package lk.ijse.motorComplex.dao;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T, ID> extends SuperDAO {
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    boolean remove(ID id) throws SQLException, ClassNotFoundException;
    ArrayList<ID> getAllId() throws SQLException, ClassNotFoundException;
    ArrayList<ID> getSearchIds(ID id) throws SQLException, ClassNotFoundException;
    T getData(ID id) throws SQLException, ClassNotFoundException;
 //   boolean exist(ID text) throws SQLException, ClassNotFoundException;
 //   boolean todayExist(ID text) throws SQLException, ClassNotFoundException;
 //   ID getCount(ID id) throws SQLException, ClassNotFoundException;
  //  boolean save(ID text) throws SQLException, ClassNotFoundException;
  //  ID getCount() throws SQLException, ClassNotFoundException;
}
