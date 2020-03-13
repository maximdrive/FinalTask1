package com.controller.impl;

import com.Main;
import com.controller.command.Command;
import com.controller.exception.ParseException;
import com.servise.BookService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

public class DeleteBookById implements Command {
    @Override
    public String execute(String request) {
        try {
            if (request.isEmpty()) throw new ParseException("Null Data");

            String data = request.split(" ", 2)[1].split("!!")[0];
            String responce;

            int id = Integer.parseInt(data);

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            BookService bookService = serviceFactoryObj.getBookService();

            try {
                bookService.deleteBook(id);
                responce = "Succes";
            } catch (ServiceException e) {
                responce = e.getMessage();
            }
            return responce;
        }catch (ParseException e){
            return e.getMessage();
        }

    }
}

