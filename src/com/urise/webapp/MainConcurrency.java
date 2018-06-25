package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {

    public static final int THREADS_NUMBER = 10000;
    private static final Object LOCK = new Object();
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);


        // TODO реализовать deadlock

        Object oneObject = new Object();
        Object twoObject = new Object();

        deadLockObject(oneObject, twoObject);
        deadLockObject(twoObject, oneObject);

    }

    private static void deadLockObject(Object one, Object two) {
        new Thread(() -> {
            System.out.println("Outside1 -> " + one.toString());
            synchronized (one) {
                System.out.println("Inside1 -> " + one.toString());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Outside2 -> " + one.toString());
                synchronized (two) {
                    System.out.println("Inside2 -> " + two.toString());

                }
            }
        }).start();
    }

    private synchronized void inc() {
        counter++;
    }
}
