package com.controller;

import com.controller.command.Command;
import com.controller.command.CommandName;
import com.servise.exception.ServiceException;
import com.view.StartProcess;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    private int idUser = -1;


    private final char delimiter = ' ';

    public String executeTask (String request) throws ServiceException {
        String commandName;
        Command execCommand;


        commandName = request.split(" ")[0];
        execCommand = provider.getCommand(commandName);

        if(idUser<0 &&
                !execCommand.getClass().getSimpleName().equals("Registration")&&
                !execCommand.getClass().getSimpleName().equals("SignIn")) return "Sign in, please";

        request += "!!" + idUser;

        String  responce;
        responce = execCommand.execute(request);

        if(execCommand.getClass().getSimpleName().equals("SignIn")
                || execCommand.getClass().getSimpleName().equals("SignOut")) {
            idUser = Integer.parseInt(responce.split("!!")[0]);
            responce = responce.split("!!")[1];
        }

        return responce;
    }



}
