import java.io.*;

/**
 * File对象和Path，都是操作文件或文件夹的
 * Java 7开始，提供了Files和Paths，读写小文件，例如配置文件等
 */
public class FileAndPath01 {
    public static void main(String[] args) throws IOException {
        System.out.println("---------------directory--------------");

        File file = new File("./IOsrc/verificationCode.html");
        File file1 = new File("./IOsrc");

        System.out.println(file.isFile());
        System.out.println(file.getCanonicalFile());
        System.out.println(file.canExecute());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.length());

        System.out.println("------------------file------------------");
        System.out.println(file1.isDirectory());
        System.out.println(file1.getCanonicalFile());
        System.out.println(file1.canExecute());

        File[] fList = file1.listFiles();
        for (File f : fList) {
            System.out.println(f);
        }


    }
}
