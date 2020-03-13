package com.controller.impl;

import com.Main;
import com.controller.command.Command;
import com.view.StartProcess;

public class SignOut implements Command {
    @Override
    public String execute(String request) {
        return "-1!!Signed Out";
    }
}
