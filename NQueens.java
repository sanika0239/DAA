import java.util.Scanner;

public class NQueens {

    static int N;
    static int[][] board;

    static boolean isSafe(int row, int col) {

        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    static boolean solveNQueens(int row) {

        if (row == N) {
            return true;
        }

        boolean fixedQueen = false;
        for (int col = 0; col < N; col++) {
            if (board[row][col] == 1) {
                fixedQueen = true;
                break;
            }
        }

        if (fixedQueen) {
            return solveNQueens(row + 1);
        }

        for (int col = 0; col < N; col++) {

            if (isSafe(row, col)) {
                board[row][col] = 1;

                if (solveNQueens(row + 1)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the value of N: ");
        N = sc.nextInt();

        if (N <= 0) {
            System.out.println("N must be a positive integer.");
            sc.close();
            return;
        }

        board = new int[N][N];

        System.out.print("Enter first Queen's row (1 to " + N + "): ");
        int row = sc.nextInt();

        System.out.print("Enter first Queen's column (1 to " + N + "): ");
        int col = sc.nextInt();

        if (row < 1 || row > N || col < 1 || col > N) {
            System.out.println("Invalid position!");
            sc.close();
            return;
        }

        row--;
        col--;

        board[row][col] = 1;

        if (!isSafe(row, col)) {
            System.out.println("Invalid initial Queen placement!");
            sc.close();
            return;
        }

        System.out.println("\nInitial N-Queens Matrix:");
        printBoard();

        if (solveNQueens(0)) {
            System.out.println("\nFinal N-Queens Matrix:");
            printBoard();
        } else {
            System.out.println(
                    "\nNo solution exists for this initial Queen position.");
        }

        sc.close();
    }
}

/*
 * ========================================================
 * TIME AND SPACE COMPLEXITY ANALYSIS
 * N-QUEENS USING RECURSIVE BACKTRACKING
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
 * STEP 2: Check initial queen position
 * --------------------------------
 * The isSafe() method checks three directions:
 * 
 * 1. Same column.
 * 2. Upper-left diagonal.
 * 3. Upper-right diagonal.
 * 
 * Each loop takes at most N iterations.
 * 
 * Time Complexity = O(N)
 * 
 * 
 * STEP 3: isSafe() method
 * --------------------------------
 * For every queen placement attempt, the program
 * checks the column and both upper diagonals.
 * 
 * Maximum operations are proportional to 3N.
 * 
 * Time Complexity = O(N)
 * 
 * 
 * STEP 4: solveNQueens() method
 * --------------------------------
 * The algorithm places one queen in each row.
 * 
 * For every row, it tries at most N columns.
 * 
 * For each attempted position, isSafe() takes O(N).
 * 
 * The algorithm explores different arrangements
 * using recursion and backtracking.
 * 
 * The number of possible arrangements is bounded
 * by a factorial growth of N!.
 * 
 * There are at most N choices for each row.
 * 
 * Therefore, a conservative worst-case upper bound is:
 * 
 * Time Complexity = O(N^2 * N!)
 * 
 * 
 * STEP 5: Print chessboard
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
 * Initial safety check = O(N)
 * Recursive search = O(N^2 * N!)
 * Printing board = O(N^2)
 * 
 * The recursive search dominates.
 * 
 * FINAL TIME COMPLEXITY = O(N^2 * N!)
 * 
 * 
 * SPACE COMPLEXITY
 * --------------------------------
 * 1. Chessboard array:
 * N x N = O(N^2)
 * 
 * 2. Recursive call stack:
 * Maximum recursion depth = N
 * Space = O(N)
 * 
 * 3. Other variables:
 * Constant space = O(1)
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
