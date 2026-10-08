
package com.careercompass.ds;

import java.util.ArrayList;
import java.util.List;

import com.careercompass.model.College;

public class CollegeAVLTree {

    private static class Node {
        College college;
        int height;
        Node left, right;

        Node(College college) {
            this.college = college;
            this.height = 1;
        }
    }

    private Node root;

    public void insert(College college) {
        root = insert(root, college);
    }

    private Node insert(Node node, College college) {
        if (node == null) return new Node(college);

        if (college.getAnnualFee() < node.college.getAnnualFee()) {
            node.left = insert(node.left, college);
        } else {
            node.right = insert(node.right, college);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node, college.getAnnualFee());
    }

    public List<College> searchWithinBudget(double maxBudget) {
        List<College> result = new ArrayList<>();
        inOrderRange(root, maxBudget, result);
        return result;
    }

    private void inOrderRange(Node node, double maxBudget, List<College> result) {
        if (node == null) return;

        inOrderRange(node.left, maxBudget, result);
        if (node.college.getAnnualFee() <= maxBudget) {
            result.add(node.college);
            inOrderRange(node.right, maxBudget, result);
        }
    }

    private int height(Node n) { return n == null ? 0 : n.height; }
    private int getBalance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }

    private Node balance(Node node, double fee) {
        int balance = getBalance(node);
        if (balance > 1 && fee < node.left.college.getAnnualFee()) return rotateRight(node);
        if (balance < -1 && fee > node.right.college.getAnnualFee()) return rotateLeft(node);
        if (balance > 1 && fee > node.left.college.getAnnualFee()) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && fee < node.right.college.getAnnualFee()) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = x.right;
        x.right = y;
        y.left = t2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        y.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node t2 = y.left;
        y.left = x;
        x.right = t2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
}