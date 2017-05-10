package com.jebao.erp.web.utils.contract;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Administrator on 2016/12/19.
 */
public class PdfQueueSingleton {
    //pdf的生成比较耗费资源--需要通过阻塞队列来控制数量
    private int queueSize = 100;
    private ArrayBlockingQueue<PdfInfo> queue = new ArrayBlockingQueue <PdfInfo>(queueSize);
    private volatile static PdfQueueSingleton instance; //声明成 volatile
    private PdfQueueSingleton(){}

    public static PdfQueueSingleton getSingleton() {
        if (instance == null) {
            synchronized (PdfQueueSingleton.class) {
                if (instance == null) {
                    instance = new PdfQueueSingleton();
                }
            }
        }
        return instance;
    }
    public void put(PdfInfo pdfInfo) throws InterruptedException {
        queue.put(pdfInfo);
    }
    public PdfInfo take() throws InterruptedException {
        return queue.take();
    }
    public int size()
    {
        return queue.size();
    }
    public boolean isFree()
    {
        return queue.size()<queueSize-5;
    }
    public boolean isExist(PdfInfo pdfInfo)
    {
        boolean isOk= queue.contains(pdfInfo);
        return queue.contains(pdfInfo);
    }
}
