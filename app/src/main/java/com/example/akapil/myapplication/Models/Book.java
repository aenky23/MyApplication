package com.example.akapil.myapplication.Models;

/**
 * Created by akapil on 3/15/2017.
 */

public class Book {

    private String bookName;
    private int bookPrice;
    private String bookDetails;

    public String getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(String bookDetails) {
        this.bookDetails = bookDetails;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }


}
