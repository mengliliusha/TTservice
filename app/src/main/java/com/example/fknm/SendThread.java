package com.example.fknm;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by sgll on 2018/12/11.
 * 发送消息到PC端
 */
public class SendThread extends Thread {
    private final String data;

    private final Socket socket;

    public SendThread(String data, Socket socket){
        this.data = data;
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        sendDatas();
    }

    private void sendDatas(){
        try {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.write(data.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
