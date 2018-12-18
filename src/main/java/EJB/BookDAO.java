package EJB;

import data.Book;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class BookDAO {

    @PersistenceContext
    private EntityManager manager;


    @Transactional
    public void persist(Book book){
        System.out.println("inside BookDao method persist book id = " + book.getId());
        System.out.println(" book title = " + book.getTitle());
        System.out.println(" book des = " + book.getDescription());
        System.out.println("manager = " + manager.toString());
        manager.persist(book);
    }


    @Transactional
    public void update(Book book) {
        System.out.println("inside update book id ="+ book.getId() + "title = " + book.getTitle() + " description = " + book.getDescription());
        manager.merge(book);
    }

    @Transactional
    public void remove(Book book){
        System.out.println("inside remove book id ="+ book.getId() + "title = " + book.getTitle() + " description = " + book.getDescription());
        manager.remove(this.find(book.getId()));
    }

    @Transactional
    public List<Book> listAll() {
        return manager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Transactional
    public Book find(Integer id){
        try {
            return manager
                    .createQuery("select b from Book b where b.id = :id", Book.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
