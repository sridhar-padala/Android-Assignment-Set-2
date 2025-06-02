#include <iostream>
#include <vector>
#include <string>

using namespace std;

class NQueensSolver {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> result;
        // Initializing n x n board with '.'
        vector<string> board(n, string(n, '.')); 
        placeQueens(0, n, board, result);
        return result;
    }

private:
    
    void placeQueens(int row, int n, vector<string>& board, vector<vector<string>>& result) {
        // If all queens are done
        if (row == n) {
            result.push_back(board); 
            return;
        }

        //Tring to place a queen in each column
        for (int col = 0; col < n; ++col) {
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q';              
                placeQueens(row + 1, n, board, result); 
                // Backtrack
                board[row][col] = '.';  
            }
        }
    }

    
    bool isSafe(int row, int col, vector<string>& board) {
        int n = board.size();

        //Checking squares above in the same column 
        for (int i = 0; i < row; ++i) {
            if (board[i][col] == 'Q') {
                return false; 
            }
        }

        //Checking  upper-left diagonal squares
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == 'Q') {
                return false; 
            }
        }

        //Checking  upper-right diagonal squares
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; --i, ++j) {
            if (board[i][j] == 'Q') {
                return false; 
            }
        }

        // Safe to place queen 
        return true;
    }
};

int main() {
    int n;
    cout << "Enter the value of n (1 to 9): ";
    cin >> n;

    NQueensSolver solver;
    vector<vector<string>> solutions = solver.solveNQueens(n);

    // Printing the  solution
    cout << "Total Solutions: " << solutions.size() << endl;
    for (const auto& board : solutions) {
        for (const auto& row : board) {
            cout << row << endl;
        }
        cout << endl;
    }

    return 0;
}
