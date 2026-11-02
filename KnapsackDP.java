
import java.util.*;

public class KnapsackDP {

    static int knapsack(int n, int capacity, int[] weights, int[] profits) {

        // Create DP table
        int[][] dp = new int[n + 1][capacity + 1];

        // Fill the DP table
        for (int i = 1; i <= n; i++) {

            for (int w = 0; w <= capacity; w++) {

                // If current item can fit
                if (weights[i - 1] <= w) {

                    int include = profits[i - 1]
                            + dp[i - 1][w - weights[i - 1]];

                    int exclude = dp[i - 1][w];

                    dp[i][w] = Math.max(include, exclude);

                } else {

                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Find selected items
        ArrayList<Integer> selectedItems = new ArrayList<>();

        int w = capacity;

        for (int i = n; i > 0; i--) {

            if (dp[i][w] != dp[i - 1][w]) {

                selectedItems.add(i);
                w = w - weights[i - 1];
            }
        }

        Collections.reverse(selectedItems);

        System.out.println("\nSelected Items: " + selectedItems);

        return dp[n][capacity];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("0/1 Knapsack using Dynamic Programming");

        // Input number of items
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] profits = new int[n];

        // Input item details
        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details of Item " + (i + 1));

            System.out.print("Enter weight: ");
            weights[i] = sc.nextInt();

            System.out.print("Enter profit: ");
            profits[i] = sc.nextInt();

            if (weights[i] <= 0 || profits[i] < 0) {
                System.out.println("Invalid weight or profit!");
                sc.close();
                return;
            }
        }

        // Input capacity
        System.out.print("\nEnter knapsack capacity: ");
        int capacity = sc.nextInt();

        if (capacity < 0) {
            System.out.println("Capacity cannot be negative!");
            sc.close();
            return;
        }

        // Calculate maximum profit
        int result = knapsack(n, capacity, weights, profits);

        System.out.println("Maximum Profit = " + result);

        sc.close();
    }
}

/*
 * ========================================================
 * TIME AND SPACE COMPLEXITY ANALYSIS
 * 0/1 KNAPSACK USING DYNAMIC PROGRAMMING
 * ========================================================
 * 
 * Let:
 * n = Total number of items.
 * W = Maximum capacity of the knapsack.
 * 
 * STEP 1: Input items
 * --------------------------------
 * Two arrays are used to store weights and profits.
 * A loop runs n times.
 * 
 * Time Complexity = O(n)
 * 
 * 
 * STEP 2: Create DP table
 * --------------------------------
 * A two-dimensional array is created:
 * 
 * int[][] dp = new int[n + 1][W + 1];
 * 
 * Number of rows = n + 1
 * Number of columns = W + 1
 * 
 * Total cells = (n + 1) * (W + 1)
 * 
 * Time Complexity for initialization = O(n * W)
 * Space Complexity = O(n * W)
 * 
 * 
 * STEP 3: Fill the DP table
 * --------------------------------
 * The outer loop runs n times.
 * The inner loop runs W + 1 times.
 * 
 * For every cell, we perform constant-time operations:
 * 1. Check whether the item can fit.
 * 2. Calculate include profit.
 * 3. Calculate exclude profit.
 * 4. Find maximum using Math.max().
 * 
 * Total operations = n * (W + 1)
 * 
 * Time Complexity = O(n * W)
 * 
 * 
 * STEP 4: Find selected items
 * --------------------------------
 * A loop runs from n to 1.
 * In each iteration, we check whether the item
 * was selected and update the remaining capacity.
 * 
 * Time Complexity = O(n)
 * 
 * 
 * STEP 5: Reverse selected items
 * --------------------------------
 * Collections.reverse() reverses the selected items.
 * At most n items can be selected.
 * 
 * Time Complexity = O(n)
 * 
 * 
 * STEP 6: Display maximum profit
 * --------------------------------
 * Accessing dp[n][W] takes constant time.
 * 
 * Time Complexity = O(1)
 * 
 * 
 * OVERALL TIME COMPLEXITY
 * --------------------------------
 * Input = O(n)
 * DP table initialization = O(n * W)
 * Filling DP table = O(n * W)
 * Finding selected items = O(n)
 * Reversing selected items = O(n)
 * 
 * T(n, W) = O(n) + O(n * W) + O(n * W)
 * + O(n) + O(n)
 * 
 * Ignoring lower-order terms:
 * 
 * FINAL TIME COMPLEXITY = O(n * W)
 * 
 * 
 * SPACE COMPLEXITY
 * --------------------------------
 * 1. Weight array = O(n)
 * 2. Profit array = O(n)
 * 3. DP table = O(n * W)
 * 4. Selected items list = O(n)
 * 
 * Total Space = O(n * W + n)
 * 
 * Ignoring lower-order terms:
 * 
 * FINAL SPACE COMPLEXITY = O(n * W)
 * 
 * 
 * ========================================================
 * FINAL ANSWER:
 * Time Complexity = O(n * W)
 * Space Complexity = O(n * W)
 * 
 * Note: W represents the numerical capacity of the knapsack.
 * ========================================================
 */
