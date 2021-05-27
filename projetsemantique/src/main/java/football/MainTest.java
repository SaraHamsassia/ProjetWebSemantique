package football;

public class MainTest {

    public static void main(String[] args) {

        System.out.println();

        Entraineur ent = new Entraineur("12345");

        if (ent.getLabel() == null) {
            System.out.println("Oui");
        }
    }

}
