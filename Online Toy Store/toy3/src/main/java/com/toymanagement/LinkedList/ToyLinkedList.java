package com.toymanagement.LinkedList;

import com.toymanagement.model.Toy;
import java.util.ArrayList;
import java.util.List;

public class ToyLinkedList {
    private Node head;

    public void add(Toy toy) {
        Node newNode = new Node(toy);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public Toy get(int index) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) return current.data;
            current = current.next;
            count++;
        }
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    public void set(int index, Toy toy) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                current.data = toy;
                return;
            }
            current = current.next;
            count++;
        }
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    public void remove(int index) {
        if (head == null) return;

        if (index == 0) {
            head = head.next;
            return;
        }

        Node current = head;
        int count = 0;
        while (current != null && current.next != null) {
            if (count == index - 1) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
            count++;
        }
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    public List<Toy> toList() {
        List<Toy> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    public Node getHead() {
        return head;
    }

    public void addAll(List<Toy> toysFromFile) {
        for (Toy toy : toysFromFile) {
            add(toy);
        }
    }

    private static class Node {
        Toy data;
        Node next;

        Node(Toy data) {
            this.data = data;
            this.next = null;
        }
    }
}
