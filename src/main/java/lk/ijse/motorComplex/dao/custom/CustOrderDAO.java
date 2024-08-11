package lk.ijse.motorComplex.dao.custom;

import lk.ijse.motorComplex.dao.CrudDAO;
import lk.ijse.motorComplex.dto.CustOrderDTO;
import lk.ijse.motorComplex.dto.CustomerOrderDetailsDTO;

import java.sql.SQLException;

public interface CustOrderDAO extends CrudDAO<CustOrderDTO,String> {
      boolean addCustomerOrderDetail(CustomerOrderDetailsDTO details) throws SQLException, ClassNotFoundException;
      String getCustOrderCount() throws SQLException, ClassNotFoundException;
      String getCustPaymentCount() throws SQLException, ClassNotFoundException;
      String getCustomerOrder(int i) throws SQLException, ClassNotFoundException;

      //    public  boolean addCustOrder(CustOrderDTO custOrderDTO) throws SQLException, ClassNotFoundException;
//    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
//    public  CustOrderDTO get(String id) throws SQLException, ClassNotFoundException ;
//    public  boolean update(CustOrderDTO custOrderDTO) throws SQLException, ClassNotFoundException;
//    public  boolean remove(String customerId) throws SQLException, ClassNotFoundException;
      //    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;

}
