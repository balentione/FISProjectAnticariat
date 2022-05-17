package org.loose.fis.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

public class BooksWithOffer {
    @Id
    private NitriteId id;
    private String username;
    private String customer_username;
    private String category;
    private String title;
    private String author;
    private String number_pag;
    private String condition;

    public BooksWithOffer(String username, String customer_username, String category, String title, String author, String number_pag, String condition) {
        this.username=username;
        this.customer_username=customer_username;
        this.category=category;
        this.title=title;
        this.author=author;
        this.number_pag=number_pag;
        this.condition=condition;
    }

    public BooksWithOffer(){
    }

    public NitriteId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNumber_pag() {
        return number_pag;
    }

    public void setNumber_pag(String number_pag) {
        this.number_pag = number_pag;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
