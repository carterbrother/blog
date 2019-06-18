package com.springx.bootdubbo.job.autoconfig;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.List;

public abstract class AbstractDataFlowJob<T>  extends AbstractJob  implements DataflowJob {

	/**
	 * 执行作业
	 * @param context 作业上下文
	 * @param  dataList   数据列表
	 * @return 执行的完成的数据量
	 */
	public abstract int doJob(final ShardingContext context, List<T> dataList);


	@Override
	public void processData(ShardingContext shardingContext, List dataList) {
		super.executeJob(shardingContext,dataList,((jobContext, listParam) -> doJob(jobContext,listParam)));
	}
}