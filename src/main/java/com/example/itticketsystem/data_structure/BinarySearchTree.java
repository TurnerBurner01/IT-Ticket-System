package com.example.itticketsystem.data_structure;

import com.example.itticketsystem.model.Ticket;

public class BinarySearchTree {

    // Node class to represent a ticket
    static class Node {
        Ticket ticket;
        Node left, right;

        public Node(Ticket ticket) {
            this.ticket = ticket;
            this.left = this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Insert ticket into the BST
    public void insert(Ticket ticket) {
        root = insertRec(root, ticket);
    }

    // Recursive insert method
    private Node insertRec(Node root, Ticket ticket) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(ticket);
            return root;
        }

        // Otherwise, recur down the tree
        if (ticket.getPriority() < root.ticket.getPriority()) {
            root.left = insertRec(root.left, ticket);
        } else if (ticket.getPriority() > root.ticket.getPriority()) {
            root.right = insertRec(root.right, ticket);
        }

        // return the (unchanged) node pointer
        return root;
    }

    // In-order traversal of the tree
    public Ticket[] inOrder() {
        // Store the tickets in an array and return it
        Ticket[] tickets = new Ticket[getSize()];
        inOrderRec(root, tickets, new int[]{0});
        return tickets;
    }

    // Helper method for in-order traversal
    private void inOrderRec(Node root, Ticket[] tickets, int[] index) {
        if (root != null) {
            inOrderRec(root.left, tickets, index);
            tickets[index[0]++] = root.ticket;
            inOrderRec(root.right, tickets, index);
        }
    }

    // Get the size of the BST (number of nodes)
    public int getSize() {
        return getSizeRec(root);
    }

    private int getSizeRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + getSizeRec(root.left) + getSizeRec(root.right);
    }
}
