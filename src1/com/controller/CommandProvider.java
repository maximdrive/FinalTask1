package com.controller;

import java.awt.color.CMMException;
import java.util.HashMap;
import java.util.Map;

import com.controller.command.*;
import com.controller.impl.Registration;
import com.controller.impl.SignIn;
import com.controller.impl.WrongRequest;
import com.controller.impl.*;


final class CommandProvider {
    private Map<CommandName,Command> repository = new HashMap<>();

     CommandProvider(){
        repository.put(CommandName.SIGN_IN,new SignIn() );
        repository.put(CommandName.REGISTRATION, new Registration() );
        repository.put(CommandName.WRONG_REQUEST,new WrongRequest() );
        repository.put(CommandName.ADD_BOOK,new AddBook() );
        repository.put(CommandName.DELETE_BOOK_BY_ID, new DeleteBookById() );
        repository.put(CommandName.SHOW_BOOKS, new ShowBooks() );
        repository.put(CommandName.SHOW_USERS, new ShowUsers() );
        repository.put(CommandName.SHOW_STYLES, new ShowStyles() );
        repository.put(CommandName.EDIT_BOOK, new EditBook() );
        repository.put(CommandName.RESERVE_BOOK, new ReserveBook());
        repository.put(CommandName.SHOW_MY_BOOKS, new ShowMyBooks() );
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.FIND_BY_ID, new FindById());
    }

    Command getCommand(String name ){
        CommandName commandName = null;
        Command command = null;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }
        catch(Exception e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}

