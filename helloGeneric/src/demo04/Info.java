package demo04;

public class Info <T extends Person>{
    private T par;

    public void setPar(T par) {
        this.par = par;
    }

    public T getPar() {
        return par;
    }

    @Override
    public String toString() {
        return "Info{" +
                "par=" + par +
                '}';
    }
}
