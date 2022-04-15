package com.car.client;

public class SocketClient extends Thread {
    Object object;
    public SocketClient(Object object ){
        this.object = object;
    }
    @Override
    public void run() {
        Client client = new Client();
        client.send(object);
        client.receive();
    }
}