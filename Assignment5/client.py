%%writefile client.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 45678
#define BUFFER_SIZE 1024

int main()
{
    int client_socket;
    struct sockaddr_in server_address;

    char message[BUFFER_SIZE];
    char buffer[BUFFER_SIZE];

    // 1. Create TCP socket
    client_socket = socket(AF_INET, SOCK_STREAM, 0);

    if (client_socket < 0)
    {
        perror("Socket creation failed");
        return 1;
    }

    printf("Client socket created successfully.\n");

    // 2. Configure server address
    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(PORT);

    // Server is running on localhost
    if (inet_pton(AF_INET, "127.0.0.1",
                  &server_address.sin_addr) <= 0)
    {
        perror("Invalid address");
        close(client_socket);
        return 1;
    }

    // 3. Connect to server
    if (connect(client_socket,
                (struct sockaddr *)&server_address,
                sizeof(server_address)) < 0)
    {
        perror("Connection failed");
        close(client_socket);
        return 1;
    }

    printf("Connected to server.\n");

    // 4. Input message from user
    printf("Enter message for server: ");
    fgets(message, BUFFER_SIZE, stdin);

    // Remove newline
    message[strcspn(message, "\n")] = '\0';

    // 5. Send message to server
    send(client_socket, message, strlen(message), 0);

    printf("Message sent to server.\n");

    // 6. Receive reply from server
    memset(buffer, 0, BUFFER_SIZE);

    recv(client_socket, buffer, BUFFER_SIZE - 1, 0);

    // 7. Display server reply
    printf("Reply from server: %s\n", buffer);

    // 8. Close client socket
    close(client_socket);

    printf("Client closed.\n");

    return 0;
}
