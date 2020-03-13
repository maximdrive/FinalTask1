package com.controller.impl;

import com.controller.command.Command;
import com.controller.exception.ParseException;
import com.servise.UserService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

public class SignIn implements Command {
    @Override
    public String execute(String request)  {

            String delimiter = "!!";

            String login;
            String password;

            String responce;
            int id;

            try {
                String data = request.split(" ", 2)[1];
                String[] parseData = data.split(delimiter);
                if (parseData.length < 2) throw new ParseException("Incorrect login or password");
                login = parseData[0];
                password = parseData[1];
                id = Integer.parseInt(parseData[2]);

                if(id>0) return "You've already signed";

                ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
                UserService userService = serviceFactoryObj.getUserService();

                try {
                    id = userService.signIn(login, password);
                    responce = String.valueOf(id) + "!!" + "Welcome";
                } catch (ServiceException serExc) {
                    responce = "-1" + "!!" + serExc.getMessage();
                }
                return responce;
            }catch (ParseException e){
                return "-1" +"!!" + e.getMessage();
            }
    }
}
