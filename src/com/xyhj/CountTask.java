package com.xyhj;

import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long>{

    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        //如果需要求和的总数小于10000 可以直接求和
        if (canCompute){
            for (long i = start ; i <= end ; i++){
                sum += i;
            }
        }else {
            //分解成100 个小任务
            long step = (start + end) / 100;
        }
        return null;
    }
}
