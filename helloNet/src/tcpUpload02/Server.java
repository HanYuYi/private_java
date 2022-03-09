package tcpUpload02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 多线程上传文件(一个文件开一个线程)
 */
class Uploader {
    // 上传文件的个数
    public static int fileCount = 0;
    private final String OS_SEPARATOR = File.separator;

    /**
     * 生成文件名
     * @return
     */
    private String fileName() {
        return "" + System.currentTimeMillis() + "_" +new Random().nextInt(999999) + ".jpg";
    }

    /**
     * 创建文件夹
     */
    private void mkDirectory() {
        File file = new File("." + OS_SEPARATOR + "IODirectory");
        if (file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 读取客户端数据，写到硬盘，并返回给客户端响应
     * @throws IOException
     */
    public void handle() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        int len = 0;
                        byte[] bytes = new byte[1024];
                        mkDirectory();
                        FileOutputStream fileOutputStream = new FileOutputStream("."+OS_SEPARATOR+"IODirectory"+OS_SEPARATOR+fileName());
                        while ((len = inputStream.read(bytes)) != -1) {
                            fileOutputStream.write(bytes, 0 , len);
                        }
                        Uploader.fileCount++;
                        System.out.println("文件写入到硬盘了...："+Uploader.fileCount);
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write("数据上传成功！".getBytes(StandardCharsets.UTF_8));
                        inputStream.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }).start();
            // fileOutputStream.close();
            // socket.close();
            // socket.close();
        }
    }
}

public class Server {
    public static void main(String[] args) throws IOException {
        new Uploader().handle();
    }
}
