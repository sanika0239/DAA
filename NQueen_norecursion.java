
import java.util.Scanner;

public class NQueen_norecursion {

    static boolean isSafe(int board[][], int row, int col, int n) {

        // Check same row
        for (int j = 0; j < col; j++) {
            if (board[row][j] == 1)
                return false;
        }

        // Check upper diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {

            if (board[i][j] == 1)
                return false;
        }

        // Check lower diagonal
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) {

            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    static boolean solveNQueens(int board[][], int n) {

        int row[] = new int[n];

        // First queen is fixed at (0,0)
        board[0][0] = 1;
        row[0] = 0;

        int col = 1;

        while (col > 0 && col < n) {

            boolean placed = false;

            // Try next row in current column
            for (int i = row[col] + 1; i < n; i++) {

                if (isSafe(board, i, col, n)) {

                    board[i][col] = 1;
                    row[col] = i;
                    placed = true;
                    break;
                }
            }

            if (placed) {
                col++;
                if (col < n)
                    row[col] = -1;
            } else {

                // Backtracking
                row[col] = -1;
                col--;

                if (col > 0) {
                    board[row[col]][col] = 0;
                }
            }
        }

        return col == n;
    }

    static void printBoard(int board[][], int n) {

        System.out.println("\nN-Queens Solution:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of N: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Enter a positive value.");
        } else {

            int board[][] = new int[n][n];

            if (solveNQueens(board, n)) {
                printBoard(board, n);
            } else {
                System.out.println("Solution does not exist.");
            }
        }

        sc.close();
    }
}


/*
 * ========================================================
 * TIME AND SPACE COMPLEXITY ANALYSIS
 * N-QUEENS USING ITERATIVE BACKTRACKING
 * ========================================================
 * 
 * Let:
 * N = Number of queens and chessboard rows/columns.
 * 
 * STEP 1: Create chessboard
 * --------------------------------
 * A 2D array of size N x N is created.
 * 
 * Total cells = N * N
 * 
 * Space Complexity = O(N^2)
 * 
 * 
 * STEP 2: Create row array
 * --------------------------------
 * An integer array of size N is created.
 * 
 * Space Complexity = O(N)
 * 
 * 
 * STEP 3: isSafe() method
 * --------------------------------
 * The method checks three directions:
 * 
 * 1. Same row: At most N iterations.
 * 2. Upper diagonal: At most N iterations.
 * 3. Lower diagonal: At most N iterations.
 * 
 * Total operations = N + N + N
 * = 3N
 * 
 * Ignoring constants:
 * 
 * Time Complexity = O(N)
 * 
 * 
 * STEP 4: solveNQueens() method
 * --------------------------------
 * The algorithm places one queen in each column.
 * 
 * For every column, it tries different rows.
 * For each attempted position, isSafe() takes O(N).
 * 
 * The algorithm may explore a factorial number
 * of arrangements using backtracking.
 * 
 * Worst-case number of arrangements is bounded
 * by N!.
 * 
 * Therefore:
 * 
 * Time Complexity = O(N * N * N!)
 * 
 * = O(N^2 * N!)
 * 
 * 
 * STEP 5: printBoard() method
 * --------------------------------
 * Two nested loops are used.
 * 
 * Outer loop runs N times.
 * Inner loop runs N times.
 * 
 * Total operations = N * N
 * 
 * Time Complexity = O(N^2)
 * 
 * 
 * OVERALL TIME COMPLEXITY
 * --------------------------------
 * Creating board = O(N^2)
 * Creating array = O(N)
 * Safety checking and search = O(N^2 * N!)
 * Printing board = O(N^2)
 * 
 * The recursive-free search dominates.
 * 
 * FINAL TIME COMPLEXITY = O(N^2 * N!)
 * 
 * 
 * SPACE COMPLEXITY
 * --------------------------------
 * 1. Chessboard array = O(N^2)
 * 2. Row array = O(N)
 * 3. Other variables = O(1)
 * 
 * Total Space = O(N^2) + O(N)
 * 
 * Ignoring lower-order terms:
 * 
 * FINAL SPACE COMPLEXITY = O(N^2)
 * 
 * 
 * ========================================================
 * FINAL ANSWER:
 * Time Complexity = O(N^2 * N!)
 * Space Complexity = O(N^2)
 * ========================================================
 */
