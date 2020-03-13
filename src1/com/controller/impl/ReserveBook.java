package com.controller.impl;

import com.Main;
import com.controller.command.Command;
import com.servise.BookService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;


public class ReserveBook implements Command {
    @Override
    public String execute(String request) {
        try {

            String data = request.split(" ", 2)[1];
            int idBook = Integer.parseInt(data.split("!!")[0]);
            String responce;
            int idUser = Integer.parseInt(request.split("!!")[1]);

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            BookService bookService = serviceFactoryObj.getBookService();
            try {
                bookService.reserveBook(idBook, idUser);
                responce = "Success";
            } catch (ServiceException e) {
                e.printStackTrace();
                responce = e.getMessage();
            }
            return responce;
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
