#include <iostream>
#include <iomanip>
using namespace std;

#define INF 999

int main() {
    int n;

    cout << "Enter the number of routers: ";
    cin >> n;

    int cost[10][10];
    int dist[10][10];
    int nextHop[10][10];

    // Read the cost matrix
    cout << "\nEnter the cost matrix:\n";
    cout << "(Enter 999 for no direct connection)\n";

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {

            cin >> cost[i][j];

            // Initially, distance is the direct cost
            dist[i][j] = cost[i][j];

            if (i == j)
                nextHop[i][j] = i;
            else if (cost[i][j] != INF)
                nextHop[i][j] = j;
            else
                nextHop[i][j] = -1;
        }
    }

    // Display initial routing tables
    cout << "\n\n========== INITIAL ROUTING TABLES ==========\n";

    for (int i = 0; i < n; i++) {

        cout << "\nRouting Table for Router " << i + 1 << endl;
        cout << "----------------------------------------\n";

        cout << left
             << setw(15) << "Destination"
             << setw(10) << "Cost"
             << setw(10) << "Next Hop" << endl;

        for (int j = 0; j < n; j++) {

            cout << left << setw(15) << j + 1;

            if (dist[i][j] == INF)
                cout << setw(10) << "INF";
            else
                cout << setw(10) << dist[i][j];

            if (nextHop[i][j] == -1)
                cout << "-";
            else
                cout << nextHop[i][j] + 1;

            cout << endl;
        }
    }

    // Distance Vector Algorithm
    bool updated;

    do {
        updated = false;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                for (int k = 0; k < n; k++) {

                    if (dist[i][k] != INF &&
                        dist[k][j] != INF &&
                        dist[i][j] > dist[i][k] + dist[k][j]) {

                        // Update distance
                        dist[i][j] =
                            dist[i][k] + dist[k][j];

                        // Update next hop
                        nextHop[i][j] =
                            nextHop[i][k];

                        updated = true;
                    }
                }
            }
        }

    } while (updated);

    // Display final routing tables
    cout << "\n\n========== FINAL ROUTING TABLES ==========\n";

    for (int i = 0; i < n; i++) {

        cout << "\nRouting Table for Router " << i + 1 << endl;
        cout << "----------------------------------------\n";

        cout << left
             << setw(15) << "Destination"
             << setw(10) << "Cost"
             << setw(10) << "Next Hop" << endl;

        for (int j = 0; j < n; j++) {

            cout << left << setw(15) << j + 1;

            if (dist[i][j] == INF)
                cout << setw(10) << "INF";
            else
                cout << setw(10) << dist[i][j];

            if (nextHop[i][j] == -1)
                cout << "-";
            else
                cout << nextHop[i][j] + 1;

            cout << endl;
        }
    }

    // Shortest path between selected source and destination
    int source, destination;

    cout << "\n\nEnter source router: ";
    cin >> source;

    cout << "Enter destination router: ";
    cin >> destination;

    source--;
    destination--;

    cout << "\n========== SHORTEST PATH ==========\n";

    if (dist[source][destination] == INF) {

        cout << "No path exists between Router "
             << source + 1
             << " and Router "
             << destination + 1 << endl;
    }
    else {

        cout << "Source: Router "
             << source + 1 << endl;

        cout << "Destination: Router "
             << destination + 1 << endl;

        cout << "Minimum Cost: "
             << dist[source][destination] << endl;

        cout << "Next Hop: Router "
             << nextHop[source][destination] + 1
             << endl;
    }

    return 0;
}
