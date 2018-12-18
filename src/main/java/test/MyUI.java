package test;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;

import data.Book;
import EJB.BookDAO;
import ui.BookForm;
import ui.BookList;
import javax.ejb.EJB;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@CDIUI("")
@Theme("mytheme")
public class MyUI extends UI implements Property.ValueChangeListener{
    @Inject
    BookDAO bookDAO ;

    final VerticalLayout layout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
       // bookDAO = new BookDAO();

       // BookForm bookForm = new BookForm();
        
//        final TextField name = new TextField();
//        name.setCaption("Type your name here:");
//
        initAndRefresh();
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    public void addBook(Book book){
        bookDAO.persist(book);
    }

    public List<Book> listAll(){
        return bookDAO.listAll();
    }

    public void remove(Book book){
        bookDAO.remove(book);
    }

    public void update(Book book){
        bookDAO.update(book);
    }

    public Book find(int id){
        return bookDAO.find(id);
    }

    public void updateAndDelete(Book book){

    }

    public void funCallback(int bookid){
        layout.removeAllComponents();
        layout.addComponent(new BookForm(this,bookid));
    }

    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();
        System.out.println("value changed " + property.toString());
    }

    public void initAndRefresh(){
        layout.removeAllComponents();
        BookList bookList = new BookList(this);
        layout.addComponent(bookList);
        Button button = new Button("new Book");
        button.addClickListener( e -> {
            this.funCallback(-1);
        });
        layout.addComponents( button);
    }
//    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
//    public static class MyUIServlet extends VaadinServlet {
//    }
}
