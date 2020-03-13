package com.controller.impl;

import com.Main;
import com.controller.command.Command;
import com.servise.BookService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

public class ShowMyBooks implements Command {
    @Override
    public String execute(String request) {
        try {
            String responce;
            int idUser = Integer.parseInt(request.split("!!")[1]);

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            BookService bookService = serviceFactoryObj.getBookService();
            try {
                responce = bookService.showMyBooks(idUser);
                return responce;
            } catch (ServiceException e) {
                e.printStackTrace();
                return responce = e.getMessage();
            }
        }catch (Exception e){
            return e.getLocalizedMessage();
        }
    }
}
