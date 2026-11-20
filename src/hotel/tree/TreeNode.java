package hotel.tree;

import hotel.model.Employee;

// ============================================================
// TreeNode
// One node of the hierarchy. It stores an employee (Name,
// Designation and Department) and at most two children.
// ============================================================
public class TreeNode {

    private final Employee employee;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
