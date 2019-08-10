package com.example.testlearning.thread;

public class TransferRunnable implements Runnable{

    private Alipay alipay = new Alipay(3,200);
    private int from;
    private int to;
    private int amout;
    private static final String TAG = "TransferRunnable";

    public TransferRunnable(int from,int to,int amout){
        this.from = from;
        this.to = to;
        this.amout = amout;
    }

    @Override
    public void run() {
        try {
            alipay.transfer(from,to,amout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
