package lk.ijse.motorComplex.bo;

import lk.ijse.motorComplex.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null)? boFactory = new BOFactory() :boFactory;
    }

    public enum BOTypes{
        ATTENDANCE,CAR,CUSTOMER,CUSTOMER_ORDER,CASHIERBORD,EMPLOYEE,
        LOGIN, QUARY,SALARY,SUPPLIER,SUPPLIER_ORDER,DASHBOARD
    }
    public <T extends SuperBO> T getBO(BOFactory.BOTypes res){
        switch (res){
            case ATTENDANCE: return (T) new AttendanceBOImpl();
            case CAR: return (T) new CarBOImpl();
            case CUSTOMER: return (T) new CustomerBOImpl();
            case CUSTOMER_ORDER: return (T) new CustOrderBOImpl();
            case SALARY: return (T) new SalaryBOImpl();
            case EMPLOYEE: return (T) new EmployeeBOImpl();
            case SUPPLIER: return (T) new SupplierBOImpl();
            case SUPPLIER_ORDER: return (T) new SupOrderBOImpl();
            case CASHIERBORD:return (T) new CashierBordBOImpl();
            case DASHBOARD:return (T) new DashbordBOImpl();
            case LOGIN:return (T) new LoginBOImpl();
            default:
                return null;
        }
    }
}
