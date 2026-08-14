import java.util.Scanner;
import java.util.Random;

public class HammingCode {

    public static int getRedundantBits(int m) {
        int r = 0;
        while ((1 << r) < (m + r + 1)) {
            r++;
        }
        return r;
    }

    public static int[] generateHammingCode(int[] data) {
        int m = data.length;
        int r = getRedundantBits(m);
        int totalLen = m + r;
        int[] code = new int[totalLen + 1];

        int j = 0;
        for (int i = 1; i <= totalLen; i++) {
            if ((i & (i - 1)) == 0) {
                code[i] = 0;
            } else {
                code[i] = data[j++];
            }
        }

        for (int i = 0; i < r; i++) {
            int parityPos = (1 << i);
            int parityVal = 0;

            for (int k = 1; k <= totalLen; k++) {
                if ((k & parityPos) != 0 && k != parityPos) {
                    parityVal ^= code[k];
                }
            }
            code[parityPos] = parityVal;
        }

        return code;
    }

    public static void decodeHammingCode(int[] code, int r) {
        int totalLen = code.length - 1;
        int errorPos = 0;

        for (int i = 0; i < r; i++) {
            int parityPos = (1 << i);
            int parityVal = 0;

            for (int j = 1; j <= totalLen; j++) {
                if ((j & parityPos) != 0) {
                    parityVal ^= code[j];
                }
            }

            if (parityVal != 0) {
                errorPos += parityPos;
            }
        }

        if (errorPos == 0) {
            System.out.println("Status: Sab sahi hai bhai! Koi error nahi mila.");
        } else {
            System.out.println("Status: Arre yaar, noise aaya tha! Receiver ne XOR checks se calculate karke Bit " 
                    + errorPos + " pe error dhoond liya.");
            code[errorPos] ^= 1;

            System.out.print("Sahi wala codeword fix karke ye bana: ");
            for (int i = totalLen; i >= 1; i--) {
                System.out.print(code[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Kitne data bits hain batao: ");
        int m = sc.nextInt();

        int[] data = new int[m];

        System.out.print("Chalo ab " + m + " data bits dalo (space deke): ");
        for (int i = 0; i < m; i++) {
            data[i] = sc.nextInt();
        }

        int r = getRedundantBits(m);
        int totalLen = m + r;

        int[] codeword = generateHammingCode(data);

        System.out.println("\n--- TRANSMITTER SIDE ---");
        System.out.println("Extra parity bits kitne lage (r): " + r);

        System.out.print("Bana hua codeword: ");
        for (int i = totalLen; i >= 1; i--) {
            System.out.print(codeword[i] + " ");
        }
        System.out.println();

        System.out.println("\n--- RECEIVER SIDE (Sahi Data) ---");
        decodeHammingCode(codeword.clone(), r);

        System.out.println("\n--- AUTOMATIC CHANNEL NOISE ---");
        int errPos = rand.nextInt(totalLen) + 1;
        System.out.println("Wire me noise aane se randomly bit " + errPos + " flip ho gayi!");

        int[] corruptedCodeword = codeword.clone();
        corruptedCodeword[errPos] ^= 1;

        System.out.print("Kharab wala codeword jo receiver ko mila: ");
        for (int i = totalLen; i >= 1; i--) {
            System.out.print(corruptedCodeword[i] + " ");
        }
        System.out.println();

        System.out.println("\n--- RECEIVER SIDE (Auto Detect & Correct) ---");
        decodeHammingCode(corruptedCodeword, r);

        sc.close();
    }
}
