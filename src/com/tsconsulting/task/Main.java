package com.tsconsulting.task;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        try {
            int peopleCount = Integer.parseInt(args[0]);
            Warehouse warehouse = Warehouse.getInstance(1000);
            ExecutorService threadPool = Executors.newFixedThreadPool(peopleCount);
            Phaser phaser = new Phaser(peopleCount);

            for(int i=0; i<peopleCount; i++){
                threadPool.submit(new Consumer(i+1, warehouse, phaser));
            }
            threadPool.shutdown();

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Не введен аргумент для запуска!");
        }
    }
}
