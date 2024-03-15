package lk.ijse.dao;

import lk.ijse.entity.BorrowDetails;
import org.hibernate.Session;

public class BorrowDetailsDAOImpl implements BorrowDetailsDAO{

    private Session session;
    @Override
    public Long save(BorrowDetails object) {
        return (Long) session.save(object);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

}
