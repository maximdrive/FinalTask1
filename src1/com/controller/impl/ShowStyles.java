package com.controller.impl;

import com.Main;
import com.controller.command.Command;
import com.servise.BookService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

public class ShowStyles implements Command {
    @Override
    public String execute(String request) {
        try {

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            BookService bookService = serviceFactoryObj.getBookService();
            String responce;

            try {
                responce = bookService.showStyles();
            } catch (ServiceException e) {
                responce = e.getMessage();
            }
            return responce;
        }catch (Exception e){
            return e.getLocalizedMessage();
        }
    }
}
