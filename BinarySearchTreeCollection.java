import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BinarySearchTreeCollection {
    private ArrayList<BinarySearchTree> trees;   // private method for the list of binary search tree objects 

    public BinarySearchTreeCollection() {  // our constructor that intializes the list of trees 
        trees = new ArrayList<>();
    }

    public void addTree(BinarySearchTree tree) {  // method to add a new tree to the collection 
        trees.add(tree);
    }

    public BinarySearchTree getTree(int index) {    // method to get a tree from the collectoin by index
        if (index < 0 || index >= trees.size()) {
            throw new IllegalArgumentException("Invalid argument!");  // if index is invalid, throw exception
        }
        return trees.get(index);
    }

    public void deleteTree(int index) {     // method to delete a tree by index 
        if (index < 0 || index >= trees.size()) {
            throw new IllegalArgumentException("Invalid argument!");  // if index is invalid, throw exception 
        }
        trees.remove(index);
    }

    public int getNumberOfTrees() {    // to get number of trees in the collection 
        return trees.size();
    }

    public boolean areStructurallyEquivalent(int[] indices) {    // method to check if trees at a specified index are structurally equivalent 
        if (indices.length < 2) {
            throw new IllegalArgumentException("Invalid argument!"); // throw exception of index are invalid 
        }

        for (int index : indices) {            // check if each index is valid 
            if (index < 0 || index >= trees.size()) {
                throw new IllegalArgumentException("Invalid argument!");
            }
        }

        BinarySearchTree firstTree = trees.get(indices[0]);    // compares first tree with others 
        for (int i = 1; i < indices.length; i++) {
            if (!isStructurallyEquivalent(firstTree.root, trees.get(indices[i]).root)) {
                return false;
            }
        }
        return true;
    }

    private boolean isStructurallyEquivalent(TreeNode node1, TreeNode node2) {   // method to check strucutral equivalence of 2 trees 
        if (node1 == null && node2 == null) {
            return true;                           // if both nodes are null, they are equvalient 
        }
        if (node1 == null || node2 == null) {     // if one is null and the other isn't they are not equvalient 
            return false;
        }
        if (node1.value != node2.value) {  // if values are different, they aren't equivalent 
            return false;
        }
        return isStructurallyEquivalent(node1.leftChild, node2.leftChild) &&        
                isStructurallyEquivalent(node1.rightChild, node2.rightChild);
    }

    public void merge(int[] indices) {         // method to merge trees at specified indices 
        if (indices.length < 2) {
            throw new IllegalArgumentException("Invalid argument!"); // if there are less than 2 indices, throw an exception 
        }

        for (int index : indices) {       // check if index is valid 
            if (index < 0 || index >= trees.size()) {  
                throw new IllegalArgumentException("Invalid argument!");
            }
        }

        Set<Integer> mergedValues = new HashSet<>();         // collects all values from specified trees 
        for (int index : indices) {
            collectValues(trees.get(index).root, mergedValues);
        }

        ArrayList<Integer> sortedValues = new ArrayList<>(mergedValues);   // sorts the values and creates a new merged tree 
        Collections.sort(sortedValues);

        BinarySearchTree mergedTree = new BinarySearchTree();   
        for (int value : sortedValues) {
            mergedTree.addNode(value);
        }

        for (int i = indices.length - 1; i >= 0; i--) {     // removes the original trees and adds the new merged tree 
            deleteTree(indices[i]);
        }

        addTree(mergedTree);
    }

    private void collectValues(TreeNode node, Set<Integer> values) {  // method to collect values from tree 
        if (node != null) {
            values.add(node.value);
            collectValues(node.leftChild, values);
            collectValues(node.rightChild, values);
        }
    }

    public int getTotalNodes() {        // method to get the total number of nodes in every tree in the collection 
        int totalNodes = 0;
        for (BinarySearchTree tree : trees) {
            totalNodes += tree.getNodeCount();
        }
        return totalNodes;
    }
}
