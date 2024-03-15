package lk.ijse.dao;

import lk.ijse.dto.ReturnBookDTO;
import lk.ijse.entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO{

    private Session session;

    @Override
    public List<Book> getAllBooksFromUser(Long userId) {
        String hql = "SELECT B\n" +
                "FROM Book B\n" +
                "INNER JOIN B.borrowDetails BD\n" +
                "WHERE BD.user.id = :user";
        Query query = session.createQuery(hql);
        query.setParameter("user",userId);
        List results = query.list();
        return results;
    }
    @Override
    public List<ReturnBookDTO> getNotReturnedBooksByUser(Long userId) {
        String hql = "SELECT B.photoPath,B.name,B.id,B.author,BD.timestamp\n" +
                "FROM Book B\n" +
                "INNER JOIN B.borrowDetails BD\n" +
                "WHERE BD.user.id = :user and BD.isReturned=false";
        Query query = session.createQuery(hql);
        query.setParameter("user",userId);
        List<Object[]> results = query.list();
        List<ReturnBookDTO> dtos = new ArrayList<>();
        for (Object[] objects : results) {
            ReturnBookDTO returnBookDTO = new ReturnBookDTO();
            returnBookDTO.setPhotoPath((String) objects[0]);
            returnBookDTO.setBookName((String) objects[1]);
            returnBookDTO.setBookId((Long) objects[2]);
            returnBookDTO.setAuthor((String) objects[3]);
            returnBookDTO.setUserId(userId);
            returnBookDTO.setBorrowedTimestamp((Timestamp) objects[4]);

            dtos.add(returnBookDTO);
        }
        return dtos;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}
