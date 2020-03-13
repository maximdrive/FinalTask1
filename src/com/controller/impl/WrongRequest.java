package com.controller.impl;

import com.controller.command.Command;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        return "Wrong Request";
    }
}
