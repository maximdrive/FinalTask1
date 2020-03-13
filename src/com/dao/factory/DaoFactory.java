package com.dao.factory;

import com.dao.BookDao;
import com.dao.UserDao;
import com.dao.handler.Handler;
import com.dao.impl.SqlBookDao;
import com.dao.impl.SqlUserDao;

public final class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    Handler handler = new Handler();

    private final BookDao sqlBookImpl = new SqlBookDao();
    private final UserDao sqlUserImpl = new SqlUserDao();

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance;
    }
    public BookDao getBookDao(){
        return sqlBookImpl;
    }
    public UserDao getUserDao(){
        return sqlUserImpl;
    }


}
