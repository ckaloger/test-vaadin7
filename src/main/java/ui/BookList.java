package ui;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import test.MyUI;
import data.Book;

import java.util.List;

import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickEvent;

public class BookList extends Table{
    public BookList(MyUI myUI){
        List<Book> books = myUI.listAll();

        this.addContainerProperty("name",String.class,null);
        this.addContainerProperty("description",String.class,null);
        this.addContainerProperty("delete",Button.class,null);
        this.addContainerProperty("update",Button.class,null);
        this.addItemClickListener(new ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                  myUI.remove(books.get((int)event.getItemId()));
            }
        });

         for (int i=0; i<books.size(); i++){
             Button deleteButton = new Button("click me");
             Button updateButton = new Button("click me");

             deleteButton.setData(new Integer(i));
             updateButton.setData(new Integer(i));

             deleteButton.addClickListener(new Button.ClickListener(){
                 public void buttonClick(ClickEvent event) {
                     Integer iid = (Integer)event.getButton().getData();
                     myUI.remove(books.get(iid));
                     myUI.initAndRefresh();
                 }
             });
             updateButton.addClickListener(new Button.ClickListener(){
                 public void buttonClick(ClickEvent event) {
                     Integer iid = (Integer)event.getButton().getData();
                     myUI.funCallback(books.get(iid).getId());
                 }
             });

             deleteButton.addStyleName("link");
             updateButton.addStyleName("link");

             this.addItem(new Object[]{books.get(i).getTitle(),books.get(i).getDescription(),deleteButton,updateButton},i);
         }
    }
}
