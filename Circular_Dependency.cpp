#include <iostream>
#include <vector>
using namespace std;

bool dfs(int node, vector<vector<int>>& graph, vector<bool>& visited, vector<bool>& recstack) {
    visited[node] = true;
    recstack[node] = true;

    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            if (dfs(neighbor, graph, visited, recstack)){
                return true;
            }
        } else if (recstack[neighbor]) {
            return true;
        }
    }

    recstack[node] = false;  // Remove from recursion stack
    return false;
}

bool hasCircularDependency(int n, vector<vector<int>>& edges) {
    
    vector<vector<int>> graph(n);
    for (auto& edge : edges) {
        int from = edge[0];
        int to = edge[1];
        graph[from].push_back(to);
    }

    vector<bool> visited(n, false);
    vector<bool> recstack(n, false);

    // Check each component
    for (int i = 0; i < n; ++i) {
        if (!visited[i]) {
            if (dfs(i, graph, visited, recstack))
                return true;
        }
    }

    return false;
}

int main() {
    int n, m;
    cout << "Enter the number of nodes: ";
    cin >> n;
    
    cout << "Enter the number of edges: ";
    cin >> m;
    
    vector<vector<int>> edges;
    // give spaces while entering edges eg. 0 1 then press enter add another edge 1 2
    cout << "Enter the edges (from to):" << endl;
    for (int i = 0; i < m; i++) {
        int from, to;
        cin >> from >> to;
        edges.push_back({from, to});
    }
    
    bool hasCycle = hasCircularDependency(n, edges);
    
    if (hasCycle) {
        cout << "True" << endl;
    } else {
        cout << "False" << endl;
    }
    
    return 0;
}