import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegExp: 正则表达式
 */
public class Main {
    public static void main(String[] args) {
        // ============java中 \ 是转义字符，所以两个 \\ 代表一个 \ ============

        // \d 匹配一个数字
        // \w 匹配一个字母或数字或下划线
        // \s 匹配一个空格，包括tab（Java中用\t表示tab）
        // \D 匹配一个非\d匹配的字符
        // \W 匹配一个非\w匹配的字符
        // \S 匹配一个\s匹配的字符
        // \d{5} 匹配五个数字
        // \& 匹配&
        // a\u548cc 表示十六进制，匹配一个非ASCII字符，比如中文，这里匹配 a和c，因为中文字符和的Unicode编码是548c
        // . 匹配一个任意字符

        // * 匹配任意个字符
        // + 匹配至少一个字符
        // ? 匹配0个或一个字符，也开启非贪婪匹配
        // {2,5} 匹配2到5个字符
        // {5,} 匹配至少5个字符
        // [123] 或者 [1-3] 匹配一个数字只能为123
        // [^123] 或者 [^1-3] 匹配一个数字不能为123

        // [0-9a-fA-F] 匹配小写不限的十六进制数
        // [0-9a-fA-F]{6} 匹配6位十六进制数

        // 12|52 匹配12或25
        // () 匹配提取或的部分，如："learn\\s(java|php|go)"
        // () 匹配分组，如："(\d{3,4})\\-(\d{6,8})"

        System.out.println("abcd".matches("ab\\w{2}"));
        System.out.println("a和和c".matches("a\\u548c{2}c"));
        System.out.println("a*@\\c".matches("a\\*.{2}c"));
        System.out.println("awwwwwww".matches("a\\w*"));
        System.out.println("A123".matches("A\\d{3}"));
        System.out.println("A123".matches("^A\\d{3}$"));
        System.out.println("A571".matches("^A[^123]{3}$"));
        System.out.println("13".matches("^12|25$"));
        System.out.println("learn Java".matches("^(learn\\s(java|php|go)|learn\\s(Java|Php|Go))$"));
        System.out.println("learn php".matches("^learn\\s((j|p|g)|(J|P|G))(ava|hp|o)$"));


        Pattern compile = Pattern.compile("(\\d{3,4})\\-(\\d{6,8})");
        System.out.println(compile.matcher("0100-3517442").matches());
        Matcher matcher = compile.matcher("0771-510877");

        // regex包提取匹配后的分组数据
        if (matcher.matches()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }

        // 非贪婪匹配
        Pattern compile1 = Pattern.compile("(\\d??)(9*)");
        Matcher matcher1 = compile1.matcher("99999");
        if (matcher1.matches()) {
            System.out.println(matcher1.group(1));
            System.out.println(matcher1.group(2));
        }

        // 分割字符串也可以使用正则
        for (String s : "a,,b; c".split("[\\,\\;\\s]")) {
            System.out.println(s);
        }

        // 替换字符串
        String str = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
        String str1 = str.replaceAll("\\s+", " ");
        System.out.println(str1);

        // 正则实现简单的模版引擎
        String template = "Hello, ${name}! You are learning ${lang}!";
        HashMap<String, String> stringStringHashMap = new HashMap<>() {};
        stringStringHashMap.put("name", "Bob");
        stringStringHashMap.put("lang", "Java");

        for (String k : stringStringHashMap.keySet()) {
            template = template.replaceAll("\\$\\{" + k + "\\}", stringStringHashMap.get(k));
        }
        System.out.println(template);
    }
}