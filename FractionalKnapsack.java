
import java.util.*;

public class FractionalKnapsack {

    // Class to represent an item
    static class Item {
        int profit;
        int weight;
        double ratio;

        Item(int profit, int weight) {
            this.profit = profit;
            this.weight = weight;
            this.ratio = (double) profit / weight;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Step 1: Input number of items
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];

        // Step 2: Input profit and weight
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Item " + (i + 1));

            System.out.print("Enter profit: ");
            int profit = sc.nextInt();

            System.out.print("Enter weight: ");
            int weight = sc.nextInt();

            items[i] = new Item(profit, weight);
        }

        // Step 3: Input knapsack capacity
        System.out.print("\nEnter capacity of knapsack: ");
        int capacity = sc.nextInt();

        // Step 4: Sort items by profit/weight ratio
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                return Double.compare(b.ratio, a.ratio);
            }
        });

        // Step 5: Display sorted items
        System.out.println("\nItems sorted according to Profit/Weight ratio:");
        System.out.println("Item\tProfit\tWeight\tRatio");

        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t%d\t%.2f%n",
                    i + 1,
                    items[i].profit,
                    items[i].weight,
                    items[i].ratio);
        }

        // Step 6: Apply Greedy Method
        double maxProfit = 0.0;
        int remainingCapacity = capacity;

        System.out.println("\nItems selected:");

        for (int i = 0; i < n; i++) {

            if (remainingCapacity == 0) {
                break;
            }

            // If complete item can fit
            if (items[i].weight <= remainingCapacity) {

                remainingCapacity -= items[i].weight;
                maxProfit += items[i].profit;

                System.out.printf(
                        "Item %d -> 100%% selected, Profit = %d%n",
                        i + 1,
                        items[i].profit);
            }

            // Otherwise take fractional part
            else {

                double fraction = (double) remainingCapacity / items[i].weight;

                double profitTaken = items[i].profit * fraction;

                maxProfit += profitTaken;

                System.out.printf(
                        "Item %d -> %.2f%% selected, Profit = %.2f%n",
                        i + 1,
                        fraction * 100,
                        profitTaken);

                remainingCapacity = 0;
            }
        }

        // Step 7: Display result
        System.out.println("\n--------------------------------");
        System.out.println("Fractional Knapsack Result");
        System.out.println("--------------------------------");

        System.out.println("Knapsack Capacity : " + capacity);
        System.out.println("Remaining Capacity: " + remainingCapacity);

        System.out.printf("Maximum Profit     : %.2f%n", maxProfit);

        sc.close();
    }
}



/*
 * ========================================================
 * TIME AND SPACE COMPLEXITY ANALYSIS
 * FRACTIONAL KNAPSACK USING GREEDY METHOD
 * ========================================================
 * 
 * Let:
 * n = Total number of items.
 * 
 * STEP 1: Input number of items
 * --------------------------------
 * Reading the number of items takes constant time.
 * Time Complexity = O(1)
 * 
 * 
 * STEP 2: Input profit and weight of all items
 * --------------------------------
 * A for loop runs n times.
 * Each iteration takes O(1) time.
 * 
 * Time Complexity = n * O(1)
 * = O(n)
 * 
 * 
 * STEP 3: Calculate profit/weight ratio
 * --------------------------------
 * The ratio is calculated inside the Item constructor.
 * Each item requires one division operation.
 * 
 * For n items:
 * Time Complexity = O(n)
 * 
 * 
 * STEP 4: Sort items by profit/weight ratio
 * --------------------------------
 * Arrays.sort() sorts the items using a comparator.
 * Java uses a sorting algorithm with O(n log n)
 * time complexity for object arrays.
 * 
 * Time Complexity = O(n log n)
 * 
 * 
 * STEP 5: Display sorted items
 * --------------------------------
 * A for loop runs n times.
 * Each iteration takes O(1) time.
 * 
 * Time Complexity = O(n)
 * 
 * 
 * STEP 6: Apply Greedy Method
 * --------------------------------
 * A for loop checks each item at most once.
 * Each iteration performs constant-time operations.
 * 
 * Time Complexity = n * O(1)
 * = O(n)
 * 
 * 
 * STEP 7: Display final result
 * --------------------------------
 * Printing the final result takes constant time.
 * 
 * Time Complexity = O(1)
 * 
 * 
 * OVERALL TIME COMPLEXITY
 * --------------------------------
 * T(n) = O(1) + O(n) + O(n) + O(n log n)
 * + O(n) + O(n) + O(1)
 * 
 * T(n) = O(n log n + n)
 * 
 * Ignoring lower-order terms:
 * 
 * FINAL TIME COMPLEXITY = O(n log n)
 * 
 * 
 * SPACE COMPLEXITY
 * --------------------------------
 * 1. Item array stores n items = O(n)
 * 2. Each Item object stores fields = O(1)
 * per item, therefore = O(n)
 * 3. Sorting auxiliary space = O(n) worst case
 * for Java object-array sorting.
 * 4. Other variables = O(1)
 * 
 * FINAL SPACE COMPLEXITY = O(n)
 * 
 * 
 * ========================================================
 * FINAL ANSWER:
 * Time Complexity = O(n log n)
 * Space Complexity = O(n)
 * ========================================================
 */
