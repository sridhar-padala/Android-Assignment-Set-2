#include <iostream>
#include <vector>

using namespace std;

class ModuleLoader {
public:
    bool hasCircularDependency(int n, vector<vector<int>>& edges) {
        // Building adjacency list
        vector<vector<int>> graph(n);
        for (auto& edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph[from].push_back(to);
        }

        //visited state array-- 0 = unvisited, 1 = visiting, 2 = visited
        vector<int> state(n, 0); 

        //DFS for each unvisited 
        for (int i = 0; i < n; ++i) {
            if (state[i] == 0) {
                if (dfsHasCycle(graph, state, i)) {
                    return true; //cycle
                }
            }
        }

        return false; // No cycle
    }

private:
    bool dfsHasCycle(vector<vector<int>>& graph, vector<int>& state, int node) {
        state[node] = 1; // Mark as visiting

        for (int neighbor : graph[node]) {
            if (state[neighbor] == 1) {
                // Found a cycle (back edge)
                return true;
            }
            if (state[neighbor] == 0) {
                if (dfsHasCycle(graph, state, neighbor)) {
                    return true;
                }
            }
        }

        state[node] = 2; // Mark as visited
        return false;
    }
};

int main() {
    ModuleLoader loader;

    vector<vector<int>> edges1 = {{0, 1}, {1, 2}, {2, 3}};
    cout << boolalpha << loader.hasCircularDependency(4, edges1) << endl; 

    vector<vector<int>> edges2 = {{0, 1}, {1, 2}, {2, 0}};
    cout << boolalpha << loader.hasCircularDependency(4, edges2) << endl; 

    return 0;
}
