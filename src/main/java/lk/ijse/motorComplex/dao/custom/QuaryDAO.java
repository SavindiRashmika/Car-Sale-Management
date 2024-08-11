package lk.ijse.motorComplex.dao.custom;

import lk.ijse.motorComplex.dao.SuperDAO;
import lk.ijse.motorComplex.dto.AttendanceDTO;
import lk.ijse.motorComplex.dto.CustOrderTmDTO;
import lk.ijse.motorComplex.dto.OrderTmDTO;

import java.sql.SQLException;

public interface QuaryDAO extends SuperDAO {
     AttendanceDTO getData(String id) throws SQLException, ClassNotFoundException;
     CustOrderTmDTO getCustOrderData(String id) throws SQLException, ClassNotFoundException;
     OrderTmDTO getSupOrderData(String id) throws SQLException, ClassNotFoundException;
}
