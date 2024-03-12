package ru.innotech.javapro.hw3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyExecutorService {
    private final LinkedList<Runnable> taskList;
    private final List<Thread> threadList;
    private boolean isRunning = true;

    public MyExecutorService(int numberOfWorkers) {
        this.threadList = new ArrayList<>(numberOfWorkers);
        this.taskList = new LinkedList<>();
        for (int i = 0; i < numberOfWorkers; i++) {
            Thread thread = new Thread(() -> {
                while (isRunning) {
                    Runnable runnable;
                    synchronized (taskList) {
                        while (taskList.isEmpty() && isRunning) {
                            try {
                                taskList.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (!taskList.isEmpty()) {
                            runnable = taskList.removeFirst();
                        } else {
                            continue;
                        }
                    }
                    runnable.run();
                }
            });
            threadList.add(thread);
            thread.start();
        }
    }

    /**
     * Execute task
     * @param task Runnable task
     */
    public void execute(Runnable task) {
        synchronized (taskList) {
            if (isRunning) {
                taskList.addLast(task);
                taskList.notify();
            }
        }
    }

    /**
     * Shutdown all threads
     */
    public void shutdown() {
        this.isRunning = false;
        this.threadList.forEach(t -> t.interrupt());
    }
}
