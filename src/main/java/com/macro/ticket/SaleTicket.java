package com.macro.ticket;

public class SaleTicket {
    public static void main(String[] args) throws InterruptedException {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}