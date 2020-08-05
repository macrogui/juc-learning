package com.macro.ticket;

class Ticket{
    private int num = 600;

    public synchronized void sale(){
        if (num>0) {
            System.out.println(Thread.currentThread().getName()+"卖出了第"+num--+"张票,剩余: "+num);
        }
    }
}