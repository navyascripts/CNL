import socket

# Create UDP socket
client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Define server address
server_address = ("127.0.0.1", 9001)

# Input message from user
message = input("Enter a string in uppercase: ")

# Send message to server
client_socket.sendto(message.encode(), server_address)

# Receive reply from server
data, server_address = client_socket.recvfrom(1024)

# Display server reply
result = data.decode()

print("Result:", result)

# Close client socket
client_socket.close()
