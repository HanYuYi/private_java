import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * 用三方工具Jackson 反序列化 xml
 */
class Book {
    public long id;
    public String name;
    public String author;
    public String isbn;
    public List<String> tags;
    public String pubDate;
}


public class ReadXML {
    public static void main(String[] args) {
        InputStream resourceAsStream = ReadXML.class.getResourceAsStream("/book.xml");
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper mapper = new XmlMapper(module);
        try {
            Book book = mapper.readValue(resourceAsStream, Book.class);
            System.out.println(book.id);
            System.out.println(book.name);
            System.out.println(book.author);
            System.out.println(book.isbn);
            System.out.println(book.tags);
            System.out.println(book.pubDate);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
