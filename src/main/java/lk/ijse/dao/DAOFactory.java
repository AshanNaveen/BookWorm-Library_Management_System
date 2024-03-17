package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory==null ? daoFactory=new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        Book,Branch,BorrowBook,Query,User
    }

    public SuperDAO getDAO(DAOTypes type){
        switch (type){
            case User :return new UserDAOImpl();
            case Branch:return new BranchDAOImpl();
            case Book:return new BookDAOImpl();
            case BorrowBook:return new BorrowDetailsDAOImpl();
            case Query:return new QueryDAOImpl();
            default:return null;
        }
    }
}
