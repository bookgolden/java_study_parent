
###### hash

    hset book  name:zhangxianshuang:30:100:shandong ZhangXianShuang
    hset book  age:zhangxianshuang:30:100:shandong 30
    hset book  height:zhangxianshuang:30:100:shandong 100
    hset book  address:zhangxianshuang:30:100:shandong ShanDong

    hset book  name:wangYu:20:188:heilongjiang WangYu
    hset book  age:wangYu:20:188:heilongjiang 20
    hset book  height:wangYu:20:188:heilongjiang 188
    hset book  address:wangYu:20:188:heilongjiang HeiLongJiang
    
    hset book  name:xiaobo:28:175:hebei LiuZhenBo
    hset book  age:xiaobo:28:175:hebei 28
    hset book  height:xiaobo:28:175:hebei 175
    hset book  address:xiaobo:28:175:hebei HeBei
    
    hset book  name:yaozhongping:31:222:shanxi YaoZhongPin
    hset book  age:yaozhongping:31:222:shanxi 31
    hset book  height:yaozhongping:31:222:shanxi 222 
    hset book  address:yaozhongping:31:222:shanxi ShanXi

    hgetall book
    hkeys book
    hvals book
    hexists book height:xiaobo:28:175:hebei
    
    分页查询：
    hscan book 0 match age:*:3*:*:* count 100
    
    https://www.runoob.com/redis/redis-hashes.html
    
    
    HSTRLEN book height:xiaobo:28:175:hebei
    
    