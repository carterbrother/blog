package com.carter.demo.niuke.forkjoin;

import java.util.concurrent.ForkJoinTask;

/**
 * @author Carter.li
 * @createtime 2023/4/18 16:37
 */
public class BatchScoreTask extends ForkJoinTask<Integer> {

    @Override
    public Integer getRawResult() {
        return null;
    }


    @Override
    protected void setRawResult(Integer value) {

    }


    @Override
    protected boolean exec() {
        return false;
    }
}
