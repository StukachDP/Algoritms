package com.company.SortAlgoritms;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    Node rootOfTree;

    //Рекурсия - дело такое противное.
    private Node recursiveToAdd(Node currentTree, int value) {
        if (currentTree == null) {
            return new Node(value);
        }

        if (value < currentTree.value) {
            currentTree.leftBranch = recursiveToAdd(currentTree.leftBranch, value);
        } else if (value > currentTree.value) {
            currentTree.rightBranch = recursiveToAdd(currentTree.rightBranch, value);
        } else {
            //Если значение, которое я хочу добавить, уже есть в дереве, то я его вот и не добавлю!!!!
            return currentTree;
        }

        return currentTree;
    }

    // Используем рекурсию выше
    public void add(int value) {
        rootOfTree = recursiveToAdd(rootOfTree, value);
    }

    // Рекурсия для проверки принадлежности элемента
    private boolean isContainsRecursive(Node currentTree, int value) {
        if (currentTree == null) {
            return false;
        }
        if (value == currentTree.value) {
            return true;
        }

        if (value < currentTree.value){
            return isContainsRecursive(currentTree.leftBranch, value);
        }else {
            return isContainsRecursive(currentTree.rightBranch, value);
        }
    }

    // Используем рекурсию принадлежности
    public boolean isContainsElementInTree(int value) {
        return isContainsRecursive(rootOfTree, value);
    }


    //Смотрим налево, пишем, смотрим направо
    public void depthFirstSearchLWR(Node node) {
        if (node != null) {
            depthFirstSearchLWR(node.leftBranch);
            System.out.print(" " + node.value);
            depthFirstSearchLWR(node.rightBranch);
        }
    }


    //Пишем, смотрим налево, смотрим направо
    public void depthFirstSearchWLR(Node node) {
        if (node != null) {
            System.out.print(" " + node.value);
            depthFirstSearchLWR(node.leftBranch);
            depthFirstSearchLWR(node.rightBranch);
        }
    }


    //Смотрим налево, смотрим направо, пишем.
    public void depthFirstSearchLRW(Node node) {
        if (node != null) {
            depthFirstSearchLRW(node.leftBranch);
            depthFirstSearchLRW(node.rightBranch);
            System.out.print(" " + node.value);
        }
    }


    //Обход в ширину
    public void breadthFirstSearch() {
        if (rootOfTree == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(rootOfTree);

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            System.out.print(" " + node.value);

            if (node.leftBranch != null) {
                nodes.add(node.leftBranch);
            }

            if (node.rightBranch!= null) {
                nodes.add(node.rightBranch);
            }
        }
    }

    // Ищем высота деревца. Вот только не надо потом говорить, что я списывал,
    // потому что есть комментарии. Комментарий я написал сам, а про метод вы явно спросите на паре.
    public int heightOfTree(Node node){
        if (node == null){
            return 0;
        }else {
            int leftHeight = heightOfTree(node.leftBranch);
            int rightHeight = heightOfTree(node.rightBranch);
            if (leftHeight > rightHeight){
                return 1 + leftHeight;
            }else{
                return 1 + rightHeight;
            }
        }
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + rootOfTree +
                '}';
    }
}
