package com.tsconsulting.task;

import java.util.concurrent.Phaser;

public class Consumer implements Runnable{
    private int numOfPurchases;
    private int numOfGoods;
    private int id;

    private Warehouse warehouse;
    private Phaser phaser;

    public Consumer(int id, Warehouse warehouse, Phaser phaser){
        this.id=id;
        this.warehouse=warehouse;
        this.phaser=phaser;
    }


    public void run(){
        phaser.arriveAndAwaitAdvance();
        while(true){
        int buyCount = warehouse.sell((int)(1+Math.random()*9));

        if(buyCount!=0){
            numOfGoods += buyCount;
            numOfPurchases++;
            phaser.arriveAndAwaitAdvance();
        }
        else{
            System.out.println("____________________\r\nПокупатель: "+id+"\r\nКол-во товара: "+numOfGoods+
                    "\r\nКол-во покупок: "+numOfPurchases);
            phaser.arriveAndDeregister();
            break;
             }

        }
    }
}
