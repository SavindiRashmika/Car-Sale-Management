package lk.ijse.motorComplex.dao;

import lk.ijse.motorComplex.dao.custom.impl.*;
import lk.ijse.motorComplex.dao.custom.impl.AttendanceDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null)? daoFactory = new DAOFactory() :daoFactory;
    }

    public enum DAOTypes{
        ATTENDANCE,CAR,CUSTOMER,CUSTOMER_ORDER,CUSTOMER_ORDER_DETAILS,EMPLOYEE,
        LOGIN, QUARY,SALARY,SUPPLIER,SUPPLIER_ORDER,SUPPLIER_ORDER_DETAILS
    }
    public <T extends SuperDAO> T getDAO(DAOTypes res){
        switch (res){
            case ATTENDANCE: return (T) new AttendanceDAOImpl();
            case CAR: return (T) new CarDAOImpl();
            case CUSTOMER: return (T) new CustomerDAOImpl();
            case CUSTOMER_ORDER: return (T) new CustOrderDAOImpl();
            case CUSTOMER_ORDER_DETAILS: return (T) new CustomerOrderDetailsDAOImpl();
            case LOGIN: return (T) new LoginDAOImpl();
            case SALARY: return (T) new SalaryDAOImpl();
            case EMPLOYEE: return (T) new EmployeeDAOImpl();
            case SUPPLIER: return (T) new SupplierDAOImpl();
            case SUPPLIER_ORDER: return (T) new SupOrderDAOImpl();
            case SUPPLIER_ORDER_DETAILS: return (T) new SupOrderDetailsDAOImpl();
            case QUARY: return (T) new QuaryDAOImpl();
            default:
                return null;
        }
    }
}
