import java.util.*;

class KnapsackItem {
    int weight, profit;
    double ratio;

    KnapsackItem(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = (double) profit / weight;
    }
}

class Node {
    int level, weight, profit;
    double bound;

    Node(int level, int weight, int profit) {
        this.level = level;
        this.weight = weight;
        this.profit = profit;
    }
}

public class KnapsackBranchBound {

    static int capacity;
    static KnapsackItem[] items;

    // Calculate upper bound using fractional knapsack
    static double calculateBound(Node node) {
        if (node.weight >= capacity)
            return node.weight == capacity ? node.profit : 0;

        double bound = node.profit;
        int totalWeight = node.weight;
        int j = node.level + 1;

        while (j < items.length &&
                totalWeight + items[j].weight <= capacity) {
            totalWeight += items[j].weight;
            bound += items[j].profit;
            j++;
        }

        if (j < items.length) {
            bound += (capacity - totalWeight) * items[j].ratio;
        }

        return bound;
    }

    static int knapsack(int n, int W) {

        capacity = W;

        // Sort items by decreasing profit-to-weight ratio
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        PriorityQueue<Node> queue = new PriorityQueue<>(
                (a, b) -> Double.compare(b.bound, a.bound));

        Node root = new Node(-1, 0, 0);
        root.bound = calculateBound(root);
        queue.add(root);

        int maxProfit = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.bound <= maxProfit)
                continue;

            int nextLevel = current.level + 1;

            if (nextLevel >= n)
                continue;

            // Case 1: Include the next item

            Node include = new Node(
                    nextLevel,
                    current.weight + items[nextLevel].weight,
                    current.profit + items[nextLevel].profit);

            if (include.weight <= capacity) {
                maxProfit = Math.max(maxProfit, include.profit);

                include.bound = calculateBound(include);

                if (include.bound > maxProfit)
                    queue.add(include);
            }

            // Case 2: Exclude the next item
            Node exclude = new Node(
                    nextLevel,
                    current.weight,
                    current.profit);

            exclude.bound = calculateBound(exclude);

            if (exclude.bound > maxProfit)
                queue.add(exclude);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        items = new KnapsackItem[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and profit of item "
                    + (i + 1) + ": ");
            int weight = sc.nextInt();
            int profit = sc.nextInt();

            items[i] = new KnapsackItem(weight, profit);
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        int result = knapsack(n, W);

        System.out.println("Maximum Profit = " + result);

        sc.close();

    }
}






/*
 * ========================================================
 * TIME AND SPACE COMPLEXITY ANALYSIS
 * 0/1 KNAPSACK USING BRANCH AND BOUND
 * ========================================================
 * 
 * Let:
 * n = Total number of items.
 * W = Maximum capacity of the knapsack.
 * 
 * STEP 1: Input items
 * --------------------------------
 * A loop runs n times to accept weight and profit.
 * Each iteration takes O(1) time.
 * 
 * Time Complexity = O(n)
 * 
 * 
 * STEP 2: Calculate profit/weight ratio
 * --------------------------------
 * Ratio is calculated once for every item
 * inside the constructor.
 * 
 * For n items:
 * Time Complexity = O(n)
 * 
 * 
 * STEP 3: Sort items by decreasing ratio
 * --------------------------------
 * Arrays.sort() sorts n items.
 * 
 * Time Complexity = O(n log n)
 * 
 * 
 * STEP 4: Calculate Bound
 * --------------------------------
 * The calculateBound() method may traverse
 * all remaining items in the worst case.
 * 
 * Time Complexity = O(n)
 * 
 * 
 * STEP 5: Priority Queue operations
 * --------------------------------
 * Priority Queue insertion (add) takes O(log n).
 * Priority Queue deletion (poll) takes O(log n).
 * 
 * In the worst case, Branch and Bound may explore
 * up to 2^n possible nodes.
 * 
 * Therefore, total Priority Queue operations
 * may take O(2^n log(2^n)).
 * 
 * = O(2^n * n)
 * 
 * 
 * STEP 6: Branch and Bound exploration
 * --------------------------------
 * For each node, the algorithm generates at most
 * two child nodes:
 * 1. Include the next item.
 * 2. Exclude the next item.
 * 
 * The total number of possible nodes in the
 * decision tree is:
 * 
 * 1 + 2 + 4 + 8 + ... + 2^n
 * 
 * = 2^(n+1) - 1
 * 
 * Therefore, worst-case number of nodes = O(2^n).
 * 
 * Each node may call calculateBound(), which
 * takes O(n) time in the worst case.
 * 
 * Total bound calculation time:
 * 
 * = O(n * 2^n)
 * 
 * 
 * OVERALL TIME COMPLEXITY
 * --------------------------------
 * Sorting = O(n log n)
 * Node exploration = O(2^n)
 * Priority Queue = O(n * 2^n)
 * Bound calculation = O(n * 2^n)
 * 
 * Therefore:
 * 
 * FINAL TIME COMPLEXITY = O(n * 2^n)
 * 
 * 
 * SPACE COMPLEXITY
 * --------------------------------
 * 1. Items array stores n items = O(n)
 * 
 * 2. Priority Queue may store up to
 * O(2^n) nodes in the worst case = O(2^n)
 * 
 * 3. Each Node stores a fixed number
 * of variables = O(1)
 * 
 * 4. Other variables = O(1)
 * 
 * Therefore:
 * 
 * FINAL SPACE COMPLEXITY = O(2^n)
 * 
 * 
 * ========================================================
 * FINAL ANSWER:
 * Time Complexity = O(n * 2^n)
 * Space Complexity = O(2^n)
 * ========================================================
 */



/*
========================================================
SAMPLE INPUT
========================================================

Enter number of items: 4

Enter weight and profit of item 1: 2 40
Enter weight and profit of item 2: 3 50
Enter weight and profit of item 3: 4 60
Enter weight and profit of item 4: 5 70

Enter knapsack capacity: 8


========================================================
EXPECTED OUTPUT
========================================================

Maximum Profit = 130

========================================================
*/
