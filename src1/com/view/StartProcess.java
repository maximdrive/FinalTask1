package com.view;

import com.beans.User;
import com.controller.Controller;
import com.servise.exception.ServiceException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StartProcess implements Runnable {
    Socket accSocket;

    public StartProcess(Socket accSocket){
        this.accSocket = accSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream is = new ObjectInputStream(accSocket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(accSocket.getOutputStream());

            Controller controller = new Controller();

            String recvCommand = "";
            String sendCommand = "";

            while (!recvCommand.equals("EXIT")) {
                recvCommand = (String) is.readObject();
                sendCommand = controller.executeTask(recvCommand);
                os.writeObject(sendCommand);
            }
            accSocket.close();
        } catch (IOException | ClassNotFoundException | ServiceException e) {
            e.printStackTrace();
        }
    }


}
