package demo03;

public class Person implements IPerson<String> {
    @Override
    public String printName(String data) {
        return data;
    }
}
