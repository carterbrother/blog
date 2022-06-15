redis.call('sadd',KEYS[1],ARGV[2])
local productPurchaseList = KEYS[2]..ARGV[2]
local userId = ARGV[1]
local product = 'product_'..ARGV[2]
local quantity = tonumber[ARGV[3]]
local stock = tonumber(redis.call('hget',product,'stock'))
local price = tonumber(redis.call('hget',product,'price'))
local purchase_date=ARGV[4]
if stock< quantity then  return 0 end
stock = stock - quantity
redis.call('hset',product,'stock',tostring(stock))
local sum= price * quantity
local purchaseRecord = userId..','..quantity..','..sum..','..price..','..purchase_date
redis.call('rpush',productPurchaseList,purchaseRecord)
return 1
