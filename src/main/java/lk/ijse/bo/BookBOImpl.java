package lk.ijse.bo;

import lk.ijse.dao.BookDAOImpl;
import lk.ijse.dao.BooksDAO;
import lk.ijse.dao.QueryDAO;
import lk.ijse.dao.QueryDAOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.ReturnBookDTO;
import lk.ijse.entity.Book;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class BookBOImpl implements BookBO{

    BooksDAO booksDAO = new BookDAOImpl();

    QueryDAO queryDAO = new QueryDAOImpl();
    @Override
    public List<BookDTO> loadAllBooks() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        booksDAO.setSession(session);
        List<Book> all = booksDAO.getAll();
        session.close();
        return all.stream().map(book -> book.toDTO()).toList();
    }

    @Override
    public List<BookDTO> loadAllBooksByUser(Long userID) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        queryDAO.setSession(session);
        List<Book> all = queryDAO.getAllBooksFromUser(userID);
        return all.stream().map(book -> book.toDTO()).collect(Collectors.toList());
    }

    @Override
    public List<ReturnBookDTO> getNotReturnedBooks(Long userID) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        queryDAO.setSession(session);
        List<ReturnBookDTO> notReturnedBooksByUser = queryDAO.getNotReturnedBooksByUser(userID);
        session.close();
        return notReturnedBooksByUser;
    }

    @Override
    public void delete(Long id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        booksDAO.setSession(session);
        Book book = booksDAO.get(id);
        booksDAO.delete(book);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean update(BookDTO bookDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        booksDAO.setSession(session);
        try {
            Transaction transaction = session.beginTransaction();
            Book book = booksDAO.get(bookDTO.getId());
            book.setAuthor(bookDTO.getAuthor());
            book.setGenre(bookDTO.getGenre());
            book.setIsbn(bookDTO.getIsbn());
            book.setName(bookDTO.getName());
            book.setPhotoPath(bookDTO.getPhotoPath());
            booksDAO.update(book);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            return false;
        }
    }
}
