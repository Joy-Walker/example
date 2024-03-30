package com.test;

import java.util.List;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-06 19:03:00
 */
public class ListNode {

     int val;
     ListNode next;
     ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next)
    { this.val = val; this.next = next; }



    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode tail = head;
        ListNode preTail = head;

        while (tail.next != null) {
            preTail = tail;
            tail = tail.next;
        }

        ListNode headNext = head.next;
        head.next = tail;
        tail.next = headNext;

        preTail.next = null;

        reorderList(headNext);

    }


}
