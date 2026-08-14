#include <stdio.h>

int main()
{
    int a, b, c, d;
    int requiredSubnets;
    int subnetBits = 0;
    int totalSubnets;
    int hostBits;
    int hosts;
    int i;

    printf("Enter IP address: ");
    scanf("%d.%d.%d.%d", &a, &b, &c, &d);

    /* Check valid IP */
    if(a < 1 || a > 255 ||
       b < 0 || b > 255 ||
       c < 0 || c > 255 ||
       d < 0 || d > 255)
    {
        printf("Invalid IP address.\n");
        return 0;
    }

    /* Identify class */

    if(a >= 1 && a <= 126)
    {
        printf("\nIP Class: A\n");
        printf("Default Mask: 255.0.0.0\n");

        printf("Enter required number of subnets: ");
        scanf("%d", &requiredSubnets);

        /* Find subnet bits */
        totalSubnets = 1;

        while(totalSubnets < requiredSubnets)
        {
            totalSubnets = totalSubnets * 2;
            subnetBits++;
        }

        hostBits = 24 - subnetBits;
        hosts = (1 << hostBits) - 2;

        printf("\nNumber of subnet bits : %d", subnetBits);
        printf("\nActual number of subnets: %d", totalSubnets);
        printf("\nUsable hosts per subnet: %d", hosts);

        printf("\nNew Prefix: /%d", 8 + subnetBits);

        printf("\n\nSubnet Mask: ");

        if(subnetBits <= 8)
        {
            int mask = 256 - (1 << (8 - subnetBits));
            printf("255.%d.0.0\n", mask);
        }

        printf("\nFirst few subnets:\n");

        int blockSize = 1 << (8 - subnetBits);

        int limit = totalSubnets;

        if(limit > 10)
            limit = 10;

        for(i = 0; i < limit; i++)
        {
            int network = i * blockSize;

            printf("\nSubnet %d\n", i + 1);

            printf("Network   : %d.%d.0.0\n",
                   a, network);

            printf("First Host: %d.%d.0.1\n",
                   a, network);

            printf("Last Host : %d.%d.255.254\n",
                   a, network + blockSize - 1);

            printf("Broadcast : %d.%d.255.255\n",
                   a, network + blockSize - 1);
        }
    }

    else if(a >= 128 && a <= 191)
    {
        printf("\nIP Class: B\n");
        printf("Default Mask: 255.255.0.0\n");

        printf("Enter required number of subnets: ");
        scanf("%d", &requiredSubnets);

        totalSubnets = 1;

        while(totalSubnets < requiredSubnets)
        {
            totalSubnets = totalSubnets * 2;
            subnetBits++;
        }

        hostBits = 16 - subnetBits;
        hosts = (1 << hostBits) - 2;

        printf("\nNumber of subnet bits : %d", subnetBits);
        printf("\nActual number of subnets: %d", totalSubnets);
        printf("\nUsable hosts per subnet: %d", hosts);

        printf("\nNew Prefix: /%d", 16 + subnetBits);

        int mask = 256 - (1 << (8 - subnetBits));

        printf("\nSubnet Mask: 255.255.%d.0\n", mask);

        int blockSize = 1 << (8 - subnetBits);

        int limit = totalSubnets;

        if(limit > 10)
            limit = 10;

        printf("\nFirst few subnets:\n");

        for(i = 0; i < limit; i++)
        {
            int network = i * blockSize;

            printf("\nSubnet %d\n", i + 1);

            printf("Network   : %d.%d.%d.0\n",
                   a, b, network);

            printf("First Host: %d.%d.%d.1\n",
                   a, b, network);

            printf("Last Host : %d.%d.%d.254\n",
                   a, b, network + blockSize - 1);

            printf("Broadcast : %d.%d.%d.255\n",
                   a, b, network + blockSize - 1);
        }
    }

    else if(a >= 192 && a <= 223)
    {
        printf("\nIP Class: C\n");
        printf("Default Mask: 255.255.255.0\n");

        printf("Enter required number of subnets: ");
        scanf("%d", &requiredSubnets);

        totalSubnets = 1;

        while(totalSubnets < requiredSubnets)
        {
            totalSubnets = totalSubnets * 2;
            subnetBits++;
        }

        hostBits = 8 - subnetBits;

        if(hostBits < 2)
        {
            printf("Too many subnets for a Class C network.\n");
            return 0;
        }

        hosts = (1 << hostBits) - 2;

        printf("\nNumber of subnet bits : %d", subnetBits);
        printf("\nActual number of subnets: %d", totalSubnets);
        printf("\nUsable hosts per subnet: %d", hosts);

        printf("\nNew Prefix: /%d", 24 + subnetBits);

        int subnetMask = 256 - (1 << (8 - subnetBits));

        printf("\nSubnet Mask: 255.255.255.%d\n",
               subnetMask);

        int blockSize = 256 - subnetMask;

        int limit = totalSubnets;

        if(limit > 10)
            limit = 10;

        printf("\nFirst few subnets:\n");

        for(i = 0; i < limit; i++)
        {
            int network = i * blockSize;
            int firstHost = network + 1;
            int lastHost = network + hosts;
            int broadcast = network + blockSize - 1;

            printf("\nSubnet %d\n", i + 1);

            printf("Network   : %d.%d.%d.%d\n",
                   a, b, c, network);

            printf("First Host: %d.%d.%d.%d\n",
                   a, b, c, firstHost);

            printf("Last Host : %d.%d.%d.%d\n",
                   a, b, c, lastHost);

            printf("Broadcast : %d.%d.%d.%d\n",
                   a, b, c, broadcast);
        }
    }

    else if(a >= 224 && a <= 239)
    {
        printf("\nIP Class: D\n");
        printf("Class D is used for multicast.\n");
    }

    else
    {
        printf("\nIP Class: E\n");
        printf("Class E is reserved for experimental use.\n");
    }

    return 0;
}
