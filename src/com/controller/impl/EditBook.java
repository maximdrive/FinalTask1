package com.controller.impl;

import com.Main;
import com.beans.Book;
import com.controller.command.Command;
import com.controller.exception.ParseException;
import com.servise.BookService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

public class EditBook implements Command {
    @Override
    public String execute(String request) {
        try {
            if (request.isEmpty()) throw new ParseException("Null Data");
            String responce;
            Book book = new Book();

            String data = request.split(" ", 2)[1];
            String[] parsedData = data.split("!!");

            book.setIdBook(Integer.parseInt(parsedData[0]));
            book.setName(parsedData[1]);
            book.setAuthor(parsedData[2]);

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            BookService bookService = serviceFactoryObj.getBookService();
            try {
                book.setIdStyle(bookService.idByStyleName(parsedData[3]));
                bookService.editBook(book);
                return responce = "Success";
            } catch (ServiceException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }catch (ParseException e){
            return e.getMessage();
        }
    }
}
