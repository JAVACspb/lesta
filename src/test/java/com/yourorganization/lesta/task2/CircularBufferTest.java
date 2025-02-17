package com.yourorganization.lesta.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircularBufferTest {

    @Test
    void testCircularBufferArray() {
        CircularBufferArray<Integer> buffer = new CircularBufferArray<>(3);

        System.out.println("=== TestCircularBufferArrayWithLogs ===");
        System.out.println("Создали буфер на 3 элемента. Изначально:");
        System.out.println(buffer);

        // Добавляем 3 элемента
        buffer.enqueue(10);
        System.out.println("После добавления 10: " + buffer);
        buffer.enqueue(20);
        System.out.println("После добавления 20: " + buffer);
        buffer.enqueue(30);
        System.out.println("После добавления 30: " + buffer);

        // Попробуем переполнение
        System.out.println("Пытаемся добавить 40 (ожидаем IllegalStateException)...");
        Exception ex = assertThrows(IllegalStateException.class, () -> buffer.enqueue(40));
        System.out.println("Исключение: " + ex);

        // Извлекаем элемент
        Integer firstOut = buffer.dequeue();
        System.out.println("Извлекли элемент (должно быть 10): " + firstOut);
        System.out.println("Состояние буфера после dequeue: " + buffer);

        // Добавляем ещё один элемент
        buffer.enqueue(99);
        System.out.println("После добавления 99: " + buffer);

        // Вынимаем остальные
        Integer secondOut = buffer.dequeue();
        Integer thirdOut = buffer.dequeue();
        System.out.println("Извлекли второй элемент (20): " + secondOut);
        System.out.println("Извлекли третий элемент (30): " + thirdOut);
        System.out.println("Состояние буфера: " + buffer);

        // Проверим, что остался 1 элемент (99)
        assertFalse(buffer.isEmpty(), "Buffer should not be empty yet");
        assertEquals(1, buffer.size(), "Buffer size must be 1");
        assertEquals(99, buffer.dequeue(), "Last item should be 99");
        System.out.println("После извлечения 99 буфер стал пустым: " + buffer);
    }

    @Test
    void testCircularBufferQueue() {
        CircularBufferQueue<Integer> buffer = new CircularBufferQueue<>(3);

        System.out.println("\n=== TestCircularBufferQueueWithLogs ===");
        System.out.println("Создали очередь на 3 элемента. Изначально:");
        System.out.println(buffer);

        // Заполняем буфер
        buffer.enqueue(100);
        System.out.println("После добавления 100: " + buffer);
        buffer.enqueue(200);
        System.out.println("После добавления 200: " + buffer);
        buffer.enqueue(300);
        System.out.println("После добавления 300: " + buffer);

        // При добавлении 400 вытесняется старый (100)
        buffer.enqueue(400);
        System.out.println("После добавления 400 (старый элемент вытеснился): " + buffer);

        // Извлекаем один
        Integer firstOut = buffer.dequeue();
        System.out.println("Извлекли элемент: " + firstOut);
        System.out.println("Состояние очереди: " + buffer);

        // Добавляем ещё
        buffer.enqueue(500);
        System.out.println("После добавления 500: " + buffer);
        buffer.enqueue(600);
        System.out.println("После добавления 600: " + buffer);

        // Извлекаем пока не опустошим
        while (!buffer.isEmpty()) {
            Integer out = buffer.dequeue();
            System.out.println("Извлекли: " + out + " -> " + buffer);
        }

        // Пытаемся извлечь из пустого
        System.out.println("Пробуем извлечь из пустого буфера (ожидаем IllegalStateException)...");
        Exception ex = assertThrows(IllegalStateException.class, buffer::dequeue);
        System.out.println("Исключение: " + ex);

        System.out.println("=== Конец теста CircularBufferQueueWithLogs ===");
    }
}
