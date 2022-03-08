package tcp01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        try {
            // 创建客户端对象，并指定通信的ip和端口
            Socket socket = new Socket("127.0.0.1", 6666);
            // 获取网络输出流对象
            OutputStream outputStream = socket.getOutputStream();
            // 写入数据给服务端
            outputStream.write("你好，服务器".getBytes(StandardCharsets.UTF_8));
            // 获取网络输入流对象
            InputStream inputStream = socket.getInputStream();
            // 读取服务端输出流的数据
            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);
            System.out.println("收到服务端数据：" + new String(bytes, 0, len));
            // 释放资源
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
