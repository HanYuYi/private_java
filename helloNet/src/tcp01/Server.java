package tcp01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 根据客户端socket
 */
public class Server {
    public static void main(String[] args) {
        try {
            // 创建服务器对象，并开启端口
            ServerSocket serverSocket = new ServerSocket(6666);
            // 获取客户端socket对象
            Socket socket = serverSocket.accept();
            // 获取网络输入流
            InputStream inputStream = socket.getInputStream();
            // 读取客户端输入流的数据
            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);
            String s = new String(bytes, 0, len);
            System.out.println("收到客户端数据：" + s);
            // 获取网络字节输出流
            OutputStream outputStream = socket.getOutputStream();
            // 写入数据给客户端
            outputStream.write("收到！谢谢".getBytes(StandardCharsets.UTF_8));
            // 释放资源
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
