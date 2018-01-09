package com.wjb;

/**
 * Created by wjb on 2018/1/7.
 */
public class LinkList {
    public Node head;
    public Node current;

    public void add(int data) {
        Node node = new Node(data);
        if (head == null) {
            head=node;
            return;
        }
        Node temp = head;
        while (temp.next != null){
            temp=temp.next;
        }
        temp.next=node;

    }

    public int lenth(){
        int length = 0;
        Node temp = head;
        while (temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }


    public void printList() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }


    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        LinkList list = new LinkList();
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(55);
        list.add(36);
        list.printList();
        System.out.println("length是"+list.lenth());
    }


}
