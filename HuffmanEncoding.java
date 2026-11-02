
import java.util.*;

class HuffmanCoding {

    // Node of Huffman Tree
    static class Node {
        char ch;
        int freq;
        Node left, right;

        // Constructor for leaf node
        Node(char character, int frequency) {
            ch = character;
            freq = frequency;
            left = null;
            right = null;
        }

        // Constructor for internal node
        Node(int frequency, Node leftChild, Node rightChild) {
            ch = '\0';
            freq = frequency;
            left = leftChild;
            right = rightChild;
        }
    }

    private Node root;
    private HashMap<Character, String> huffmanCode;

    // Constructor
    HuffmanCoding() {
        root = null;
        huffmanCode = new HashMap<>();
    }

    // Comparator for Min Heap
    static class Compare implements Comparator<Node> {
        public int compare(Node a, Node b) {
            if (a.freq == b.freq) {
                return Character.compare(a.ch, b.ch);
            }
            return Integer.compare(a.freq, b.freq);
        }
    }

    // Generate Huffman Codes
    private void generateCodes(Node node, String code) {

        if (node == null)
            return;

        // Leaf node
        if (node.left == null && node.right == null) {

            // Special case: single unique character
            if (code.isEmpty())
                code = "0";

            huffmanCode.put(node.ch, code);
            return;
        }

        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    // Delete Tree
    private void deleteTree() {
        root = null;
        huffmanCode.clear();
    }

    // Display character properly
    private void displayCharacter(char ch) {
        if (ch == ' ')
            System.out.print("[space]");
        else if (ch == '\n')
            System.out.print("[newline]");
        else if (ch == '\t')
            System.out.print("[tab]");
        else
            System.out.print(ch);
    }

    // Build Huffman Tree
    public void buildTree(String text) {

        if (text.isEmpty()) {
            System.out.println("\nError: Text cannot be empty.");
            return;
        }

        // Clear previous tree and codes
        deleteTree();

        // Count character frequencies
        HashMap<Character, Integer> frequency = new HashMap<>();

        for (char ch : text.toCharArray()) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }

        // Min Heap
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new Compare());

        // Create leaf nodes
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            minHeap.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Build Huffman Tree
        while (minHeap.size() > 1) {

            Node left = minHeap.poll();
            Node right = minHeap.poll();

            Node parent = new Node(
                    left.freq + right.freq,
                    left,
                    right);

            minHeap.add(parent);
        }

        root = minHeap.poll();

        // Generate Huffman Codes
        generateCodes(root, "");

        System.out.println("\nHuffman Tree built successfully.");

        System.out.println("\nCharacter Frequencies and Huffman Codes:");
        System.out.println("------------------------------------------");

        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {

            System.out.print("Character: ");
            displayCharacter(entry.getKey());

            System.out.print("  Frequency: " + entry.getValue());
            System.out.println("  Code: " +
                    huffmanCode.get(entry.getKey()));
        }
    }

    // Encode Text
    public String encode(String text) {

        if (root == null) {
            System.out.println("\nError: Build the Huffman Tree first.");
            return "";
        }

        StringBuilder encodedText = new StringBuilder();

        for (char ch : text.toCharArray()) {

            if (!huffmanCode.containsKey(ch)) {
                System.out.print("\nError: Character ");
                displayCharacter(ch);
                System.out.println(" is not present in the Huffman Tree.");
                return "";
            }

            encodedText.append(huffmanCode.get(ch));
        }

        return encodedText.toString();
    }

    // Decode Binary String
    public String decode(String encodedText) {

        if (root == null) {
            System.out.println("\nError: Build the Huffman Tree first.");
            return "";
        }

        if (encodedText.isEmpty())
            return "";

        // Special case: single unique character
        if (root.left == null && root.right == null) {

            for (char bit : encodedText.toCharArray()) {

                if (bit != '0') {
                    System.out.println("\nError: Invalid encoded data.");
                    return "";
                }
            }

            return String.valueOf(root.ch).repeat(encodedText.length());
        }

        Node current = root;
        StringBuilder decodedText = new StringBuilder();

        for (char bit : encodedText.toCharArray()) {

            if (bit != '0' && bit != '1') {
                System.out.println(
                        "\nError: Encoded text must contain only 0 and 1.");
                return "";
            }

            if (bit == '0')
                current = current.left;
            else
                current = current.right;

            // Invalid path
            if (current == null) {
                System.out.println("\nError: Invalid encoded data.");
                return "";
            }

            // Leaf node
            if (current.left == null && current.right == null) {

                decodedText.append(current.ch);
                current = root;
            }
        }

        // Incomplete encoded data
        if (current != root) {
            System.out.println("\nError: Incomplete encoded data.");
            return "";
        }

        return decodedText.toString();
    }

    // Display Huffman Codes
    public void displayCodes() {

        if (root == null) {
            System.out.println("\nHuffman Tree has not been built yet.");
            return;
        }

        System.out.println("\nHuffman Codes:");
        System.out.println("--------------------------");

        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {

            System.out.print("Character: ");
            displayCharacter(entry.getKey());

            System.out.println("  Code: " + entry.getValue());
        }
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HuffmanCoding huffman = new HuffmanCoding();

        int choice;
        String text;
        String encodedText;

        do {

            System.out.println("\n=================================");
            System.out.println("         HUFFMAN ENCODING");
            System.out.println("=================================");
            System.out.println("1. Build Huffman Tree");
            System.out.println("2. Encode the Text");
            System.out.println("3. Decode the Text");
            System.out.println("4. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print(
                            "\nEnter text to build Huffman Tree: ");

                    text = sc.nextLine();

                    huffman.buildTree(text);
                    break;

                case 2:

                    System.out.print("\nEnter text to encode: ");

                    text = sc.nextLine();

                    encodedText = huffman.encode(text);

                    if (!encodedText.isEmpty()) {
                        System.out.println(
                                "\nEncoded Text: " + encodedText);
                    }
                    break;

                case 3:

                    System.out.print(
                            "\nEnter encoded binary text: ");

                    encodedText = sc.nextLine();

                    String decodedText = huffman.decode(encodedText);

                    System.out.println(
                            "\nDecoded Text: " + decodedText);
                    break;

                case 4:

                    System.out.println(
                            "\nProgram terminated successfully.");
                    break;

                default:

                    System.out.println(
                            "\nInvalid choice. Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}

/*
 * ========================================================
 * TIME AND SPACE COMPLEXITY ANALYSIS
 * HUFFMAN CODING USING GREEDY ALGORITHM
 * ========================================================
 * 
 * Let:
 * n = Total number of characters in the input text.
 * k = Number of unique characters in the text.
 * L = Length of the encoded binary string.
 * 
 * STEP 1: Count character frequencies
 * --------------------------------
 * A loop traverses the input text once.
 * Each character frequency is updated using HashMap.
 * 
 * Number of iterations = n
 * 
 * Time Complexity = O(n)
 * Space Complexity = O(k)
 * 
 * 
 * STEP 2: Create leaf nodes
 * --------------------------------
 * A leaf node is created for every unique character.
 * 
 * Number of leaf nodes = k
 * 
 * Each insertion into PriorityQueue takes O(log k).
 * 
 * Total Time Complexity = O(k log k)
 * Space Complexity = O(k)
 * 
 * 
 * STEP 3: Build Huffman Tree
 * --------------------------------
 * The while loop runs k - 1 times.
 * 
 * In each iteration:
 * 1. Remove two minimum-frequency nodes.
 * 2. Create one parent node.
 * 3. Insert the parent node into Min Heap.
 * 
 * Each poll() and add() operation takes O(log k).
 * 
 * Time Complexity = O(k log k)
 * 
 * Space Complexity = O(k)
 * 
 * 
 * STEP 4: Generate Huffman Codes
 * --------------------------------
 * The generateCodes() method traverses the Huffman tree.
 * 
 * A full binary Huffman tree contains:
 * 2k - 1 nodes.
 * 
 * Each node is visited once.
 * 
 * Time Complexity = O(k)
 * 
 * The recursive call stack requires space
 * proportional to the tree height.
 * 
 * Worst-case Space Complexity = O(k)
 * 
 * 
 * STEP 5: Encode Text
 * --------------------------------
 * A loop traverses all n input characters.
 * Each character's code is retrieved and appended.
 * 
 * If L is the total encoded length:
 * 
 * Time Complexity = O(n + L)
 * Space Complexity = O(L)
 * 
 * Note: HashMap lookup takes O(1) average time.
 * 
 * 
 * STEP 6: Decode Text
 * --------------------------------
 * A loop traverses all L encoded bits.
 * Each bit moves through the Huffman tree.
 * 
 * Each bit takes O(1) time.
 * 
 * Time Complexity = O(L)
 * 
 * The decoded string contains at most L characters.
 * 
 * Space Complexity = O(n) for the decoded output,
 * where n is the decoded text length.
 * 
 * 
 * STEP 7: Display Huffman Codes
 * --------------------------------
 * A loop traverses all k unique characters.
 * 
 * Time Complexity = O(k)
 * 
 * 
 * OVERALL TIME COMPLEXITY
 * --------------------------------
 * Frequency counting = O(n)
 * Creating Min Heap = O(k log k)
 * Building Tree = O(k log k)
 * Generating Codes = O(k)
 * Encoding = O(n + L)
 * Decoding = O(L)
 * 
 * Tree construction:
 * 
 * O(n + k log k)
 * 
 * Encoding and decoding depend on the input/output
 * lengths.
 * 
 * OVERALL BUILD TIME COMPLEXITY = O(n + k log k)
 * 
 * 
 * SPACE COMPLEXITY
 * --------------------------------
 * 1. Frequency HashMap = O(k)
 * 2. Huffman Tree = O(k)
 * 3. Huffman Code Map = O(k) entries
 * 4. Min Heap = O(k)
 * 5. Recursion Stack = O(k) worst case
 * 
 * Auxiliary Space = O(k)
 * 
 * Encoding output = O(L)
 * Decoding output = O(n)
 * 
 * ========================================================
 * FINAL ANSWER:
 * Tree Building Time = O(n + k log k)
 * Encoding Time = O(n + L)
 * Decoding Time = O(L)
 * Auxiliary Space = O(k)
 * ========================================================
 */
