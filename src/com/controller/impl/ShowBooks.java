package com.controller.impl;

import com.beans.Book;
import com.controller.command.Command;
import com.servise.BookService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

import java.util.ArrayList;

public class ShowBooks implements Command {
    @Override
    public String execute(String request) {
        try {

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            BookService bookService = serviceFactoryObj.getBookService();

            ArrayList<Book> books;
            String responce = "";
            String styleName;
            try {
                books = bookService.showBook();
                for (Book book : books) {
                    responce += book.getIdBook() + "!!" + book.getName() + "!!"
                            + book.getAuthor() + "!!" + bookService.styleById(book.getStyle()) + "\n";
                }
                return responce;
            } catch (ServiceException e) {
                e.printStackTrace();
                responce = e.getMessage();
            }
            return responce;
        }catch (Exception e){
            return e.getLocalizedMessage();
        }
    }


}
