redis.call('set',KEYS[1],ARGV[1])
redis.call('set',KEYS[2],ARGV[2])
local str1=redis.call('get',KEYS[1])
local str2=redis.call('get',KEYS[2])
if str1 == str2 then
return 1
else
return 0
