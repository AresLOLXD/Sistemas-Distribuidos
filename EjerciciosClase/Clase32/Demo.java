import java.io.Serializable;

class Demo implements Serializable {
    public int a;
    public String b;

    // Default constructor
    public Demo(int a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Demo [a=" + a + ", b=" + b + "]";
    }
}