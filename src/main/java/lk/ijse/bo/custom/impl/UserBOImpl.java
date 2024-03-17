package lk.ijse.bo.custom.impl;

import jakarta.persistence.NoResultException;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.User);

    @Override
    public UserDTO findCredential(String text) throws NoResultException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        userDAO.setSession(session);
        User user = userDAO.findByUsername(text);
        if (user != null) {
            session.close();
            return user.toDTO();
        } else {
            session.close();
            return null;
        }
    }

    @Override
    public Boolean saveUser(UserDTO userDTO) {
        Session session =SessionFactoryConfig.getInstance().getSession();
        userDAO.setSession(session);
        Transaction transaction = session.beginTransaction();
        try{
            Long save = userDAO.save(userDTO.toEntity());
            transaction.commit();
            session.close();
            if (save!=null)return true;
            else return false;
        }catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public UserDTO getUser(Long userID) {
        Session session =SessionFactoryConfig.getInstance().getSession();
        userDAO.setSession(session);
        User user = userDAO.get(userID);
        session.close();
        return user.toDTO();
    }

    @Override
    public void deleteUser(Long userId) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        userDAO.setSession(session);
        Transaction transaction = session.beginTransaction();
        User user = userDAO.get(userId);
        userDAO.delete(user);
        transaction.commit();
        session.close();
    }


}
