package com.company.ShortestPaths;

import java.util.*;

// Встреча.
// Вводится 2 числа: n - количество домиков и k - количество дорог.
// Домики занумерованы целыми числами от 1 до n.
// Каждая дорога определяется тройкой чисел:
// двумя номерами домиков,
// которые являются концами этой дороги,
// и длиной дороги (длины дорог - положительные целые числа).
// Необходимо найти домик, от которого суммарное расстояние до всех остальных домиков будет минимальным.
// Граф будет ориентированным.


//Если что, использовался алгоритм Флойда.
// Там, где ребра нет, находится 100000
// И дома занумерованы от 0 до n

public class Main {
        public static void main(String[] args) {

            final int POSITIVE_INFINITY = 100000;

            int numberOfVertices = (int)(Math.random() * 12 + 3);
            int[][] matrixOfPathsLenght = new int[numberOfVertices][numberOfVertices];
            int[][] matrixOfHouses = new int[numberOfVertices][numberOfVertices];

            for (int i = 0; i < matrixOfPathsLenght.length; i++) {
                for (int j = 0; j < matrixOfPathsLenght.length; j++) {
                    matrixOfPathsLenght[i][j] = (int)(Math.random() * 40 + 1);
                    matrixOfHouses[i][j] = j + 1;
                }
            }
            int count = 0;
            int randomNumber;
            int secondRandomNumber;
            while(count < numberOfVertices - 3){
                randomNumber = (int)(Math.random()*(numberOfVertices - 1));
                secondRandomNumber = (int)(Math.random()*(numberOfVertices - 1));
                matrixOfPathsLenght[randomNumber][secondRandomNumber] = POSITIVE_INFINITY;
                matrixOfHouses[randomNumber][secondRandomNumber] = POSITIVE_INFINITY;
                count++;
            }
            for (int i = 0; i < matrixOfPathsLenght.length; i++) {
                matrixOfPathsLenght[i][i] = 0;
                matrixOfHouses[i][i] = i + 1;
            }

            for (int i = 0; i < matrixOfPathsLenght.length; i++) {
                for (int j = 0; j < matrixOfPathsLenght.length; j++) {
                    System.out.print(matrixOfPathsLenght[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            for (int i = 0; i < matrixOfPathsLenght.length; i++) {
                for (int j = 0; j < matrixOfPathsLenght.length; j++) {
                    System.out.print(matrixOfHouses[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("And solution.");


            for (int i = 0; i < matrixOfPathsLenght.length; i++) {
                for (int j = 0; j < matrixOfPathsLenght.length; j++) {
                    for (int k = 0; k < matrixOfPathsLenght.length; k++) {
                        if(j != i && k != i){
                            int sumForCompare = matrixOfPathsLenght[i][k] + matrixOfPathsLenght[j][i];
                            if (matrixOfPathsLenght[j][k] > sumForCompare){
                                matrixOfPathsLenght[j][k] = sumForCompare;
                                matrixOfHouses[j][k] = matrixOfHouses[j][i];
                            }
                        }
                    }
                }
            }

            int[] sumOfPathInLine = new int[numberOfVertices];
            int sumInLine = 0;
            for (int i = 0; i < sumOfPathInLine.length; i++) {
                for (int j = 0; j < sumOfPathInLine.length; j++) {
                    sumInLine += matrixOfPathsLenght[i][j];
                }
                sumOfPathInLine[i] = sumInLine;
                sumInLine = 0;
            }
            int indexOfMinSum = 0;
            for (int i = 0; i < sumOfPathInLine.length; i++) {
                if (sumOfPathInLine[i] < sumOfPathInLine[indexOfMinSum])
                {
                    indexOfMinSum = i;
                }
            }

            for (int i = 0; i < matrixOfPathsLenght.length; i++) {
                for (int j = 0; j < matrixOfPathsLenght.length; j++) {
                    System.out.print(matrixOfPathsLenght[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            for (int i = 0; i < matrixOfPathsLenght.length; i++) {
                for (int j = 0; j < matrixOfPathsLenght.length; j++) {
                    System.out.print(matrixOfHouses[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println();
            System.out.println("Min path to all houses is from house " + indexOfMinSum);
        }
}
