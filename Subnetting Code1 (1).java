import java.util.Scanner;

public class IPClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter IP address: ");
        String ip = sc.next();

        String[] parts = ip.split("\\.");

        if (parts.length != 4) {
            System.out.println("Invalid IP address.");
            sc.close();
            return;
        }

        int a, b, c, d;

        try {
            a = Integer.parseInt(parts[0]);
            b = Integer.parseInt(parts[1]);
            c = Integer.parseInt(parts[2]);
            d = Integer.parseInt(parts[3]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid IP address.");
            sc.close();
            return;
        }

        if (a < 1 || a > 255 ||
            b < 0 || b > 255 ||
            c < 0 || c > 255 ||
            d < 0 || d > 255) {

            System.out.println("Invalid IP address.");
            sc.close();
            return;
        }

        if (a >= 1 && a <= 126)
            System.out.println("IP Class: A");
        else if (a >= 128 && a <= 191)
            System.out.println("IP Class: B");
        else if (a >= 192 && a <= 223)
            System.out.println("IP Class: C");
        else if (a >= 224 && a <= 239)
            System.out.println("IP Class: D");
        else
            System.out.println("IP Class: E");

        sc.close();
    }
}
