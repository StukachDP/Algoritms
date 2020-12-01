package com.company.GraphWork;

public class MST {

    //Находит НОМЕР ВЕРШИНЫ С РЕБРОМ МИНИМАЛЬНОЙ СТОИМОСТИ,
    // которая не входит в остовное дерево!
    int minKey(int[] key, Boolean[] mstSet, int vertices)
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < vertices; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // Печатает результат
    void printMST(int[] parentEdgeArray, int[][] graph, int vertices)
    {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < vertices; i++)
            System.out.println(parentEdgeArray[i] + " - " + i + "\t" + graph[i][parentEdgeArray[i]]);
    }


    // Алгоритм Прима!!!!!!
    void primMST(int[][] graph, int vertices)
    {
        //Содержит стартовые номера вершин ребер,
        // которые входят в остовное дерево!
        int[] parentEdgeArray = new int[vertices];

        //Содержит веса вершин, сперва все бесконечно,
        // потом все обновляется согласно минимальной стоимости ребер из всех пройденных вершин.
        int[] key = new int[vertices];

        // Массив вошедших вершин. Сначала ничто еще не вошло.
        Boolean[] mstSet = new Boolean[vertices];

        //Инициализация. О ней писалось выше.
        for (int i = 0; i < vertices; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        //Начинаем с нулевой вершины!
        key[0] = 0;
        //Ребер будет восемь)))).
        parentEdgeArray[0] = -1;


        for (int count = 0; count < vertices - 1; count++) {


            //Ищем номер вершины с минимальным весом, которая еще не в остовном дереве!
            // Сначала это нулевая вершина.
            // Потом нулевая вершина добавит веса и понеслась.
            int viewLineInMatrix = minKey(key, mstSet, vertices);

            // Добавляем вершину в остовное дерево.
            mstSet[viewLineInMatrix] = true;


            // Текущая вершина.
            // Обновляем вес ребер с учетом тех, которые исходят из текущей вершины.
            // Если обновляется, то заносим ее в соответствующую позицию порождающего массива,
            // а вес ребра заносим в соответствующую позицию массива key.
            for (int v = 0; v < vertices; v++)

                // Собственно, вышеописанная проверка.
                // Чтобы не было циклов, смотрим еще,
                // есть ли конечная вершина ребра в остовном дереве.
                if (graph[viewLineInMatrix][v] != 0 && mstSet[v] == false && graph[viewLineInMatrix][v] < key[v]) {
                    parentEdgeArray[v] = viewLineInMatrix;
                    key[v] = graph[viewLineInMatrix][v];
                }
        }

        // Калякаем результат.
        printMST(parentEdgeArray, graph, vertices);
    }
}
