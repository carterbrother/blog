#!/bin/sh

echo '执行datax的数据迁移任务'

dir=/data/datax/2020_05_13_17_21_31

for file in $dir/*; do
     {
       python /tmp/datax/bin/datax.py $file
     }
done

echo '执行数据迁移任务完毕'