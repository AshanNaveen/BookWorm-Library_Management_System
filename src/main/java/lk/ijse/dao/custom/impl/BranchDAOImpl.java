package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.entity.Branch;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BranchDAOImpl implements BranchDAO {

    Session session;

    @Override
    public Long save(Branch object) {
        return (Long) session.save(object);
    }

    @Override
    public void update(Branch object) {
        session.persist(object);
    }

    @Override
    public Branch get(Long id) {
        Branch branch = session.get(Branch.class, id);
        return branch;
    }

    @Override
    public void delete(Branch object) {
        session.remove(object);
    }

    @Override
    public List<Branch> getAll() {
        String jpql="SELECT B FROM Branch B";
        Query<Branch> query = session.createQuery(jpql, Branch.class);
        List<Branch> list = query.list();
        return list;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}
