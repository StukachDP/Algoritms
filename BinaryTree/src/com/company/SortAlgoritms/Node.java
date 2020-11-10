package com.company.SortAlgoritms;

public class Node {

    int value;
    Node leftBranch;
    Node rightBranch;


    Node(int value) {
        this.value = value;
        rightBranch = null;
        leftBranch = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + leftBranch +
                ", right=" + rightBranch +
                '}';
    }
}
