#include <iostream>
#include<string>
using namespace std;
int main()
{
    cout<<"Enter IP address"<<endl;
    string ip;
    cin>>ip;
    string sub = ip.substr(0,3);
    // class of the IP address
    int num = stoi(sub);
    if (num >= 1 && num <= 126)
       { cout << "Class A" << endl;
         cout<<"Default subnet mask 255.0.0.0"<<endl;
         cout << "Network Bits = 8\n";
        cout << "Host Bits = 24\n";
        cout << "Number of Networks = 2^7 = 128 (126 usable)\n";
        cout << "Hosts per Network = 2^24 - 2 = 16,777,214\n";
       }
        
    else if (num == 127) {
        cout << "Loopback Address" << endl;
        cout<<"allows device to send network traffic to itself"<<endl;
    }
    
    else if (num >= 128 && num <= 191)
        {cout << "Class B" << endl;
        cout<<"Default Subnet Mask: 255.255.0.0"<<endl;
        cout << "Network Bits = 16\n";
        cout << "Host Bits = 16\n";
        cout << "Number of Networks = 2^14 = 16,384\n";
        cout << "Hosts per Network = 2^16 - 2 = 65,534\n";
        }
        
        // class c starts with 110 (3 bits)
    else if (num >= 192 && num <= 223)
        {cout << "Class C" << endl;
        cout<<"Default subnet mask: 255.255.255.0"<<endl;
        cout << "Network Bits = 24\n";
        cout << "Host Bits = 8\n";
        cout << "Number of Networks = 2^21 = 2,097,152\n";
        cout << "Hosts per Network = 2^8 - 2 = 254\n";
        }
        
    else if (num >= 224 && num <= 239)
        {cout << "Class D (Multicast)" << endl;
        cout<<"No default subjet mask "<<endl;}
        
    else if (num >= 240 && num <= 255)
        {cout << "Class E (Experimental)" << endl;
        cout<<"No default subnet mask"<<endl;}
        
    else
        cout << "Invalid IP Address" << endl;
        
  
    
    return 0;
}
