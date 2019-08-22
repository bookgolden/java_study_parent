

### linux下yum命令出现 Loaded plugins: fastestmirror Determining fastest mirrors
 
转载：http://www.csdn123.com/html/blogs/20131107/94062.htm

    1.修改插件的配置文件
    
    # vi  /etc/yum/pluginconf.d/fastestmirror.conf  
    enabled = 1//由1改为0，禁用该插件
    
    2.修改yum的配置文件
    # vi /etc/yum.conf
    plugins=1//改为0，不使用插件

---

#### iptables不存在解决方案

    iptables的配置文件/etc/sysconfig/iptables不存在怎么办
    
    首先要看一下iptables是否安装了,使用service iptables status或yum info iptables看一下当前状态
    
    如果已安装，运行以下命令：
    
    iptables -A INPUT -p tcp --dport 22 -j ACCEPT
    
    iptables -I INPUT -p tcp --dport 15672 -j ACCEPT
    
    service iptables save
    
    这样就会提示
    
    iptables: Saving firewall rules to /etc/sysconfig/iptables:[ OK ]
    
    这样就会有iptables的初始配置文件了

---

#### yum源更新

    1.导入CentOS-5的GPG证书
    #rpm --import http://mirrors.163.com/centos/RPM-GPG-KEY-CentOS-5
    
    2.让yum使用网易的源：
    #cd /etc/yum.repos.d/
    #wget http://mirrors.163.com/.help/CentOS-Base-163.repo
    
    3.修改repo文件
    在所有mirrorlist前面加上#，把所有$releasever替换成5，保存
    #sed -i '/mirrorlist/d' CentOS-Base-163.repo
    #sed -i 's/\$releasever/5/' CentOS-Base-163.repo
    
    4.清理并重新生成yum缓存
    #yum clean metadata
    #yum makecache
    
    或：
    
    在安装完CentOS后一般需要修改yum源，才能够在安装更新rpm包时获得比较理想的速度。国内比较快的有163源、sohu源。这里以163源为例子。
    
    1. cd /etc/yum.repos.d
    2. mv CentOS-Base.repo CentOS-Base.repo.backup
    3. wget http://mirrors.163.com/.help/CentOS6-Base-163.repo
    4. mv CentOS6-Base-163.repo CentOS-Base.repo
    5.yum clean all
    
    最后在使用yum 就可以了。