package com.yourorganization.lesta.task2;

/**
 * Кольцевой буфер (FIFO) на базе обычного массива.
 * + Плюсы:
 * 1) Полный контроль над логикой буфера: чётко видно, как формируются head, tail и т.д.
 * 2) Константная сложность операций enqueue/dequeue (O(1)): меняются только индексы.
 * - Минусы:
 * 1) Необходима ручная обработка переполнения (либо исключение, либо перезапись).
 * 2) При желании переписывать старые данные и вести историю,
 * нужна дополнительная логика.
 */
public class CircularBufferArray<T> {

    private final T[] buffer;
    private final int capacity;

    private int head = 0;
    private int tail = 0;
    private int count = 0;

    @SuppressWarnings("unchecked")
    public CircularBufferArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;

        this.buffer = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    /**
     * Добавить элемент в буфер.
     *
     * @throws IllegalStateException если буфер заполнен (можно изменить логику под перезапись).
     */
    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Buffer is full.");
        }
        buffer[tail] = item;
        tail = (tail + 1) % capacity;
        count++;
    }

    /**
     * Извлечь самый старый (FIFO) элемент из буфера.
     *
     * @throws IllegalStateException если буфер пуст.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Buffer is empty.");
        }
        T item = buffer[head];
        buffer[head] = null; // Необязательно, но полезно для GC
        head = (head + 1) % capacity;
        count--;
        return item;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        // Строка что бы лучше посмотреть вывод
        StringBuilder sb = new StringBuilder();
        sb.append("CircularBufferArray[");
        for (int i = 0; i < capacity; i++) {
            sb.append(buffer[i]);
            if (i < capacity - 1) sb.append(", ");
        }
        sb.append("], head=").append(head)
                .append(", tail=").append(tail)
                .append(", count=").append(count);
        return sb.toString();
    }
}
