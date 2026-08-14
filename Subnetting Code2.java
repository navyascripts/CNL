import java.util.Scanner;

public class Subnetting {

    public static String intToIP(long ip) {
        return ((ip >> 24) & 255) + "." +
               ((ip >> 16) & 255) + "." +
               ((ip >> 8) & 255) + "." +
               (ip & 255);
    }

    public static long ipToInt(String ipStr) {
        String[] parts = ipStr.split("\\.");
        long ip = 0;

        for (int i = 0; i < 4; i++) {
            ip = (ip << 8) | Integer.parseInt(parts[i]);
        }

        return ip;
    }

    public static char getIPClass(long firstOctet) {
        if (firstOctet <= 127) return 'A';
        if (firstOctet <= 191) return 'B';
        if (firstOctet <= 223) return 'C';
        if (firstOctet <= 239) return 'D';
        return 'E';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Base IP address dalo (jaise 192.168.1.0): ");
        String ipStr = sc.next();

        System.out.print("Subnet prefix/CIDR dalo (jaise 26): ");
        int cidr = sc.nextInt();

        if (cidr < 1 || cidr > 30) {
            System.out.println("Arre bhai galat CIDR daal diya! 1 se 30 ke beech me hona chahiye.");
            sc.close();
            return;
        }

        long baseIP = ipToInt(ipStr);
        long firstOctet = (baseIP >> 24) & 255;
        char ipClass = getIPClass(firstOctet);

        long mask = (cidr == 0) ? 0 :
                (0xFFFFFFFFL << (32 - cidr)) & 0xFFFFFFFFL;

        long networkIP = baseIP & mask;
        int hostBits = 32 - cidr;
        long totalIPs = 1L << hostBits;
        long usableHosts = (totalIPs > 2) ? (totalIPs - 2) : 0;

        int defaultCidr = (ipClass == 'A') ? 8 :
                (ipClass == 'B') ? 16 :
                (ipClass == 'C') ? 24 : 0;

        int borrowedBits = (cidr > defaultCidr) ? (cidr - defaultCidr) : 0;
        int numSubnets = 1 << borrowedBits;

        System.out.println("\n--- SUBNET DETAILS ---");
        System.out.println("Base IP: " + ipStr);
        System.out.println("Kon sa Class hai: Class " + ipClass);
        System.out.println("CIDR Notation: /" + cidr);
        System.out.println("Subnet Mask kitna bana: " + intToIP(mask));
        System.out.println("Har subnet me Total IPs: " + totalIPs);
        System.out.println("Kaam ke usable hosts: " + usableHosts);
        System.out.println("Kitne total subnets bane: " + numSubnets);

        System.out.println("\n--- SUB-NETWORKS BREAKDOWN ---");
        System.out.println("Subnet #\tNetwork ID\t\tFirst Host\t\tLast Host\t\tBroadcast ID");

        long currentNet = networkIP;
        int displayLimit = Math.min(numSubnets, 8);

        for (int i = 1; i <= displayLimit; i++) {
            long firstHost = currentNet + 1;
            long broadcast = currentNet + totalIPs - 1;
            long lastHost = broadcast - 1;

            System.out.println(i + "\t\t" +
                    intToIP(currentNet) + "\t\t" +
                    intToIP(firstHost) + "\t\t" +
                    intToIP(lastHost) + "\t\t" +
                    intToIP(broadcast));

            currentNet += totalIPs;
        }

        if (numSubnets > 8) {
            System.out.println("... baki " + (numSubnets - 8) +
                    " subnets chhode hain zyada lamba na ho isliye.");
        }

        sc.close();
    }
}
