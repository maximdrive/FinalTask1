package com.servise.factory;

import com.servise.BookService;
import com.servise.UserService;
import com.servise.impl.BookServiceImpl;
import com.servise.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final BookService bookService = new BookServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }

    public BookService getBookService(){
        return bookService;
    }
}
