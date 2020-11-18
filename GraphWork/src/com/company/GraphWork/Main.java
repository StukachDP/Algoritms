package com.company.GraphWork;

import java.util.*;



//Олимпиада. На олимпиаду прибыло n человек.
// Некоторые из них знакомы между собой.
// Круг знакомств задается матрицей A размером nxn.
// A[i,j]=1, если i-й человек знает j-го, A[i,j]=0 в противном случае, знакомство является симметричным.
// Необходимо определить, можно ли опосредованно перезнакомить всех людей между собой
// (незнакомые люди могут познакомиться только через общего знакомого).
// Если нет, то какое максимальное количество людей будет знать друг друга?
//Условно назовем эту задачу "ЗАДАЧА №1"



//Граф задан матрицей смежности.
// Проверить, является ли он двудольным.
// Граф задан матрицей смежности.
// Проверить, является ли он двудольным.
//Условно назовем эту задачу "ЗАДАЧА №2"
public class Main {
        public static void main(String[] args) {

            //Инциализация для обоих задач в комментариях находяться значения для работающего случая
            //Задача сделана для любых матриц любого размера

//            int numberOfVertices = 4;
//            int numberOfVertices = 5;
            int numberOfVertices = (int)(Math.random() * 7 + 5);
            int[][] matrixOfChildren = new int[numberOfVertices][numberOfVertices];
            int[][] graph = new int[numberOfVertices][numberOfVertices];

            //Заполнение матриц, для заполнения по проверке надо раскомментировать с нулями
            //и закомментировать с рандомом.
            for (int i = 0; i < matrixOfChildren.length; i++) {
                for (int j = i; j < matrixOfChildren.length; j++) {
//                    matrixOfChildren[i][j] = 0;
//                    graph[i][j] = 0;
                    matrixOfChildren[i][j] = (int)(Math.random() * 2);
                    matrixOfChildren[j][i] = matrixOfChildren[i][j];
                    graph[i][j] = (int)(Math.random() * 2);
                    graph[j][i] = graph[i][j];
                }
                matrixOfChildren[i][i] = 0;
                graph[i][i] = 0;
            }

            //ВАЖНО!!!!!!!!!!!
            //Для проверки каждой задачи, надо расскоментировать все, что снизу.

//            matrixOfChildren[0][2] = 1;
//            matrixOfChildren[2][0] = 1;
//            matrixOfChildren[3][4] = 1;
//            matrixOfChildren[4][3] = 1;

//            graph[0][1] = 1;
//            graph[1][0] = 1;
//            graph[0][2] = 1;
//            graph[2][0] = 1;
//            graph[1][3] = 1;
//            graph[3][1] = 1;
//            graph[2][3] = 1;
//            graph[3][2] = 1;



            //!!!!!!! Для ЗАДАЧИ №1
            // Логика:
            // 1. Проверка на изолированные вершины. Изолированные вершины внесены в список и разделены.
            // 2. Если вершина не изолированная, то вносим ее соседей в очередь.
            // 3. Переходим к след элементу в очереди. Вносим соседей этой вершины при этом проверяем,
            // есть ли они в очереди. Если да, то в очередь не заносим.
            // 4. Делаем это, пока очередь не закончена.
            // 5. Вносим всю очередь в список.
            // 6. Находим вершину, которой нет в списке и проделываем то же самое.



            ArrayList<Integer> resultChildren = new ArrayList<Integer>();
            resultChildren.add(-1);
            int[] zeroArray = new int[numberOfVertices];

//            for (int i = 0; i < matrixOfChildren.length; i++) {
//                boolean globalRepeat = false;
//                for (int element: resultChildren) {
//                    if (i == element){
//                        globalRepeat = true;
//                        break;
//                    }
//                }
//                if (globalRepeat){
//                    continue;
//                }else{
//                    int[] turn = new int[numberOfVertices];
//                    int turnCounter = 0;
//                    if (Arrays.equals(matrixOfChildren[i], zeroArray)){
//                        resultChildren.add(i);
//                        resultChildren.add(-1);
//                    }else{
//                        turn[turnCounter] = i;
//                        int counterOfTurnArray = turnCounter;
//                        do {
//                            for (int j = 0; j < matrixOfChildren.length; j++) {
//                                if (matrixOfChildren[turn[turnCounter]][j] == 1){
//                                    boolean repeat = false;
//                                    for (int k = 0; k < counterOfTurnArray + 1; k++) {
//                                        if (j == turn[k]){
//                                            repeat =true;
//                                            break;
//                                        }
//                                    }
//                                    if (repeat == false){
//                                        counterOfTurnArray++;
//                                        turn[counterOfTurnArray] = j;
//                                    }
//                                }
//                            }
//                            if (turnCounter != turn.length - 1){
//                                turnCounter++;
//                            }else {
//                                break;
//                            }
//                        }
//                        while (turn[turnCounter] != 0);
//                        for (int j = 0; j < turn.length; j++) {
//                            if (turn[j] != 0 || j == 0){
//                                resultChildren.add(turn[j]);
//                            }
//                        }
//                        resultChildren.add(-1);
//                    }
//                }
//            }
//            System.out.println(resultChildren.toString());


//            for (int i = 0; i < matrixOfChildren.length; i++) {
//                for (int j = 0; j < matrixOfChildren.length; j++) {
//                    System.out.print(matrixOfChildren[i][j] + " ");
//                }
//                System.out.println();
//            }


            //Для ЗАДАЧИ №2
            // 1. Инициализируем две группы. Массив с элементами (-1)
            // 2. Проверка на изолированные вершины.
            // 3. Берем первый элемент, вносим его в первую группу,
            // а его соседей во вторую при этом проверяем, чтобы в первой группе не было тех элементов,
            // которые хотим внести во вторую. Также проверяем на повтор элементов во второй группе.
            // 4. Берем первый элемент из второй группы, проделываем то же самое только для первой.
            // 5. Все это происходит, пока в каждой группе есть элементы, то есть массив[счетчик] != -1
            // 6. После всего прохождения считаем вершины из обоих групп.
            // Если их сумма равна количеству вершин в графе И предыдущие операции прошли без помех,
            // то граф двудольный.



            int[] firstGroup = new int[numberOfVertices];
            int[] secondGroup = new int[numberOfVertices];
            final int START = 0;
            boolean isAllOk = true;

            for (int i = 0; i < numberOfVertices; i++) {
                firstGroup[i] = -1;
                secondGroup[i] = -1;
            }

//            boolean isDoublePart = true;
//            for (int i = 0; i < numberOfVertices; i++) {
//                if (Arrays.equals(graph[i], zeroArray)){
//                    isDoublePart = false;
//                    break;
//                }
//            }
//
//            if (isDoublePart){
//                int firstCounter = 0;
//                int counterFSecondGroup = firstCounter;
//                int secondCounter = 0;
//                int counterSFirstGroup = 1;
//                firstGroup[firstCounter] = START;
//                do {
//                    do {
//                        for (int i = 1; i < graph.length; i++) {
//                            if (graph[firstGroup[firstCounter]][i] == 1){
//                                boolean notRepeatFirst = true;
//                                for (int j = 0; j < counterSFirstGroup; j++) {
//                                    if (firstGroup[j] == i){
//                                        isAllOk = false;
//                                        break;
//                                    }
//                                }
//                                if (isAllOk){
//                                    for (int j = 0; j < counterFSecondGroup; j++) {
//                                        if (secondGroup[j] == i){
//                                            notRepeatFirst = false;
//                                            break;
//                                        }
//                                    }
//                                    if (notRepeatFirst){
//                                        secondGroup[counterFSecondGroup] = i;
//                                        counterFSecondGroup++;
//                                    }
//                                }
//                            }
//                        }
//                        firstCounter++;
//                    }while (firstGroup[firstCounter] != -1 && isAllOk);
//                    if (secondGroup[secondCounter] != -1){
//                        do {
//                            for (int i = 1; i < graph.length; i++) {
//                                if (graph[secondGroup[secondCounter]][i] == 1){
//                                    boolean notRepeatSecond = true;
//                                    for (int j = 0; j < counterFSecondGroup; j++) {
//                                        if (secondGroup[j] == i){
//                                            isAllOk = false;
//                                            break;
//                                        }
//                                    }
//                                    if (isAllOk){
//                                        for (int j = 0; j < counterSFirstGroup; j++) {
//                                            if (firstGroup[j] == i){
//                                                notRepeatSecond = false;
//                                                break;
//                                            }
//                                        }
//                                        if (notRepeatSecond){
//                                            firstGroup[counterSFirstGroup] = i;
//                                            counterSFirstGroup++;
//                                        }
//                                    }
//                                }
//                            }
//                            secondCounter++;
//                        }while (secondGroup[secondCounter] != -1 && isAllOk == true);
//                    }
//                }while ((firstGroup[firstCounter] != -1 || secondGroup[secondCounter] != -1) && isAllOk == true);
//            }
//
//            int finalCounter = 0;
//            for (int i = 0; i < graph.length; i++) {
//                if (firstGroup[i] != -1){
//                    finalCounter++;
//                }
//
//                if (secondGroup[i] != -1){
//                    finalCounter++;
//                }
//            }
//
//            if (isAllOk && finalCounter == graph.length){
//                System.out.println("Граф Двудольный!!");
//            }else {
//                System.out.println("Граф не является двудольным!!!!");
//            }

            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }



        }
}
