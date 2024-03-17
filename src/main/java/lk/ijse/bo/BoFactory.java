package lk.ijse.bo;

import lk.ijse.bo.custom.impl.BorrowBookBOImpl;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BoFactory {
    public static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getBoFactory(){
        return boFactory  ==null ? boFactory=new BoFactory() : boFactory;
    }

    public enum BoType{
        Book,BorrowBook,Branch,User
    }

    public SuperBO getBo(BoType type){
        switch (type){
            case Book : return new BranchBOImpl();
            case BorrowBook: return new BorrowBookBOImpl();
            case Branch:return new BranchBOImpl();
            case User: return new UserBOImpl();
            default:return null;
        }
    }
}
