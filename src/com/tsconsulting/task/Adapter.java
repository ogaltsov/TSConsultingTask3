package com.tsconsulting.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

public class Adapter extends Phaser {
    private CyclicBarrier cyclicBarrier;
    private int count;

    public Adapter(CyclicBarrier cyclicBarrier){
        super(cyclicBarrier.getParties());
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public int arriveAndAwaitAdvance(){

        try {
            cyclicBarrier.await();

        } catch (InterruptedException | BrokenBarrierException e) {
            return -1;
        }
        return 0;
    }

    @Override
    public int arriveAndDeregister() {
        count++;
        if(count!=cyclicBarrier.getParties()) {

            try {
                cyclicBarrier.await();

            } catch (InterruptedException | BrokenBarrierException e) {
                return -1;
            }
            return 0;
        }
        else {
            cyclicBarrier.reset();
            return -1;
        }
    }

}
