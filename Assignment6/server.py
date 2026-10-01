import socket

# Create UDP socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Define server address
server_address = ("127.0.0.1", 9001)

# Bind socket to IP and port
server_socket.bind(server_address)

print("UDP Server is running...")
print("Waiting for client...")

# Receive message and client address
data, client_address = server_socket.recvfrom(1024)

# Convert received data to string
message = data.decode()

# Display received message
print("Received from client:", message)

# Convert uppercase to lowercase
result = message.lower()

# Send reply to client
server_socket.sendto(result.encode(), client_address)

print("Result sent to client:", result)

# Close server socket
server_socket.close()
