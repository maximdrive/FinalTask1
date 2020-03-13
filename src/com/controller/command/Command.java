package com.controller.command;

import com.servise.exception.ServiceException;

import java.util.ArrayList;

public interface Command {
    public String execute(String request);
}
