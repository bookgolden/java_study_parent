

####     1. 常用系统变量
    $HOME、$PWD、$SHELL、$USER等
    
#####     （1）查看系统变量的值
    [root@storm4 ~]# echo $HOME
    /root
#####     （2）显示当前Shell中所有变量：set
    [root@storm4 ~]# set
    BASH=/bin/bash
    BASH_ALIASES=()
    BASH_ARGC=()
    BASH_ARGV=()
    BASH_CMDS=()
    BASH_LINENO=()
    BASH_SOURCE=()
    
####     自定义变量
    （1）定义变量：变量=值 
    （2）撤销变量：unset 变量
    （3）声明静态变量：readonly变量，注意：不能unset

##### 案例实操
    [root@storm4 ~]# A=1
    [root@storm4 ~]# echo $A
    1
    [root@storm4 ~]# A=2
    [root@storm4 ~]# echo $A
    2
    [root@storm4 ~]# unset A
    [root@storm4 ~]# echo $A
    
    [root@storm4 ~]# 
    
#####     可把变量提升为全局环境变量
    export 变量名
    
####     特殊变量  $n  $#  $*  $@  $?

######   $n：n为数字，$0代表该脚本名称，$1-$9代表第一到第九个参数，十以上的参数需要用大括号包含，如${10}
   
######   $#：获取所有输入参数个数，常用于循环
    
######   $*：代表命令行中所有的参数，$*把所有的参数看成一个整体
    
######   $@：代表命令行中所有的参数，不过$@把每个参数区分对待
    
######   $?：代表最后一次执行的命令的返回状态。值为0，证明上一个命令正确执行；值为非0（具体是哪个数，由命令自己来决定），则证明上一个命令执行不正确了
    [root@storm4 study_space]# cat para.sh 
    #!/bin/bash
    echo "$0 $1 $2 $3 $4 $5 $6 $7 $8 $9 ${10} ${11}"
    echo $#
    echo $*
    echo $@
    echo $?
    [root@storm4 study_space]# sh para.sh 11 22 33 44 55 66 77 88 99 10 11
    para.sh 11 22 33 44 55 66 77 88 99 10 11
    11
    11 22 33 44 55 66 77 88 99 10 11
    11 22 33 44 55 66 77 88 99 10 11
    0
    
####     运算符
    （1）"$((运算式))" 或 "$[运算式]"
    （2）expr  + , - , \*,  /,  %    加，减，乘，除，取余
    注意：expr运算符间要有空格
    
    [root@storm4 study_space]# expr 2 + 3
    5
    [root@storm4 study_space]# expr 3 - 2
    1
    [root@storm4 study_space]# expr `expr 2 + 3` \* 4           （2+3）X 4
    20
    [root@storm4 study_space]# S=$[(2+3)*4]
    [root@storm4 study_space]# echo $S
    20
    
####    条件判断
    [ condition ]（注意condition前后要有空格）
######  （1）两个整数之间比较
    =   字符串比较
    -lt 小于（less than）			-le 小于等于（less equal）
    -eq 等于（equal）				-gt 大于（greater than）
    -ge 大于等于（greater equal）	-ne 不等于（Not equal）

---    
    if [ -n str1 ]　　　　　　 当串的长度大于0时为真(串非空) 
    if [ -z str1 ]　　　　　　　 当串的长度为0时为真(空串) 
    
    eg:
    
    if [ -z "$STD_BACKUP_DIR" ]; then
            STD_BACKUP_DIR=$PRI_BACKUP_DIR
    fi
    
    if [ -n "$STD_HOST_IP" ]; then
    ping $STD_HOST_IP -c1
    fi
---

######  （2）按照文件权限进行判断
    -r 有读的权限（read）			
    -w 有写的权限（write）
    -x 有执行的权限（execute）
######  （3）按照文件类型进行判断
    -f 文件存在并且是一个常规的文件（file）
    -e 文件存在（existence）		
    -d 文件存在并是一个目录（directory）

    [root@storm4 study_space]# [ 23 -ge 22 ]        23是否大于等于22
    [root@storm4 study_space]# echo $?
    0
    [root@storm4 study_space]# [ -w helloworld.sh ]     是否具有写权限
    [root@storm4 study_space]# echo $?
    0
    [root@storm4 study_space]# [ -e /study_space/para.sh ]      文件是否存在
    [root@storm4 study_space]# echo $?
    0
    [root@storm4 study_space]# [ condition ] 
    [root@storm4 study_space]# echo $?
    0
    
    多条件判断（&& 表示前一条命令执行成功时，才执行后一条命令，|| 表示上一条命令执行失败后，才执行下一条命令）
    [root@storm4 study_space]# [ condition ] && echo OK || echo notok
    OK
    [root@storm4 study_space]# echo $?
    0
    [root@storm4 study_space]# [ condition ] && [ ] || echo notok
    notok
    [root@storm4 study_space]# echo $?
    0
    
####     流程控制
##### if 判断 基本语法
    if [ 条件判断式 ];then 
      程序 
    fi 
    或者 
    if [ 条件判断式 ] 
      then 
        程序 
    fi
    	注意事项：
    （1）[ 条件判断式 ]，中括号和条件判断式之间必须有空格
    （2）if后要有空格
    
######     例：输入一个数字，如果是1，则输AAA，如果是2，则输出BBB，如果是其它，什么也不输出
    [root@storm4 study_space]# cat flowIf.sh 
    #!/bin/bash
    if [ $1 -eq "1" ]
    then
            echo "AAA"
    elif [ $1 -eq "2" ]
    then
            echo "BBB"
    fi
    
    [root@storm4 study_space]# sh flowIf.sh 1
    AAA
    [root@storm4 study_space]# sh flowIf.sh 2
    BBB
    
###### case 语句 基本语法
    case $变量名 in 
      "值1"） 
        如果变量的值等于值1，则执行程序1 
        ;; 
      "值2"） 
        如果变量的值等于值2，则执行程序2 
        ;; 
      …省略其他分支… 
      *） 
        如果变量的值都不是以上的值，则执行此程序 
        ;; 
    esac
    注意事项：
    1)	case行尾必须为单词“in”，每一个模式匹配必须以右括号“）”结束。
    2)	双分号“;;”表示命令序列结束，相当于java中的break。
    3)	最后的“*）”表示默认模式，相当于java中的default。

###### 示例：输入一个数字，如果是1，则输出AAA，如果是2，则输出BBB，如果是其它，输出CCC
    [root@storm4 study_space]# cat flowCase.sh 
    #!/bin/bash
    case $1 in
    "1")
            echo "AAA" ;;
    "2")
            echo "BBB" ;;
    "3")
            echo "CCC" ;;
    *)
            echo "OOO" ;;
    esac
    [root@storm4 study_space]# sh flowCase.sh 1
    AAA
    [root@storm4 study_space]# sh flowCase.sh 2
    BBB
    [root@storm4 study_space]# sh flowCase.sh 3
    CCC
    [root@storm4 study_space]# sh flowCase.sh 4
    OOO

##### for 循环
###### 基本语法1
    for (( 初始值;循环控制条件;变量变化 )) 
      do 
        程序 
      done
###### for示例1：
    [root@storm4 study_space]# cat for.sh 
    #!/bin/bash
    s=0
    for((i=0;i<=100;i++))
    do
            s=$[$s+$i]
    done
    
    echo $s
    [root@storm4 study_space]# sh for.sh 
    5050
###### 基本语法2
    for 变量 in 值1 值2 值3… 
      do 
        程序 
      done
###### for示例2：
######     比较$*和$@区别
######     $*和$@都表示传递给函数或脚本的所有参数，不被双引号“”包含时，都以$1 $2 …$n的形式输出所有参数。
    [root@storm4 study_space]# cat for2.sh 
    #!/bin/bash
    for i in $*
    do
            echo "AAA= $i"
    done
    
    echo "======"
    
    for i in $@
    do
            echo "BBB- $i"
    done
    [root@storm4 study_space]# sh for2.sh 1 2 3 
    AAA= 1
    AAA= 2
    AAA= 3
    ======
    BBB- 1
    BBB- 2
    BBB- 3
###### 当它们被双引号“”包含时，“$*”会将所有的参数作为一个整体，以“$1 $2 …$n”的形式输出所有参数；“$@”会将各个参数分开，以“$1” “$2”…”$n”的形式输出所有参数 
    [root@storm4 study_space]# cat for2.sh 
    #!/bin/bash
    for i in "$*"
    do
            echo "AAA= $i"
    done
    
    echo "======"
    
    for i in "$@"
    do
            echo "BBB- $i"
    done
    [root@storm4 study_space]# sh for2.sh 1 2 3
    AAA= 1 2 3
    ======
    BBB- 1
    BBB- 2
    BBB- 3
    
#### while 循环
###### 基本语法
    while [ 条件判断式 ] 
      do 
        程序
      done
###### 示例：从1加到100
    [root@storm4 study_space]# cat while.sh 
    #!/bin/bash
    s=0
    i=1
    while [ $i -le 100 ]
    do
            s=$[$s+$i]
            i=$[$i+1]
    done
    echo $s
    
    [root@storm4 study_space]# sh while.sh 
    5050

#### read读取控制台输入
######     基本语法
    read(选项)(参数)
    	选项：
    -p：指定读取值时的提示符；
    -t：指定读取值时等待的时间（秒）
    参数
    	变量：指定读取值的变量名

###### 示例： 提示7秒内，读取控制台输入的名称
    #!/bin/bash
    read -t 7 -p "Enter your name in 7 seconds " NAME
    echo $NAME
    
    [root@storm4 study_space]# sh read.sh 
    Enter your name in 7 seconds bob
    bob

### 函数
#### 系统函数
###### 基本语法
    basename [string / pathname] [suffix]
    basename命令会删掉所有的前缀包括最后一个（‘/’）字符，然后将字符串显示出来
    suffix为后缀，如果suffix被指定了，basename会将pathname或string中的suffix去掉
    
    dirname 文件绝对路径
    从给定的包含绝对路径的文件名中去除文件名（非目录的部分），然后返回剩下的路径（目录的部分）
###### 示例
    [root@storm4 study_space]# basename /study_space/read.sh
    read.sh
    [root@storm4 study_space]# basename /study_space/read.sh .sh
    read
    [root@storm4 study_space]# 

    [root@storm4 study_space]# dirname /study_space/read.sh     获取read.sh文件的路径
    /study_space
    
####     自定义函数
###### 基本语法
    [ function ] funname[()]
    {
    	Action;
    	[return int;]
    }
    funname
    （1）必须在调用函数之前先声明函数，shell脚本是逐行运行,不会像其它语言一样先编译。
	（2）函数返回值，只能通过$?系统变量获得，可以显示加：return返回，如果不加，将以最后一条命令运行结果作为返回值。return后跟数值n(0-255)

    [root@storm4 study_space]# cat func.sh 
    #!/bin/bash
    function sum(){
            s=0
            s=$[ $1 + $2 ]
            echo "$s"
    }
    read -p "please input the num1: " n1
    read -p "please input the num2: " n2
    sum $n1 $n2
    [root@storm4 study_space]# sh func.sh 
    please input the num1: 12
    please input the num2: 12
    24
    [root@storm4 study_space]# sh func.sh 
    please input the num1: 1
    please input the num2: 2
    3
    [root@storm4 study_space]# 

### 工具 cut sed awk sort
#### cut
    cut的工作就是“剪”，具体的说就是在文件中负责剪切数据用的。cut 命令从文件的每一行剪切字节、字符和字段并将这些字节、字符和字段输出
###### 基本用法
    cut [选项参数]  filename
    说明：默认分隔符是制表符
    
    选项参数
    -f	列号，提取第几列
    -d	分隔符，按照指定分隔符分割列
###### 示例

    [root@storm4 study_space]# cat cut.txt 
    dong shen
    guan zhen
    wo  wo
    lai  lai
    le  le

    [root@storm4 study_space]# cut -d " " -f 1 cut.txt 
    dong
    guan
    wo
    lai
    le

    [root@storm4 study_space]# cut -d " " -f 1,2,3 cut.txt 
    dong shen
    guan zhen
    wo  wo
    lai  lai
    le  le

    [root@storm4 study_space]# cut -d " " -f 1- cut.txt 
    dong shen
    guan zhen
    wo  wo
    lai  lai
    le  le
    
    [root@storm4 study_space]# echo $PATH
    /usr/lib64/qt-3.3/bin:/app/go/bin:/app/jdk-12.0.1/bin:/root/bin:/root/.local/bin:/app/zookeeper/bin:/app/apache-storm/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin
    [root@storm4 study_space]# echo $PATH| cut -d : -f 2-4
    /app/go/bin:/app/jdk-12.0.1/bin:/root/bin
    
    切割ifconfig 后打印的IP地址
    [root@storm4 study_space]# ifconfig eth0|grep "inet addr"|cut -d : -f 2|cut -d " " -f1
    192.168.1.7

#### sed
    sed是一种流编辑器，它一次处理一行内容。处理时，把当前处理的行存储在临时缓冲区中，称为“模式空间”，接着用sed命令处理缓冲区中的内容，处理完成后，把缓冲区的内容送往屏幕。接着处理下一行，这样不断重复，直到文件末尾。文件内容并没有改变，除非你使用重定向存储输出。

###### 基本用法
    sed [选项参数]  ‘command’  filename
    
    参数说明
    -e	直接在指令列模式上进行sed的动作编辑。
    
    命令功能
    a 	新增，a的后面可以接字串，在下一行出现
    d	删除
    s	查找并替换 

    [root@storm4 study_space]# cat sed.txt 
    dong shen
    guan zhen
    wo  wo
    lai  lai
    
    le  le
    
    新增 a
    [root@storm4 study_space]# sed '2a AAAAAAAAAAA' sed.txt 
    dong shen
    guan zhen
    AAAAAAAAAAA
    wo  wo
    lai  lai
    
    le  le

    删除sed.txt文件所有包含wo的行
    [root@storm4 study_space]# sed '/wo/d' sed.txt 
    dong shen
    guan zhen
    lai  lai
    
    le  le
    
    [root@storm4 study_space]# sed '2d' sed.txt 
    dong shen
    wo  wo
    lai  lai
    
    le  le

    查找并替换 s
    [root@storm4 study_space]# sed 's/wo/AAAAA/g' sed.txt
    dong shen
    guan zhen
    AAAAA  AAAAA
    lai  lai
    
    le  le

    注意：‘g’表示global，全部替换

    将sed.txt文件中的第二行删除并将wo替换为ni
    [root@storm4 study_space]# sed -e '2d' -e 's/wo/ni/g' sed.txt 
    dong shen
    ni  ni
    lai  lai
    
    le  le

#### awk
    一个强大的文本分析工具，把文件逐行的读入，以空格为默认分隔符将每行切片，切开的部分再进行分析处理
    
    awk [选项参数] ‘pattern1{action1}  pattern2{action2}...’ filename
    pattern：表示AWK在数据中查找的内容，就是匹配模式
    action：在找到匹配内容时所执行的一系列命令
    
    选项参数	功能
    -F	   指定输入文件折分隔符
    -v	   赋值一个用户定义变量

###### 示例
    [root@storm4 study_space]# awk '/^root/' passwd 
    root:x:0:0:root:/root:/bin/bash
    [root@storm4 study_space]# awk '/shutdown$/' passwd 
    shutdown:x:6:0:shutdown:/sbin:/sbin/shutdown
    
    1、搜索以root关键字开头的所有行，并输出该行的第7列
    [root@storm4 study_space]# awk -F : '/^root/{print $7}' passwd 
    /bin/bash

    2、搜索以root关键字开头的所有行，并输出该行的第1列和第7列，中间以“，”号分割
    [root@storm4 study_space]# awk -F : '/^root/{print $1 "," $7}' passwd 
    root,/bin/bash

    注意：只有匹配了pattern的行才会执行action



    3、显示第一列和第七列，以逗号分割，且在所有行前面添加列名AAAAAA 在最后一行添加"EEEEEE"
    [root@storm4 study_space]#  awk -F : 'BEGIN{print "AAAAAA==="} {print $1","$7} END{print "EEEEEE==="}' passwd
    AAAAAA====
    root,/bin/bash
    bin,/sbin/nologin
    dockerroot,/sbin/nologin
    EEEEEE====

    注意：BEGIN 在所有数据读取行之前执行；END 在所有数据执行之后执行

    4、将文件中的用户id增加数值1并输出
    [root@storm4 study_space]# awk -v i=1 -F: '{print $3" -> " $3+1}' passwd
    0 -> 1
    1 -> 2
    2 -> 3
    3 -> 4

###### awk的内置变量
    变量	    说明
    FILENAME	文件名
    NR      	已读的记录数
    NF	        浏览记录的域的个数（切割后，列的个数）
    
###### 示例
    1、统计passwd文件名，每行的行号，每行的列数
    [root@storm4 study_space]# awk -F: '{print "fileName:"  FILENAME ", lineNumber:" NR  ", columns:" NF}' passwd 
    fileName:passwd, lineNumber:1, columns:7
    fileName:passwd, lineNumber:2, columns:7
    fileName:passwd, lineNumber:3, columns:7
    fileName:passwd, lineNumber:4, columns:7
    fileName:passwd, lineNumber:5, columns:7
    
    2、切割IP
    [root@storm4 study_space]# ifconfig eth0 |grep "inet addr" | awk -F : '{print $2}'|awk -F " " '{print $1}'
    192.168.1.7
    
    3、查询sed.txt中空行所在的行号
    [root@storm4 study_space]# cat sed.txt 
    dong shen
    guan zhen
    wo  wo
    lai  lai
    
    le  le
    
    [root@storm4 study_space]# awk '/^$/{print NR}' sed.txt 
    5
    7
    
    4、计算第二列的和并输出
    [root@storm4 study_space]# cat chengji.txt 
    张三 40
    李四 50
    王五 60
    [root@storm4 study_space]# cat chengji.txt |awk -F " " '{sum+=$2} END {print "result = " sum}' 
    result = 150
    
#### sort
    sort命令是在Linux里非常有用，它将文件进行排序，并将排序结果标准输出
###### 基本语法
    sort(选项)(参数)
    
    选项	说明
    -n      依照数值的大小排序
    -r	    以相反的顺序来排序
    -t	    设置排序时所用的分隔字符
    -k	    指定需要排序的列
    
    参数：指定待排序的文件列表

###### 示例
    [root@storm4 study_space]# cat sort.txt 
    bb:40:5.4
    bd:20:4.2
    xz:50:2.3
    cl:10:3.5
    ss:30:1.6
    
    [root@storm4 study_space]# sort -t : -nrk 2 sort.txt 
    或
    [root@storm4 study_space]# sort -t : -nr -k 2 sort.txt 
    xz:50:2.3
    bb:40:5.4
    ss:30:1.6
    bd:20:4.2
    cl:10:3.5


#### 案例：
    1、Shell脚本里如何检查一个文件是否存在？如果不存在该如何处理？
    #!/bin/bash
    
    if [ -f file.txt ]; then
       echo "文件存在!"
    else
       echo "文件不存在!"
    fi


    2、请用shell脚本写出查找当前文件夹（/home）下所有的文本文件内容中包含有字符”shen”的文件名称
    grep -r "shen" /home | cut -d ":" -f 1
    
    3、用shell写一个脚本，对文本中无序的一列数字排序
    [root@storm4 study_space]# cat test.txt 
    9
    8
    7
    6
    5
    4
    3
    2
    10
    1

    [root@storm4 study_space]# sort -n test.txt |awk '{a+=$0; print $0} END{print "SUM = " a}'
    1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    SUM = 55

####     正则表达式语法
    
    字符	说明
    \	将下一字符标记为特殊字符、文本、反向引用或八进制转义符。例如，"n"匹配字符"n"。"\n"匹配换行符。序列"\\\\"匹配"\"，"\\("匹配"("。
    ^	匹配输入字符串开始的位置。如果设置了 RegExp 对象的 Multiline 属性，^ 还会与"\n"或"\r"之后的位置匹配。
    $	匹配输入字符串结尾的位置。如果设置了 RegExp 对象的 Multiline 属性，$ 还会与"\n"或"\r"之前的位置匹配。
    *	零次或多次匹配前面的字符或子表达式。例如，zo* 匹配"z"和"zoo"。* 等效于 {0,}。
    +	一次或多次匹配前面的字符或子表达式。例如，"zo+"与"zo"和"zoo"匹配，但与"z"不匹配。+ 等效于 {1,}。
    ?	零次或一次匹配前面的字符或子表达式。例如，"do(es)?"匹配"do"或"does"中的"do"。? 等效于 {0,1}。
    {n}	n 是非负整数。正好匹配 n 次。例如，"o{2}"与"Bob"中的"o"不匹配，但与"food"中的两个"o"匹配。
    {n,}	n 是非负整数。至少匹配 n 次。例如，"o{2,}"不匹配"Bob"中的"o"，而匹配"foooood"中的所有 o。"o{1,}"等效于"o+"。"o{0,}"等效于"o*"。
    {n,m}	M 和 n 是非负整数，其中 n <= m。匹配至少 n 次，至多 m 次。例如，"o{1,3}"匹配"fooooood"中的头三个 o。'o{0,1}' 等效于 'o?'。注意：您不能将空格插入逗号和数字之间。
    ?	当此字符紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是"非贪心的"。"非贪心的"模式匹配搜索到的、尽可能短的字符串，而默认的"贪心的"模式匹配搜索到的、尽可能长的字符串。例如，在字符串"oooo"中，"o+?"只匹配单个"o"，而"o+"匹配所有"o"。
    .	匹配除"\r\n"之外的任何单个字符。若要匹配包括"\r\n"在内的任意字符，请使用诸如"[\s\S]"之类的模式。
    (pattern)	匹配 pattern 并捕获该匹配的子表达式。可以使用 $0…$9 属性从结果"匹配"集合中检索捕获的匹配。若要匹配括号字符 ( )，请使用"\("或者"\)"。
    (?:pattern)	匹配 pattern 但不捕获该匹配的子表达式，即它是一个非捕获匹配，不存储供以后使用的匹配。这对于用"or"字符 (|) 组合模式部件的情况很有用。例如，'industr(?:y|ies) 是比 'industry|industries' 更经济的表达式。
    (?=pattern)	执行正向预测先行搜索的子表达式，该表达式匹配处于匹配 pattern 的字符串的起始点的字符串。它是一个非捕获匹配，即不能捕获供以后使用的匹配。例如，'Windows (?=95|98|NT|2000)' 匹配"Windows 2000"中的"Windows"，但不匹配"Windows 3.1"中的"Windows"。预测先行不占用字符，即发生匹配后，下一匹配的搜索紧随上一匹配之后，而不是在组成预测先行的字符后。
    (?!pattern)	执行反向预测先行搜索的子表达式，该表达式匹配不处于匹配 pattern 的字符串的起始点的搜索字符串。它是一个非捕获匹配，即不能捕获供以后使用的匹配。例如，'Windows (?!95|98|NT|2000)' 匹配"Windows 3.1"中的 "Windows"，但不匹配"Windows 2000"中的"Windows"。预测先行不占用字符，即发生匹配后，下一匹配的搜索紧随上一匹配之后，而不是在组成预测先行的字符后。
    x|y	匹配 x 或 y。例如，'z|food' 匹配"z"或"food"。'(z|f)ood' 匹配"zood"或"food"。
    [xyz]	字符集。匹配包含的任一字符。例如，"[abc]"匹配"plain"中的"a"。
    [^xyz]	反向字符集。匹配未包含的任何字符。例如，"[^abc]"匹配"plain"中"p"，"l"，"i"，"n"。
    [a-z]	字符范围。匹配指定范围内的任何字符。例如，"[a-z]"匹配"a"到"z"范围内的任何小写字母。
    [^a-z]	反向范围字符。匹配不在指定的范围内的任何字符。例如，"[^a-z]"匹配任何不在"a"到"z"范围内的任何字符。
    \b	匹配一个字边界，即字与空格间的位置。例如，"er\b"匹配"never"中的"er"，但不匹配"verb"中的"er"。
    \B	非字边界匹配。"er\B"匹配"verb"中的"er"，但不匹配"never"中的"er"。
    \cx	匹配 x 指示的控制字符。例如，\cM 匹配 Control-M 或回车符。x 的值必须在 A-Z 或 a-z 之间。如果不是这样，则假定 c 就是"c"字符本身。
    \d	数字字符匹配。等效于 [0-9]。
    \D	非数字字符匹配。等效于 [^0-9]。
    \f	换页符匹配。等效于 \x0c 和 \cL。
    \n	换行符匹配。等效于 \x0a 和 \cJ。
    \r	匹配一个回车符。等效于 \x0d 和 \cM。
    \s	匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
    \S	匹配任何非空白字符。与 [^ \f\n\r\t\v] 等效。
    \t	制表符匹配。与 \x09 和 \cI 等效。
    \v	垂直制表符匹配。与 \x0b 和 \cK 等效。
    \w	匹配任何字类字符，包括下划线。与"[A-Za-z0-9_]"等效。
    \W	与任何非单词字符匹配。与"[^A-Za-z0-9_]"等效。
    \xn	匹配 n，此处的 n 是一个十六进制转义码。十六进制转义码必须正好是两位数长。例如，"\x41"匹配"A"。"\x041"与"\x04"&"1"等效。允许在正则表达式中使用 ASCII 代码。
    \num	匹配 num，此处的 num 是一个正整数。到捕获匹配的反向引用。例如，"(.)\1"匹配两个连续的相同字符。
    \n	标识一个八进制转义码或反向引用。如果 \n 前面至少有 n 个捕获子表达式，那么 n 是反向引用。否则，如果 n 是八进制数 (0-7)，那么 n 是八进制转义码。
    \nm	标识一个八进制转义码或反向引用。如果 \nm 前面至少有 nm 个捕获子表达式，那么 nm 是反向引用。如果 \nm前面至少有 n 个捕获，则 n 是反向引用，后面跟有字符 m。如果两种前面的情况都不存在，则 \nm 匹配八进制值 nm，其中 n 和 m 是八进制数字 (0-7)。
    \nml	当 n 是八进制数 (0-3)，m 和 l 是八进制数 (0-7) 时，匹配八进制转义码 nml。
    \un	匹配 n，其中 n 是以四位十六进制数表示的 Unicode 字符。例如，\u00A9 匹配版权符号 (©)。



