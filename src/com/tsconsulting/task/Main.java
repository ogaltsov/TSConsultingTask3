package com.tsconsulting.task;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        try {
            int peopleCount = Integer.parseInt(args[0]);

            System.out.println("\r\n[Using java.util.concurrent.CyclicBarier]");

            Warehouse warehouse = Warehouse.getInstance();

            threadTask(peopleCount, new Adapter(new CyclicBarrier(peopleCount)));

            Thread.sleep(3000);

            warehouse.fill(1000);

            System.out.println("\r\n[Using java.util.concurrent.Phaser]");

            threadTask(peopleCount, new Phaser(peopleCount));

        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Не введен аргумент для запуска!");
        }
    }

    public static void threadTask(int peopleCount, Phaser phaser) {

            ExecutorService threadPool = Executors.newFixedThreadPool(peopleCount);
            for(int i=0; i<peopleCount; i++)
                threadPool.submit(new Consumer(i+1, Warehouse.getInstance(), phaser));
            threadPool.shutdown();
    }
}
