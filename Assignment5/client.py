import socket

# Create TCP socket
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Server address
server_address = ("127.0.0.1", 12345)

# Connect to server
client_socket.connect(server_address)

print("Connected to TCP Chat Server!")

while True:

    # Take message from client
    message = input("Client: ")

    # Send message to server
    client_socket.send(message.encode())

    # Exit
    if message.lower() == "exit":
        print("Chat ended.")
        break

    # Receive reply from server
    data = client_socket.recv(1024)

    if not data:
        break

    reply = data.decode()

    # Display server reply
    print("Server:", reply)

# Close socket
client_socket.close()

print("Client closed.")
