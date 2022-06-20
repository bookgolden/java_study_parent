
### 查询所有索引
    
    GET /_cat/indices

### 查询索引所有记录

    GET bank/_search
    {
        "query": {
            "match_all": {}
        }
    }

### 查询索引映射
    
    GET bank/_mapping

### 条件查询-方式一

    GET bank/_search?q=*&sort=@timestamp:&from=0&size=3
    q=*   : 查询所有
    sort=@timestamp:asc     按字段@timestamp排序:asc
    from: 起始
    size: 分页大小

### 条件查询-方式二

    GET bank/_search
    {
        "query":{
            "match_all":{}
        },
        "sort":[
            {
                "@timestamp":{
                    "order":"desc"
                }
            }
        ],
        "from":0,
        "size":2,
        "_source": ["dll.path","process.name", "event.type", "@timestamp"]  #指定返回字段
    }


### match 匹配
    GET bank/_search
    {
        "query":{
            "match":{
                "user.full_name":"bob"
            }
        }
    }

    {
        "query":{
            "match":{
                "process.pid":2012
            }
        }
    }
    
    {
        "query":{
            "match":{
                "user.domain":"ART-DESKTOP"     分词后查询
            }
        }
    }

    match 匹配的是数字类型:是精确查询; 是文本：全文检索
    全文检索按照评分进行排序，由高到低，会对查过条件进行分词匹配

### match_phrase 短语匹配，不分词
    
    GET bank/_search
    {
        "query":{
            "match_phrase":{
                "dll.name":"ntdll.dll"
            }
        }
    }

### multi_match 多字段匹配， 会分词
    GET bank/_search
    {
        "query":{
            "multi_match":{
                "query":"library cmd Windows",
                "fields":[
                    "process.executable",
                    "event.category"
                ]
            }
        }
    }

### bool 布尔

    GET bank/_search
    {
        "query":{
            "bool":{
                "must":[
                    {
                        "match":{
                            "event.category":"process"
                        }
                    }
                ],
                "must_not":[
                    {
                        "match":{
                            "event.type":"terminate"
                        }
                    }
                ],
                "should":[
                    {
                        "match":{
                            "dll.name":"ntdll"
                        }
                    }
                ],
                "filter":[
                    {
                        "range":{
                            "FIELD":{
                                "gte":10,
                                "lte":20
                            }
                        }
                    }
                ]
            }
        }
    }
    
    must: 必须满足
    must_not: 必须不满足
    should: 应该满足，不满足也可以，满足最好
    filter: 没有相关性得分

### 范围查询

    GET bank/_search
    {
        "query":{
            "bool":{
                "must":[
                    {
                        "range":{
                            "process.ppid":{
                                "gte":10,
                                "lte":20000
                            }
                        }
                    }
                ]
            }
        }
    }

### filter ,没有_score
    GET bank/_search
    {
        "query":{
            "bool":{
                "filter":[
                    {
                        "range":{
                            "process.ppid":{
                                "gte":10,
                                "lte":20000
                            }
                        }
                    }
                ]
            }
        }
    }

### term

    GET bank/_search
    {
        "query":{
            "term":{
                "logon_id":{
                    "value":217055
                }
            }
        }
    }

### .keyword 精确匹配
    GET bank/_search
    {
        "query":{
            "match":{
                "event.category.keyword":"process"
            }
        }
    }

    精确字段用 term          非文本字段
    全文检索用 match         文本字段
    完整短语用 match_phrase
    
    文本字段精确匹配， 用.keyword
    match_phrase    分词匹配，字段中包含即匹配
    .keyword        字段值完整匹配，部分值不行

    








