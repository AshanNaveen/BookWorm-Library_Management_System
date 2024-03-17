package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.controller.LoginFormController;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dao.custom.impl.BranchDAOImpl;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Branch;
import lk.ijse.entity.User;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class BranchBOImpl implements BranchBO {

    private BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Branch);
    private UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.User);
    @Override
    public List<BranchDTO> loadAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        branchDAO.setSession(session);
        List<Branch> branches = branchDAO.getAll();
        session.close();
        return branches.stream().map(branch -> new BranchDTO(branch.getId(), branch.getName(), branch.getEmail(),branch.getAddress() )).collect(Collectors.toList());
    }

    @Override
    public void save(BranchDTO branchDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        branchDAO.setSession(session);
        userDAO.setSession(session);
        User user = userDAO.get(LoginFormController.userID);
        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        branch.setEmail(branchDTO.getEmail());
        System.out.println(branch);
        branch.setUser(user);

        Transaction transaction = session.beginTransaction();

        branchDAO.save(branch);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(BranchDTO dto) {
        try {
            Session session = SessionFactoryConfig.getInstance().getSession();
            branchDAO.setSession(session);
            Transaction transaction = session.beginTransaction();
            Branch branch = branchDAO.get(dto.getId());
            branchDAO.delete(branch);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean update(BranchDTO dto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {

            branchDAO.setSession(session);
            Transaction transaction = session.beginTransaction();
            Branch branch = branchDAO.get(dto.getId());
            branch.setName(dto.getName());
            branch.setAddress(dto.getAddress());
            branch.setEmail(dto.getEmail());
            branchDAO.update(branch);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return false;
        }
    }
}
