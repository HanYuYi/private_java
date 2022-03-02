import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * InputStream OutPutStream: 输入流 输出流
 * 都是同步阻塞操作
 * 读取的是字节流：byte[]
 */
public class InputStreamAndOutputStreamTest02 {

    private static final String OS_SEPARATOR = File.separator;

    public static void main(String[] args) throws IOException {

        // readFileStream();

        readFileStreamTrySource();

        writeFile();

        redyToWriteFile();

        useFilter();
    }

    /**
     * 传统输入流
     * @throws IOException
     */
    public static void readFileStream() throws IOException {
        System.out.println("------------------FileInputStream-------------------");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("."+OS_SEPARATOR+"IOsrc"+OS_SEPARATOR+"verificationCode.html");
            StringBuilder stringBuilder = new StringBuilder();
            for (;;) {
                int s = fileInputStream.read(/*可以定义字节缓冲长度：new byte[100]*/);
                stringBuilder.append((char) s);
                if (s == -1) {
                    break;
                }
            }
            System.out.println(stringBuilder.toString());
        } finally {
            if (fileInputStream != null) fileInputStream.close();
        }
    }

    /**
     * 输入流一个文件
     * Java7 引入的新的try(resource)的语法，因为java7 InputStream实现了autoCloseable接口
     * 以后通用这种
     * @throws IOException
     */
    public static void readFileStreamTrySource() throws IOException {
        System.out.println("-----------FileInputStream-try(resource)----------");
        StringBuilder stringBuilder = new StringBuilder();

        try (FileInputStream input = new FileInputStream("."+OS_SEPARATOR+"IOsrc"+OS_SEPARATOR+"verificationCode.html")) {
            int n;
            while ((n = input.read()) != -1) {
                stringBuilder.append((char) n);
            }
            System.out.println(stringBuilder);
        }
    }

    /**
     * 创建文件夹
     */
    public static void mkDirectory() throws IOException {
        File file = new File("."+OS_SEPARATOR+"IODirectory");
        if (!file.isDirectory()) {
            file.mkdir();
        }
    }

    /**
     * 输出流一个文件
     * @throws IOException
     */
    public static void writeFile() throws IOException {
        mkDirectory();
        try (FileOutputStream fileOutputStream = new FileOutputStream("."+OS_SEPARATOR+"IODirectory"+OS_SEPARATOR+"javaOutputDemo.txt")) {
            for (int i = 0; i < 5; i++) {
                fileOutputStream.write(("" + i + i + i + i).getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    /**
     * 输入流一个文件，在把它输出流一个文件
     * @throws IOException
     */
    public static void redyToWriteFile() throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream("."+OS_SEPARATOR+"IOsrc"+OS_SEPARATOR+"verificationCode.html");
                FileOutputStream fileOutputStream = new FileOutputStream("."+OS_SEPARATOR+"IODirectory"+OS_SEPARATOR+"javaIOWrite.html")
        ) {
            mkDirectory();
            fileInputStream.transferTo(fileOutputStream);
        }
    }

    /**
     * 使用Filter
     * @throws IOException
     */
    public static void useFilter() throws IOException {
        System.out.println("--------------Filter---------------");
        byte[] inputData = "Hello FilterInputStream".getBytes(StandardCharsets.UTF_8);
        try (CountInputstream countInputstream = new CountInputstream(
                // ByteArrayInputStream可以在内存中模拟一个InputStream，ByteArrayoutputStream也一样
                new ByteArrayInputStream(inputData)
        )) {
            int n;
            while ((n = countInputstream.read()) != -1) {
                System.out.print((char) n);
            }
            System.out.println(countInputstream.getCount());
        }
    }

}

/**
 * Filter模式
 * jdk有自带的filter，如 ZipInputStream等，自己也可以写filter，filter继承于FilterInputStream
 */
class CountInputstream extends FilterInputStream {
    private int count = 0;

    protected CountInputstream(InputStream in) {
        super(in);
    }

    public int getCount() {
        return count;
    }

    public int read() throws IOException {
        int n = in.read();
        if (n != -1) {
            count++;
        }
        return n;
    }

    public int read (byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        if (n != -1) {
            count++;
        }
        return n;
    }
}
