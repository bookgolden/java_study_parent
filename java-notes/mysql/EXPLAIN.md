
#### type 显示的是访问类型，是较为重要的一个指标，结果值从最好到最坏依次是： 

    system > const > eq_ref > ref > fulltext > ref_or_null > index_merge > unique_subquery > index_subquery > range > index > ALL 

    system>const>eq_ref>ref>range>index>ALL
    

    一般来说，得保证查询至少达到range级别，最好能达到ref