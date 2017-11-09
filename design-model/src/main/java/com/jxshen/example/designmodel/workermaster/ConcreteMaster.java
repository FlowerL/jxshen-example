package com.jxshen.example.designmodel.workermaster;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Reference from: http://blog.csdn.net/ghuil/article/details/41048005
 * @param <T> 任务
 * @param <R> 执行结果
 */
public class ConcreteMaster<T, R> {

    //任务队列
    private Queue<T> workQueue = new ConcurrentLinkedQueue<>();
    //Worker线程映射
    private Map<String, Thread> threadMap = new ConcurrentHashMap<>();
    // 任务结果处理集合
    private Map<String, R> resultMap = new ConcurrentHashMap<>();

    // 初始化工作者，并记录所有工作者线程
    public ConcreteMaster(AbstractWorker worker, int countWorker) {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for (int i = 0; i < countWorker; i++) {
            threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
        }
    }

    // 是否所有子任务都已经结束
    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    // 提交一个子任务
    public boolean submit(T work) {
        return workQueue.offer(work);
    }

    // 返回子任务结果集
    public Map<String, R> getResultMap() {
        return resultMap;
    }

    // 执行所有worker进程，进行处理
    public void execute() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            entry.getValue().start();
        }
    }
}
