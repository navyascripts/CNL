import socket

# Create TCP socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Server address
server_address = ("127.0.0.1", 12345)

# Bind socket
server_socket.bind(server_address)

# Listen for client
server_socket.listen(1)

print("TCP Chat Server is running...")
print("Waiting for client...")

# Accept client connection
client_socket, client_address = server_socket.accept()

print("Client connected:", client_address)

while True:

    # Receive message from client
    data = client_socket.recv(1024)

    if not data:
        break

    message = data.decode()

    print("Client:", message)

    # Exit
    if message.lower() == "exit":
        print("Chat ended.")
        break

    # Automatic server reply
    reply = "Server received: " + message

    # Send reply to client
    client_socket.send(reply.encode())

# Close sockets
client_socket.close()
server_socket.close()

print("Server closed.")
