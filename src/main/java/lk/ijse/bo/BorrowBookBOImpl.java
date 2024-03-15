package lk.ijse.bo;

import lk.ijse.dao.*;
import lk.ijse.dto.BorrowBookDTO;
import lk.ijse.embbeded.BorrowId;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowDetails;
import lk.ijse.entity.User;
import lk.ijse.util.DateTimeUtil;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BorrowBookBOImpl implements BorrowBookBO{

    BorrowDetailsDAO borrowDetailsDAO = new BorrowDetailsDAOImpl();
    BooksDAO booksDAO = new BookDAOImpl();

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public void borrowBook(BorrowBookDTO dto) {
        Session session = SessionFactoryConfig.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        booksDAO.setSession(session);
        Book book = booksDAO.get(dto.getBookId());
        book.setAvailable(false);


        userDAO.setSession(session);
        User user = userDAO.get(dto.getUserId());


        BorrowDetails borrowDetails = new BorrowDetails();

        borrowDetails.setBook(book);
        borrowDetails.setUser(user);
        borrowDetails.setDueTimestamp(DateTimeUtil.getDueTimestamp(dto.getBorrowedTimestamp()));
        borrowDetails.setReturned(false);

        book.getBorrowDetails().add(borrowDetails);
        user.getBorrowDetails().add(borrowDetails);

        booksDAO.save(book);
        userDAO.save(user);


        borrowDetailsDAO.setSession(session);
        borrowDetailsDAO.save(borrowDetails);
        transaction.commit();


        session.close();
    }
}
