package org.loose.fis.sre.exceptions;

public class PublishBookException extends Exception {
    private String category;
    private String title;
    private String author;
    private String number_pag;
    private String condition;

    public PublishBookException(String category, String title, String author, String number_pag, String condition) {
        super(String.format("Please fill in all the informations!"));
        this.category=category;
        this.title=title;
        this.author=author;
        this.number_pag=number_pag;
        this.condition=condition;
    }
}
