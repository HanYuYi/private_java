import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;

/**
 * Reader Writer: 字符输入流 字符输出流
 * 读取的是字节流：char[]
 * Reader是基于InputStream的，在指定编码的同时将InputStream转为Reader
 */
public class ReaderAndWriter03 {

    private static final String OS_SEPARATOR = File.separator;

    public static void main(String[] args) throws IOException {

        useFileReader();

        useInputStreamReader();

        useSimulator();

        useCharArrayWriter();

        useOutputStreamWriter();

    }

    /**
     * 输入流一个文件
     * @throws IOException
     */
    public static void useFileReader() throws IOException {
        System.out.println("----------------FileReader----------------");
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader fileReader = new FileReader(
                "." + OS_SEPARATOR + "IOsrc" + OS_SEPARATOR + "verificationCode.html",
                StandardCharsets.UTF_8
        )) {
            int n;
            while ((n = fileReader.read(/*可以定义符缓冲长度：new char[100]*/)) != -1) {
                stringBuilder.append((char) n);
            }
            System.out.println(stringBuilder);
        }
    }

    /**
     * CharArrayReader  StringReader
     * 在内存中模拟一个FileReader，FileWriter也一样
     * @throws IOException
     */
    public static void useSimulator() throws IOException {
        System.out.println("----------------CharArrayReader----------------");

        try (CharArrayReader charArrayReader = new CharArrayReader("哈哈哈哈，我是CharArrayReader".toCharArray())) {
            int n;
            while ((n = charArrayReader.read()) != -1) {
                System.out.print((char) n);
            }
        }

        System.out.println();

        System.out.println("----------------StringReader----------------");
        try (StringReader stringReader = new StringReader("哈哈哈哈，我是StringReader")) {
            int n;
            while ((n = stringReader.read()) != -1) {
                System.out.print((char) n);
            }
            System.out.println();
        }
    }

    /**
     * InputStreamReader转换器
     * 把InputStream转换为Reader
     * @throws IOException
     */
    public static void useInputStreamReader() throws IOException {
        System.out.println("----------------InputStreamReader----------------");
        byte[] inputData = "你好，InputStreamReader".getBytes(StandardCharsets.UTF_8);

        try (
                InputStreamReader inputStreamReader = new InputStreamReader(
                        new ByteArrayInputStream(inputData),
                        StandardCharsets.UTF_8
                )
        ) {
            int n;
            while ((n = inputStreamReader.read()) != -1) {
                System.out.print((char) n);
            }
        }
        System.out.println();
    }

    /**
     * 在内存中模拟一个FileWriter，和StringWriter一样
     * @throws IOException
     */
    public static void useCharArrayWriter() throws IOException {
        System.out.println("----------------CharArrayWriter----------------");

        CharArrayWriter charArrayWriter = new CharArrayWriter();
        charArrayWriter.write('a');
        charArrayWriter.write(66);
        charArrayWriter.write("你好！");
        System.out.println(charArrayWriter.toCharArray());
    }

    /**
     * OutputStream的转换器
     * @throws IOException
     */
    public static void useOutputStreamWriter() throws IOException {
        File file = new File("."+OS_SEPARATOR+"IODirectory");
        if (!file.isDirectory()) {
            file.mkdir();
        }

        try (
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        new FileOutputStream("." + OS_SEPARATOR + "IODirectory" + OS_SEPARATOR + "outputStreamWriter.txt"),
                        StandardCharsets.UTF_8
                )
        ) {
            outputStreamWriter.write('1');
            outputStreamWriter.write("222");
            outputStreamWriter.write("哈哈哈哈哈哈");
        }
    }
}
