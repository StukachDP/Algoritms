package com.company.GraphWork;

import java.util.Arrays;

public class Graph {

    class Edge implements Comparable<Edge>
    {
        // src - начало ребра,
        // dest - конец ребра,
        // weight - вес ребра.
        int src, dest, weight;

        // Для сравнения ребер при их сортировке.
        // Сравниваются веса ребер.
        public int compareTo(Edge compareEdge)
        {
            return this.weight - compareEdge.weight;
        }
    };

    // Класс множества для каждой вершины
    class subset
    {
        int parent, rank;
    };

    int numberVer, numberEd;
    Edge edge[];

    // Конструктор графа из numberVer вершин и numberEd ребер
    Graph(int v, int e)
    {
        numberVer = v;
        numberEd = e;
        edge = new Edge[numberEd];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    // Находит порождающую вершину данного subset
    int find(subset subsets[], int i)
    {
        if (subsets[i].parent != i)
            subsets[i].parent
                    = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    // Объединяет subset через две вершины.
    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank
                < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank
                > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }




    void KruskalMST()
    {
        // Массив ребер, которые входят в остовное дерево.
        Edge result[] = new Edge[numberVer];
        int edgeCounter = 0;

        int counter = 0;
        for (counter = 0; counter < numberVer; ++counter)
            result[counter] = new Edge();

        // Сортируем все ребра по весу.
        // Каждой вершине даем собственный subset.
        Arrays.sort(edge);

        subset subsets[] = new subset[numberVer];
        for (counter = 0; counter < numberVer; ++counter){
            subsets[counter] = new subset();
            subsets[counter].parent = counter;
            subsets[counter].rank = 0;
        }

        counter = 0; // Счетчик взятия ребер из отсортированного по весу множества


        while (edgeCounter < numberVer - 1)
        {
            // Берем минимальное по весу ребро из отсортированного множества.
            // Смотрим, не создает ли его присутствие цикла.
            // Это тогда, когда оба его конца находятся в одном subset.
            // Если цикл, то берем следующее ребро, иначе. След итерация и след ребро.
            Edge next_edge = new Edge();
            next_edge = edge[counter++];

            //Собственно проверка.
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
            if (x != y) {
                result[edgeCounter++] = next_edge;
                Union(subsets, x, y);
            }
        }



        // Калякаем результат.
        System.out.println("Following are the edges in "
                + "the constructed MST");
        int minimumCost = 0;
        for (counter = 0; counter < edgeCounter; ++counter)
        {
            System.out.println(result[counter].src + " -- "
                    + result[counter].dest
                    + " == " + result[counter].weight);
            minimumCost += result[counter].weight;
        }
        System.out.println("Minimum Cost Spanning Tree "
                + minimumCost);
    }
}
