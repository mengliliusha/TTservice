package com.example.fknm;

import android.text.TextUtils;
import android.util.Log;

//import com.example.rs.risenevaluatorpad.model.DataHolder;
//import com.example.rs.risenevaluatorpad.util.ActivityCollector;
//import com.example.rs.risenevaluatorpad.util.Constants;
//import com.example.rs.risenevaluatorpad.util.OperationUtil;
import com.example.tttest.util.ActivityCollector;
import com.example.tttest.util.ToastUtils;
import com.example.tttest.util.Constants;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sgll on 2018/12/10.
 */
public class ServerThread extends Thread {
    private Socket socket;
    boolean isLoop = true;

    public void setIsLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            //设置Android端口为9000
            ServerSocket serverSocket = new ServerSocket(9000);

            while (isLoop) {
                try {
                    //从连接队列中取出一个连接，如果没有则等待
                    socket = serverSocket.accept();
                    Log.v(Constants.SERVER_TAG, "从连接队列中取出一个连接");
//                    new ProcessClientRequestThread(socket).start();

                    while (true) {

                        /**
                         * isClosed()、isConnected()、isInputStreamShutdown()、isOutputStreamShutdown()
                         * 这些方法无法判断服务端是否断开，只能判断本地的状态
                         */
                        // 发送心跳包，单线程中使用，判断socket是否断开
                        socket.sendUrgentData(0xFF);
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//                        InputStreamReader reader = new InputStreamReader(inputStream);
//                        InputStream inputStream = socket.getInputStream();
                        byte[] buffer = new byte[1024];
//                        char[] buffer = new char[1024];
                        int bt;
                        String text = "";
//                        StringBuffer stringBuffer = new StringBuffer();

                        int index = 0;

                        Log.v(Constants.SERVER_TAG, "inputStream.read 之前");
                        while ((bt = inputStream.read(buffer)) != -1) {
                            Log.v(Constants.SERVER_TAG, "bt：" + bt + "，，" + (index++));
//                            stringBuffer.append(buffer, 0, bt);
                            text += new String(buffer, 0, bt).trim();
//                            String aaa = new String(buffer, 0, bt).trim();
//                            text = text + aaa;
                            Log.e(Constants.SERVER_TAG, "------------");
                            Log.e(Constants.SERVER_TAG, "text: " + text.trim());

                            /**
                             * 当接收到大数据，inputStream读取完后，由于inputStream通道是一直在的，
                             * inputStream.read(buffer)处在堵塞状态，所以并不会跳出当前循环，
                             * 导致无法判断数据是否传输完，所以PC端传输数据时需要在数据中
                             * 加入报尾，Android设备上接收到报尾，代表此次数据传输完毕，
                             * 也可以加上报头，来表示数据的开始
                             *
                             */
                            if (text.endsWith("-vvv")) {
                                text = text.substring(0, text.lastIndexOf("-vvv"));
//                                OperationUtil.getOperation(text);
//                                DataHolder.getInstance().setData("msg", text);
//                                ActivityCollector.startActivity(text);
                                Log.v(Constants.SERVER_TAG, "内容长度：" + text.length());
                                Log.v(Constants.SERVER_TAG, "读取结束，内容为：" + text);
                                Log.v(Constants.SERVER_TAG, "inputStream.read 之后");
                                break;
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
