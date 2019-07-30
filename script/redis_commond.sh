#!/usr/bin/env bash

redis_list=("172.23.3.19:7001,172.23.3.19:7002,172.23.3.19:7003,172.23.3.19:7004,172.23.3.19:7005,172.23.3.19:7006")

arr=($(echo "$redis_list" | tr ',' '\n'))

for info in ${arr[@]}; do
  echo "开始执行:${info}"
  redis_info=($(echo "$info" | tr ':' '\n'))
  ip=${redis_info[0]}
  port=${redis_info[1]}
  echo "ip="${ip}",port="${port}
  redis-cli -c -h $ip -p $port keys \*
  #redis-cli -c -h $ip -p $port flushdb

done

echo "执行完成"
