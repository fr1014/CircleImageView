package com.example.testlearning.thread;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Alipay {
    private static final String TAG = "Alipay";
    private double[] accounts;
    private Lock alipaylock;
    private Condition condition;

    public Alipay(int n, double money) {
        accounts = new double[n];
        //重入锁
        alipaylock = new ReentrantLock();
        //得到条件对象
        condition = alipaylock.newCondition();
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = money;
        }
    }

    /**
     * @param from   转账方
     * @param to     收款方
     * @param amount 转账金额
     */
    public void transfer(int from, int to, int amount) throws InterruptedException {
        Log.d(TAG, "transfer: "+"from:"+accounts[from]+"to:"+accounts[to]);
        alipaylock.lock();
        try {
            while (accounts[from] < amount) {
                // 阻塞当前线程，并放弃锁
                condition.await();
            }
            //转账的操作
            accounts[from] -= amount;
            accounts[to] += amount;
            condition.signalAll();
        } finally {
            alipaylock.unlock();
        }
    }
}
