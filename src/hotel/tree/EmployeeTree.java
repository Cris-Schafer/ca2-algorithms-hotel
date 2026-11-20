package hotel.tree;

import hotel.model.Employee;
import java.util.LinkedList;
import java.util.Queue;

// ============================================================
// EmployeeTree
// Binary tree of the hotel hierarchy. Employees are inserted in
// level order (top to bottom, left to right), so each node has
// at most two children and the highest ranks stay near the root.
// ============================================================
public class EmployeeTree {

    private TreeNode root;

    public boolean isEmpty() {
        return root == null;
    }

    // Level-order insertion: the new node goes into the first free place found.
    public void insert(Employee employee) {
        TreeNode newNode = new TreeNode(employee);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();

            if (current.getLeft() == null) {
                current.setLeft(newNode);
                return;
            }
            if (current.getRight() == null) {
                current.setRight(newNode);
                return;
            }
            queue.add(current.getLeft());
            queue.add(current.getRight());
        }
    }

    // Level-order traversal: prints the hierarchy one level at a time.
    public void printLevelOrder() {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int nodesInThisLevel = queue.size(); // everything in the queue now belongs to this level
            System.out.println("Level " + level + ":");

            for (int i = 0; i < nodesInThisLevel; i++) {
                TreeNode current = queue.remove();
                Employee employee = current.getEmployee();
                System.out.println("  " + employee.getName()
                        + " | " + employee.getDesignation()
                        + " | " + employee.getDepartment().getName());

                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }
            level++;
        }
    }

    // Height = number of levels (a tree with only the root has height 1).
    public int height() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    // Total number of nodes in the tree.
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }
}
