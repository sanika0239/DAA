
import java.util.Scanner;

public class Fibonacci {

    // Non-recursive (Iterative) method
    static void fibonacciIterative(int n) {
        int a = 0, b = 1;

        System.out.println("Fibonacci Series (Non-Recursive):");

        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");

            int c = a + b;
            a = b;
            b = c;
        }
        System.out.println();
    }

    // Recursive method
    static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacciRecursive(n - 1)
                + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Please enter a non-negative number.");
        } else {
            fibonacciIterative(n);

            System.out.println("Fibonacci Series (Recursive):");

            for (int i = 0; i < n; i++) {
                System.out.print(fibonacciRecursive(i) + " ");
            }
        }

        sc.close();
    }
}



/*
 * =================================================
 * TIME AND SPACE COMPLEXITY ANALYSIS
 * =================================================
 * 
 * 1. NON-RECURSIVE (ITERATIVE) FIBONACCI:
 * 
 * Time Complexity:
 * - Variable initialization takes O(1).
 * - The for loop executes n times.
 * - Each iteration performs constant-time operations.
 * - Total Time = O(1) + n * O(1).
 * - Therefore, Time Complexity = O(n).
 * 
 * Space Complexity:
 * - Uses four variables: a, b, c and i.
 * - No extra data structure is used.
 * - Memory usage remains constant.
 * - Therefore, Space Complexity = O(1).
 * 
 * 
 * 2. RECURSIVE FIBONACCI:
 * 
 * Time Complexity:
 * - Each function makes two recursive calls:
 * fibonacciRecursive(n-1) and fibonacciRecursive(n-2).
 * - This creates repeated calculations.
 * - Recurrence Relation:
 * T(n) = T(n-1) + T(n-2) + O(1).
 * - Upper-bound Time Complexity = O(2^n).
 * 
 * Space Complexity:
 * - Recursive calls are stored in the call stack.
 * - Maximum recursion depth is proportional to n.
 * - Each call requires constant memory.
 * - Therefore, Space Complexity = O(n).
 * 
 * 
 * =================================================
 * FINAL RESULT
 * =================================================
 * 
 * TIME SPACE
 * ITERATIVE O(n) O(1)
 * RECURSIVE O(2^n) O(n) [upper bound]
 * 
 * =================================================
 */