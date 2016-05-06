import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * Created by root on 16-5-6.
 */
public class Test {
    abstract class A {
        public A getA() {
            this.print();
            return this;
        }

        abstract public void print();

    }

    class B extends A {

        String b;

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        @Override
        public void print() {
            System.out.println("aas");
        }
    }
    static public void main(String[] args) {
        Test test = new Test();
        A a = test.new B();
         B b = (B) a.getA();
        b.setB("hello");
        System.out.println(b.getB());
    }
}
