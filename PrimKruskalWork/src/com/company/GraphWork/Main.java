package com.company.GraphWork;

import java.util.*;



//Новое задание, тема - остовные деревья.
// Задание будет относительно простым: реализовать алгоритмы Прима и Краскала.
// Однако, надо сделать как можно более эффективную реализацию.
// Проверять производительность можно на полном графе из тысячи вершин со случайными весами.
// Молодцом будет тот, у кого программа отработает быстрее всего (с поправкой на технологию).

public class Main {
        public static void main(String[] args) {

//            int numberOfVertices = 10;
            int numberOfVertices = 1000;
            int edgeCounter = 0;
            int[][] graphMatrix = new int[numberOfVertices][numberOfVertices];

            for (int i = 0; i < graphMatrix.length; i++) {
                for (int j = i; j < graphMatrix.length; j++) {
                    graphMatrix[i][j] = (int)(Math.random() * 40 + 1);
                    if (graphMatrix[i][j] > 0 && i != j){
                        edgeCounter++;
                    }
                    graphMatrix[j][i] = graphMatrix[i][j];
                }
                graphMatrix[i][i] = 0;
            }

            Graph graph = new Graph(numberOfVertices, edgeCounter);

            edgeCounter--;
            for (int i = 0; i < graphMatrix.length; i++) {
                for (int j = i; j < graphMatrix.length; j++) {
                    if (graphMatrix[i][j] > 0){
                        graph.edge[edgeCounter].src = i;
                        graph.edge[edgeCounter].dest = j;
                        graph.edge[edgeCounter].weight = graphMatrix[i][j];
                        edgeCounter--;
                    }
                }
            }

            long startTime = System.currentTimeMillis();

            MST t = new MST();

            t.primMST(graphMatrix, numberOfVertices);
            System.out.println();
            System.out.println();

            graph.KruskalMST();
            System.out.println();
            System.out.println();

            long timeSpent = System.currentTimeMillis() - startTime;
            System.out.println("программа выполнялась " + timeSpent + " миллисекунд");

//            for (int i = 0; i < graphMatrix.length; i++) {
//                for (int j = 0; j < graphMatrix.length; j++) {
//                    System.out.print(graphMatrix[i][j] + " ");
//                }
//                System.out.println();
//            }
        }
}
