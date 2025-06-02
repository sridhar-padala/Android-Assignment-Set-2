import java.util.*;

public class NQueensSolver {
    public static void main(String[] args) {
        // taking Input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n (1 to 9): ");
        int n = scanner.nextInt();
        scanner.close();

        List<List<String>> solutions = solveNQueens(n);

        // Printing the solutions
        System.out.println("Total Solutions: " + solutions.size());
        for (List<String> board : solutions) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println(); 
        }
    }

   
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initializing the board
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

       
        placeQueens(0, board, result);
        return result;
    }

  
    private static void placeQueens(int currentRow, char[][] board, List<List<String>> result) {
        int n = board.length;

        // If all the queens are done 
        if (currentRow == n) {
            result.add(constructBoard(board));
            return;
        }

        // Tring to place a queen in each column 
        for (int col = 0; col < n; col++) {
            if (isSafe(currentRow, col, board)) {
                board[currentRow][col] = 'Q'; // Place queen
                placeQueens(currentRow + 1, board, result); // Go to next row
                board[currentRow][col] = '.'; // Backtrack
            }
        }
    }

   
    private static boolean isSafe(int row, int col, char[][] board) {
        int n = board.length;

        // Checking  squares above
        for (int previousRow = 0; previousRow < row; previousRow++) {
            if (board[previousRow][col] == 'Q') {
                return false; 
            }
        }

        // Checking upper-left diagonal squares
        int checkRow = row - 1;
        int checkCol = col - 1;
        while (checkRow >= 0 && checkCol >= 0) {
            if (board[checkRow][checkCol] == 'Q') {
                return false; 
            }
            checkRow--;
            checkCol--;
        }

        // Checking  upper-right diagonal squares
        checkRow = row - 1;
        checkCol = col + 1;
        while (checkRow >= 0 && checkCol < n) {
            if (board[checkRow][checkCol] == 'Q') {
                return false; 
            }
            checkRow--;
            checkCol++;
        }

        // If no queen can attack this position
        return true;
    }

    // Converting the board into a list of strings 
    private static List<String> constructBoard(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }
}
