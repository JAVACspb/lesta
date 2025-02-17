package com.yourorganization.lesta.task2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Кольцевой буфер (FIFO) на базе ArrayBlockingQueue.
 * В качестве основы был взять готовый класс ArrayBlockingQueue
 * + Плюсы:
 * 1) Код короче и менее подвержен ошибкам: вся «очередная» логика уже написана.
 * 2) ArrayBlockingQueue потокобезопасен (если это нужно).
 * 3) Поддерживает операции offer/poll с таймаутами, можно гибко управлять блокировками.
 * - Минусы:
 * 1) При заполнении приходится руками решать, что делать:
 * либо выбрасывать исключение, либо удалять самый старый элемент.
 * 2) Код блокирующий, что может быть избыточно, если не нужна потокобезопасность.
 */
public class CircularBufferQueue<T> {

    private final ArrayBlockingQueue<T> queue;

    public CircularBufferQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    /**
     * Добавляем элемент. Если очередь заполнена, убираем самый старый и только затем добавляем.
     */
    public void enqueue(T item) {
        if (queue.remainingCapacity() == 0) {
            queue.poll();
        }
        // Добавляем элемент (не блокирует потому что  точно есть место)
        queue.offer(item);
    }

    /**
     * Извлекаем самый старый элемент.
     *
     * @throws IllegalStateException если очередь пуста.
     */
    public T dequeue() {
        T item = queue.poll();
        if (item == null) {
            throw new IllegalStateException("Buffer is empty.");
        }
        return item;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.remainingCapacity() == 0;
    }

    public int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        return "CircularBufferQueue" + queue.toString();
    }
}
