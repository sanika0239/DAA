
import java.util.*;

public class TowerOfHanoi {

    // Count total moves
    static int count = 0;

    // Recursive Tower of Hanoi
    static void recursiveHanoi(int n, char source,
            char auxiliary, char destination) {

        if (n == 1) {
            count++;
            System.out.println("Move disk 1 from " +
                    source + " to " + destination);
            return;
        }

        recursiveHanoi(n - 1, source, destination, auxiliary);

        count++;
        System.out.println("Move disk " + n + " from " +
                source + " to " + destination);

        recursiveHanoi(n - 1, auxiliary, source, destination);
    }

    // Move disk between two rods (Iterative method)
    static void moveDisk(Stack<Integer> source,
            Stack<Integer> destination,
            char s, char d) {

        int disk1 = source.isEmpty()
                ? Integer.MAX_VALUE
                : source.pop();

        int disk2 = destination.isEmpty()
                ? Integer.MAX_VALUE
                : destination.pop();

        if (disk1 < disk2) {

            if (disk2 != Integer.MAX_VALUE)
                destination.push(disk2);

            destination.push(disk1);

            System.out.println("Move disk " + disk1 +
                    " from " + s + " to " + d);

        } else {

            if (disk1 != Integer.MAX_VALUE)
                source.push(disk1);

            source.push(disk2);

            System.out.println("Move disk " + disk2 +
                    " from " + d + " to " + s);
        }

        count++;
    }

    // Iterative Tower of Hanoi
    static void iterativeHanoi(int n) {

        Stack<Integer> source = new Stack<>();
        Stack<Integer> auxiliary = new Stack<>();
        Stack<Integer> destination = new Stack<>();

        char s = 'A', a = 'B', d = 'C';

        // Push disks onto source rod
        for (int i = n; i >= 1; i--) {
            source.push(i);
        }

        // For even number of disks, swap auxiliary and destination
        if (n % 2 == 0) {
            char temp = a;
            a = d;
            d = temp;
        }

        int totalMoves = (int) Math.pow(2, n) - 1;

        for (int i = 1; i <= totalMoves; i++) {

            if (i % 3 == 1) {
                moveDisk(source, destination, s, d);
            } else if (i % 3 == 2) {
                moveDisk(source, auxiliary, s, a);
            } else {
                moveDisk(auxiliary, destination, a, d);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();

        System.out.println("\n1. Recursive Method");
        System.out.println("2. Iterative Method");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        count = 0;

        if (choice == 1) {

            System.out.println("\nRecursive Tower of Hanoi:");
            recursiveHanoi(n, 'A', 'B', 'C');

        } else if (choice == 2) {

            System.out.println("\nIterative Tower of Hanoi:");
            iterativeHanoi(n);

        } else {
            System.out.println("Invalid choice!");
        }

        System.out.println("\nTotal moves = " + count);

        sc.close();
    }
}

/*
 * SAMPLE INPUT:
 * 
 * Enter number of disks: 3
 * 1. Recursive Method
 * 2. Iterative Method
 * Enter your choice: 1
 * 
 * SAMPLE OUTPUT:
 * 
 * Recursive Tower of Hanoi:
 * Move disk 1 from A to C
 * Move disk 2 from A to B
 * Move disk 1 from C to B
 * Move disk 3 from A to C
 * Move disk 1 from B to A
 * Move disk 2 from B to C
 * Move disk 1 from A to C
 * 
 * Total moves = 7
 * 
 * For iterative method, enter choice 2.
 * It will also produce 7 legal moves for 3 disks.
 * 
 * 
 * TIME AND SPACE COMPLEXITY:
 * 
 * 1. Recursive Method:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 * Reason: Each function call creates two recursive calls,
 * and the maximum recursion depth is n.
 * 
 * 2. Iterative Method:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 * Reason: It performs (2^n - 1) moves using three stacks
 * that store n disks.
 * 
 * Minimum number of moves = 2^n - 1
 */
