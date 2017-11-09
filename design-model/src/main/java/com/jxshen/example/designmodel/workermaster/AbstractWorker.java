package com.jxshen.example.designmodel.workermaster;

import java.util.Map;
import java.util.Queue;

/**
 * reference from: http://blog.csdn.net/ghuil/article/details/41048005
 */
public abstract class AbstractWorker<T, R> implements Runnable {

    // 任务队列
    protected Queue<T> workQueue;
    // 子任务处理结果集
    protected Map<String, R> resultMap;

    public void setWorkQueue(Queue<T> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<String, R> resultMap) {
        this.resultMap = resultMap;
    }

    protected abstract R handle(T input);

    @Override
    public void run() {
        while (true) {
            T input = workQueue.poll();
            if (input == null) {
                break;
            }
            R result = handle(input);
            resultMap.put(Integer.toString(input.hashCode()), result);
        }
    }
}
