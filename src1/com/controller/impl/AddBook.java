package com.controller.impl;

import com.Main;
import com.beans.Book;
import com.controller.command.Command;
import com.controller.exception.ParseException;
import com.servise.BookService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

import java.util.ArrayList;

public class AddBook implements Command {
    @Override
    public String execute(String request) {

            String data = request.split(" ", 2)[1];
            String[] parsedData = data.split("!!");
            String responce;

            Book book = new Book();
            book.setName(parsedData[0]);
            book.setAuthor(parsedData[1]);
            book.setIdStyle(Integer.parseInt(parsedData[2]));

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            BookService bookService = serviceFactoryObj.getBookService();
            try {
                bookService.addBook(book);
                responce = "Success";
            } catch (ServiceException e) {
                responce = "Wrong Book info";
            }
            return responce;

    }

    public void saf(){}
}
