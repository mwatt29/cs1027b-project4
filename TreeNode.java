public class TreeNode {
    public int value;
    public TreeNode leftChild;          // our instance variables for the value and child nodes
    public TreeNode rightChild;
 
    public TreeNode(int value) {          // constructor that intializes the value and then sets children to null
        this.value = value;         
        this.leftChild = null;
        this.rightChild = null;
    }
}
