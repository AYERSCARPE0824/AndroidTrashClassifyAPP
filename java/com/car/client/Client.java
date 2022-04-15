package com.car.client;

import com.car.information.NecessaryInformation;
import com.car.result.EnvironmentInfoResult;
import com.car.result.RecResult;
import com.car.result.StationsResult;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client  {

    private int port = 7777;
    private String host = "192.168.41.37";  //本地服务器IP地址
    private Socket socket = null;
    public Client(){
        try {
            socket = new Socket(NecessaryInformation.host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send (Object request){

        try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void receive(){
        try {

            ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            Object object = inputStream.readObject();

            if (object instanceof RecResult) {
                NecessaryInformation.recResult = (RecResult) object;
                if (NecessaryInformation.recResult.getResultType() == RecResult.WORD_REC_RESULT){
                    NecessaryInformation.wordRecStatus = true;
                } else {
                    NecessaryInformation.picRecStatus = true;
                }
            } else if (object instanceof StationsResult) {
                NecessaryInformation.stationsStatus = true;
                NecessaryInformation.stationsResult = (StationsResult) object;
            } else if (object instanceof EnvironmentInfoResult) {
                NecessaryInformation.environmentInfoStatus = true;
                NecessaryInformation.environmentInfoResult = (EnvironmentInfoResult) object;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
