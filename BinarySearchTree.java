import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    public TreeNode root;

    public void addNode(int value) {       // method to add node to tree 
        root = addRecursive(root, value);
    }

    private TreeNode addRecursive(TreeNode current, int value) {  // method to add a node using recursion
        if (current == null) {             // if the current node is null, create a new nodeee
            return new TreeNode(value); 
        }

        if (value < current.value) {            // if the value is less than the current node's value,  we add to the left subtree
            current.leftChild = addRecursive(current.leftChild, value); 
        } else if (value > current.value) {        // if the value is greater, we add to the right subtree 
            current.rightChild = addRecursive(current.rightChild, value);
        }
        return current;
    }

    public int getNodeCount() {        // method to get the count of nodes in tree 
        return getNodeCountRecursive(root);
    }

    private int getNodeCountRecursive(TreeNode node) { // method to count nodes recursively 
        if (node == null) {  // if node is null return 0 
            return 0;
        }
        return 1 + getNodeCountRecursive(node.leftChild) + getNodeCountRecursive(node.rightChild);  // count the current node plus the nodes in the left and right subtress
    }

    public ArrayList<Integer> bfsTraversal() {   // method to perform breadth first search traversal 
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodes = new LinkedList<>(); // use a queue to track nodes for our bfs 
        nodes.add(root);

        while (!nodes.isEmpty()) {        // while there are nodes to process 
            TreeNode node = nodes.remove();
            result.add(node.value);

            if (node.leftChild != null) {       // add the left and right children to queue 
                nodes.add(node.leftChild);
            }

            if (node.rightChild != null) {
                nodes.add(node.rightChild);
            }
        }

        return result;
    }

    public boolean containsNode(int value) {  // method to check if a node with a specific value exists in the tree 
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(TreeNode current, int value) {     // method to check for a node recursively 
        if (current == null) {
            return false;
        }
        if (value == current.value) {    // if current nodes value matches, then value is found
            return true;
        }
        return value < current.value     // recursively serach in the left or right subtree
                ? containsNodeRecursive(current.leftChild, value)
                : containsNodeRecursive(current.rightChild, value);
    }

    public int getHeight() {          // to get the height of the tree 
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(TreeNode node) {         // method to calculate the height of the tree recursively 
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeightRecursive(node.leftChild), getHeightRecursive(node.rightChild));  // height is 1 plus the max height of the left and right subtrees 
    }
}
