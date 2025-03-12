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

    /**
     * insertRoc() - Inserts nodes but also checks type & date to then place them in order for the priority
     * @param root
     * @param ticket
     * @return
     */

    // Recursive insert method with sorting criteria (Priority = Type & Date)
    private Node insertRec(Node root, Ticket ticket) {
        // if node is empty return a new node
        if (root == null) {
            return new Node(ticket);
        }

        int ticketPriority = ticket.getTypePriority();
        int rootPriority = root.ticket.getTypePriority();

        // Checks priority of the ticket
        if (ticketPriority < rootPriority) {
            root.left = insertRec(root.left, ticket);
        } else if (ticketPriority > rootPriority) {
            root.right = insertRec(root.right, ticket);
        } else {
            // If type priority is the same, compare dates
            if (ticket.getDate().compareTo(root.ticket.getDate()) < 0) {
                root.left = insertRec(root.left, ticket);
            } else {
                root.right = insertRec(root.right, ticket);
            }
        }
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

    // Method to get all tickets in the BST in sorted order
    public Ticket[] getAllTickets() {
        return inOrder(); // This uses the in-order traversal to return tickets in the correct order
    }

    // Method to update priorities after sorting
    public void updatePriorities() {
        updatePrioritiesRec(root, new int[]{1}); // Start priority from 1
    }

    // Helper method for in-order traversal and updating priorities
    private void updatePrioritiesRec(Node root, int[] currentPriority) {
        if (root != null) {
            // Traverse the left subtree first
            updatePrioritiesRec(root.left, currentPriority);

            // Update the priority of the current ticket
            root.ticket.setPriority(currentPriority[0]++);

            // Traverse the right subtree
            updatePrioritiesRec(root.right, currentPriority);
        }
    }

    // Method to delete a ticket from the BST
    public void delete(Ticket ticketToDelete) {
        root = deleteRec(root, ticketToDelete);
    }

    // Recursive delete method
    private Node deleteRec(Node root, Ticket ticketToDelete) {
        // If the tree is empty, return null
        if (root == null) {
            return root;
        }

        // Otherwise, recur down the tree
        if (ticketToDelete.getId() < root.ticket.getId()) {
            root.left = deleteRec(root.left, ticketToDelete); // Search in left subtree
        } else if (ticketToDelete.getId() > root.ticket.getId()) {
            root.right = deleteRec(root.right, ticketToDelete); // Search in right subtree
        } else {
            // Node with the ticket is found, now delete it

            // Case 1: Node has no children (leaf node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node has one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has two children
            // Get the in-order successor (smallest node in the right subtree)
            root.ticket = minValue(root.right);

            // Delete the in-order successor
            root.right = deleteRec(root.right, root.ticket);
        }

        return root;
    }

    // Get the ticket with the minimum value (used for finding in-order successor)
    private Ticket minValue(Node root) {
        Ticket minValue = root.ticket;
        while (root.left != null) {
            minValue = root.left.ticket;
            root = root.left;
        }
        return minValue;
    }

}
