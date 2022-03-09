package tcpHttp03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class NewServer {
    private final String OS_SEPARATOR = File.separator;

    /**
     * 将url格式化后，并指定IOsrc目录
     * @param url
     * @return
     */
    private String urlTransfor(String url) {
        String s = url.replaceAll("\\/", "\\" + OS_SEPARATOR);
        return "." + s;
    }

    /**
     * 读取资源文件
     * @throws IOException
     */
    public void handler() throws IOException {
        System.out.println("Service running at http:127.0.0.1:8888");
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            // 将网络输入流转化为本地字符缓冲输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // 获取url
            String s = bufferedReader.readLine();
            String filterUrl = urlTransfor(s.split("\\s+")[1]);

            FileInputStream fileInputStream = null;
            OutputStream outputStream = accept.getOutputStream();
            // 设置请求头
            outputStream.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
            outputStream.write("Content-Type:text/html\r\n".getBytes(StandardCharsets.UTF_8));
            outputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
            try {
                fileInputStream = new FileInputStream(filterUrl);
                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len = fileInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes,0 , len);
                }
                System.out.println(filterUrl);
                fileInputStream.close();
            } catch (IOException e) {
                outputStream.write("<h1>404 NOT FOUND</h1>".getBytes(StandardCharsets.UTF_8));
                System.out.println(e);
            }
            outputStream.close();

            // accept.close();
        }
        // serverSocket.close();
    }
}

public class Server {

    public static void main(String[] args) throws IOException {
        new NewServer().handler();
    }
}
