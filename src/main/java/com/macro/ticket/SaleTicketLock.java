package com.macro.ticket;

public class SaleTicketLock {
    public static void main(String[] args) throws InterruptedException {
        Ticket2 ticket = new Ticket2();
        new Thread(() -> {for (int i = 0; i < 300; i++) ticket.sale();}, "A").start();
        new Thread(() -> {for (int i = 0; i < 300; i++) ticket.sale();}, "B").start();
        new Thread(() -> {for (int i = 0; i < 300; i++) ticket.sale();}, "C").start();
    }
}

