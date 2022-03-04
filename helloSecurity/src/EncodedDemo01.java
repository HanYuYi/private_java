import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 算法
 */
public class EncodedDemo01 {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 编码有很多种，比如常见的ASCII（一个字节），Unicode（两个字节），UTF-8（三个字节），URL编码，Base64编码
        // 一个字节等于两个非中文字符
        // 编码算法不是加密算法
        // 用base64把二进制数据编码为文本，在Java中，二进制数据就是byte[]数组
        byte[] input = {(byte) 0x41, (byte) 0x42, (byte) 0x00};
        String s = Base64.getEncoder().encodeToString(input);
        System.out.println(s);

        // hash MD5加密，MD5输出长度短，容易破解，不推荐使用
        MessageDigest messageDigest1 = MessageDigest.getInstance("MD5");
        // 用随机数加盐，防止彩虹表攻击和反推
        double m1 = Math.random() * 50.5;
        messageDigest1.update(("hello" + m1).getBytes(StandardCharsets.UTF_8));
        byte[] digest1 = messageDigest1.digest();
        System.out.println(new BigInteger(1, digest1));

        // hash SHA-512加密
        MessageDigest messageDigest2 = MessageDigest.getInstance("SHA-512");
        // 用随机数加盐，防止彩虹表攻击和反推
        double m2 = Math.random() * 50.5;
        messageDigest2.update(("hello" + m1).getBytes(StandardCharsets.UTF_8));
        byte[] digest2 = messageDigest2.digest();
        System.out.println(new BigInteger(1, digest2));

        // BouncyCastle是一个提供了很多哈希算法和加密算法的三方库。它提供了Java标准库没有的一些算法，如，RipeMD160哈希算法
        // 使用第三方需要先下载jar包放到classPath中
        // java.security提供了一种标准机制，允许三方库无缝接入，并且注册，就像vue.use(XXX) Security.addProvider(new BouncyCastleProvider());

        // Hmac算法是一个使用key加盐且与hash结合的算法，为了线程安全，由KeyGenerator生成一个随机的key

    }
}