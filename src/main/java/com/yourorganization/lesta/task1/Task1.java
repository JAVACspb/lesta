package com.yourorganization.lesta.task1;

public class Task1 {
    /**
     * Оригинальная функция для проверки чётности числа через остаток от деления.
     * Плюсы:
     * 1) Одна из самых примитивных операций.
     * 2) Универсальна: легко проверить чётность практически любых типов чисел.
     * (Побитовые операции работают только с целыми примитивами.)
     * Минусы:
     * 1) В сравнении с побитовыми операциями, деление и взятие остатка могут работать чуть медленнее.
     */
    public static boolean isEvenUsingModulo(int value) {
        return value % 2 == 0;
    }

    /**
     * Альтернативная функция для проверки чётности числа через побитовое И.
     * Плюсы:
     * 1) Потенциально более быстрая, чем деление.
     * Минусы:
     * 1) Применима только к целым числам (int, long и т.д.).
     */
    public static boolean isEvenUsingBitwise(int value) {
        return (value & 1) == 0;
    }


}
