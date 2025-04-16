import java.util.*;
class user{
    public static void main(String[] args) {
        Vector<String> letters = new Vector<>();
        letters.add("LION");
        letters.add("ELEPHANT");
        letters.add("GIRAFFE");
        letters.add("TIGER");
        letters.add("CROCODILE");
        letters.remove(3);
        System.out.println(" VECTOR: " +letters);
        System.out.println("\n The string representation is : " +letters.toString());
        System.out.println("\n AFTER REMOVING : " +letters);
        System.out.println("\n Replace animal : " +letters.set(0, "MONKEY"));
        System.out.println("\n Replace animal : " +letters.set(2,"fox"));
        System.out.println("\n Display again!! VECTOR: " +letters);
    }
}