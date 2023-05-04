import java.util.Scanner;

public class Temp {

    public static void main(String[] args) {
        int count;
        try (Scanner scanner = new Scanner(System.in)) {
             count = scanner.nextInt();
        }

        for (int i = 1; i <= count; i++) {
            System.out.print(" ".repeat(count - i));
            if (i == count) {
                System.out.println("*".repeat(count * 2 - 1));
            } else if (i == 1) {
                System.out.println("*");
            } else {
                System.out.println("*" + " ".repeat((i - 1) * 2 - 1) + "*");
            }
        }

    }
}