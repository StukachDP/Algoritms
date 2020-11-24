package com.company.GraphWork;

import java.util.*;




// Доброго времени суток.
// Вы посетили мой проект по алгоритмам.
// Буду рад, если вы поиграетесь с ним.
// Задание изложено ниже.
// Конечно, это несильно глобальный и универсальный проект, но он таким быть пока и не должен.




//Вот новое задание:
// Задана последовательность слов.
// Игра заключается в том,
// что игроки по очереди называют слова из заданной последовательности.
// Правило, по которому называется слово, заключается в следующем:
// если названо какое-то слово,
// то следующий игрок может назвать слово,
// начинающееся с последней буквы предыдущего слова и которое ещё не было названо.
// Необходимо определить, можно ли выстроить цепочку из всех слов,
// причем последнее слово должно заканчиваться на ту букву,
// с которой начиналось первое слово.
// Так, например, для последовательности слов:
// Караганда, Воронеж, Киев, Жданов, Витебск, Архангельск требуемая цепочка слов имеет вид:
// Киев -> Воронеж -> Жданов -> Витебск -> Караганда -> Архангельск.


public class Main {

        public static int[][] convertingWords(String[] words){
                int[][] wordMatrix = new int[words.length][words.length];
                for (int i = 0; i < words.length; i++) {
                        for (int j = 0; j < words.length; j++) {
                                String word = words[i].toUpperCase();
                                int wordLenght = word.length();
                                if (word.substring(wordLenght - 1, wordLenght).equals("Й")
                                        || word.substring(wordLenght - 1, wordLenght).equals("Ь")
                                        || word.substring(wordLenght - 1, wordLenght).equals("Ы")){
                                    wordLenght--;
                                }
                                if (word.substring(wordLenght - 1, wordLenght).equals(words[j].substring(0,1))){
                                        wordMatrix[i][j] = 1;
                                }
                        }
                }
                return wordMatrix;
        }

        public static void main(String[] args) {

                String[] words = {"Брест", "Ошиб", "Минск", "Ивацевичи", "Киев", "Воронеж", "Варкута", "Ахам",
                        "Тамбов", "Койданово", "Житковичи", "Иркутск"};

                int[][] wordMatrix = convertingWords(words);

                int[] zeroArray = new int[words.length];
                int turn[] = new int[words.length + 1];
                int turnCounter = 0;
                boolean flag = true;
                while (turnCounter < words.length && flag){
                    for (int i = 0; i < words.length; i++) {
                        if (Arrays.equals(wordMatrix[turn[turnCounter]], zeroArray)){
                            flag = false;
                            break;
                        }
                        if (wordMatrix[turn[turnCounter]][i] == 1){
                            boolean repeat = false;
                            for (int j = 0; j < turnCounter + 1; j++) {
                                if (turn[j] == i){
                                    if (j == 0 && turnCounter == words.length - 1){
                                        break;
                                    }else{
                                        repeat = true;
                                        break;
                                    }
                                }
                            }
                            if (repeat){
                                wordMatrix[turn[turnCounter]][i] = 0;
                            }else{
                                turnCounter++;
                                turn[turnCounter] = i;
                                break;
                            }
                        }
                    }
                }

                if (flag == false){
                    System.out.println("Нет цикла по заданию!");
                }else{
                    for (int i = 0; i < turn.length; i++) {
                        System.out.print(words[turn[i]] + " ");
                    }
                }


//                System.out.println();
//                for (int i = 0; i < wordMatrix.length; i++) {
//                        for (int j = 0; j < wordMatrix.length; j++) {
//                                System.out.print(wordMatrix[i][j] + " ");
//                        }
//                        System.out.println();
//                }
        }
}
