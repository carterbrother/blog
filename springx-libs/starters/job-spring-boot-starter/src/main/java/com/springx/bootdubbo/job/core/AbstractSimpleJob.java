package com.springx.bootdubbo.job.core;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.util.Collections;

public abstract class AbstractSimpleJob extends AbstractJob implements SimpleJob {

	/**
	 * 执行作业
	 * @param context 作业上下文
	 * @return
	 */
	public abstract void doJob(final ShardingContext context);

	@Override
	public void execute(ShardingContext shardingContext) {
		super.executeJob(shardingContext, Collections.emptyList(), (jobContext, list) -> {
			doJob(jobContext);
			return 0;
		});
	}





}