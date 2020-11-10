package com.company.SortAlgoritms;

public class Main {
        public static void main(String[] args) {


            int[] digitForTree = new int[100];

            for (int i = 0; i < digitForTree.length; i++) {
                digitForTree[i] = (int)(Math.random()*100 + 1);
            }

//            for (int i = 0; i <digitForTree.length ; i++) {
//                System.out.println(digitForTree[i]);
//            }


            BinaryTree binaryTree = new BinaryTree();

            // Добавляем значения массива в дерево
            for (int i = 0; i < digitForTree.length; i++) {
                binaryTree.add(digitForTree[i]);
            }

            // Пример принадлежности элемента
//            System.out.println(binaryTree.containsNode(102));

            // Примеры обходов в глубину
//            binaryTree.depthFirstSearchLWR(binaryTree.rootOfTree);
//            binaryTree.depthFirstSearchWLR(binaryTree.rootOfTree);
//            binaryTree.depthFirstSearchLRW(binaryTree.rootOfTree);



            // Пример обхода в ширину
//            binaryTree.breadthFirstSearch();

            // Пример высоты дерева
//            int height = binaryTree.heightOfTree(binaryTree.rootOfTree);
//            System.out.println(height + " is height of tree");

//            System.out.println(binaryTree.toString());

        }
}
