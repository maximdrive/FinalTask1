package com.controller.impl;

import com.Main;
import com.beans.Book;
import com.controller.command.Command;
import com.servise.BookService;
import com.servise.UserService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

import java.util.ArrayList;

public class FindById implements Command {
    @Override
    public String execute(String request) {
        try{
        String data = request.split(" ")[1].split("!!")[0];
        if(data.isEmpty()) return "Wrong Input";
        String responce;
        int id = Integer.parseInt(data);
        ArrayList<Book> books;
        ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
        BookService bookService = serviceFactoryObj.getBookService();

            books = bookService.showBook();

            for(Book book : books){
                if(book.getIdBook() == id) {
                    responce = book.getIdBook() + " " + book.getName() +" "+ book.getAuthor()+ " " + book.getStyle();
                    return responce;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return "No books find";
        }
        return null;
    }
}
