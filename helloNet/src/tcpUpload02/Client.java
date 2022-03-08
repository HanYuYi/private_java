package tcpUpload02;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String OS_SEPARATOR = File.separator;

    public static void main(String[] args) throws IOException {
        handle("."+OS_SEPARATOR+"IOsrc"+OS_SEPARATOR+"1.jpg");
    }

    /**
     * 客户端向服务器上传文件，并打印服务器返回的响应
     * @param fileUrl
     * @throws IOException
     */
    public static void handle(String fileUrl) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileUrl);
        Socket socket = new Socket("127.0.0.1", 6666);
        OutputStream outputStream = socket.getOutputStream();
        System.out.println("开始上传...");
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0 , len);
        }
        // 上传完文件给服务器一个结束标记
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        while ((len = inputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        fileInputStream.close();
        socket.close();
    }
}
