package com.jxshen.example.designmodel.workermaster;

import java.util.Map;

/**
 * reference from: http://blog.csdn.net/ghuil/article/details/41048005
 */
public class WorkerApplication {

    public static void main(String[] args) throws InterruptedException {
        // 消费
        ConcreteMaster<Integer, Integer> master = new ConcreteMaster<>(new CubeWorker(), 4);
        // 生产
        for (int i = 1; i <= 100; i++) {
            master.submit(i);
        }
        master.execute();

        Map<String, Integer> resultMap = master.getResultMap();
        int res = 0;
        while (true) {
            if (!master.isComplete() && resultMap.isEmpty()) {
                Thread.sleep(200);
            }
            for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
                res += entry.getValue();
                resultMap.remove(entry.getKey());
            }
            if (master.isComplete() && resultMap.isEmpty()) {
                break;
            }
        }
        System.out.println(res);
    }
}
