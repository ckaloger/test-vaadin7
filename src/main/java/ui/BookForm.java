package ui;

import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;
import test.MyUI;
import data.Book;

public class BookForm extends Form implements ClickListener {
    private Book book = null;

    public BookForm(MyUI myUI,int bookid){
        if (bookid==-1)
            book = new Book();
        else
            book = myUI.find(bookid);

        FormLayout formLayout = new FormLayout();

        TextField bookName = new TextField();
        bookName.setCaption("book name");
        TextField bookDescription = new TextField();
        bookDescription.setCaption("book description");

        Button saveButton = new Button("save");
        saveButton.addClickListener( e -> {
            //book.setId(1);
            book.setTitle(bookName.getValue());
            book.setDescription(bookDescription.getValue());
            if (bookid==-1)
                myUI.addBook(book);
            else
                myUI.update(book);
            //System.out.println("clicked" + e.toString());
            myUI.initAndRefresh();
        });

        formLayout.addComponents(bookName,bookDescription,saveButton);
        setLayout(formLayout);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        System.out.println("clicked" + clickEvent.toString());
    }
}
