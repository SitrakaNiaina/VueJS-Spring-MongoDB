public class Main {
    public static void main(String[] args) {
        int[] nombre = {30,15,24,125,20};
        for (int i : nombre) {
            if(i % 5 == 0 && i % 3 == 0)
                System.out.println(i + ": FluzzGratz");
            else if(i % 3 == 0)
                System.out.println(i + ": Fluzz");
            else if(i % 5 == 0)
                System.out.println(i + ": Gratz");
        }
    }
}
