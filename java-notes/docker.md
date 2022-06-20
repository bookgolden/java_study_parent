
参考文档

    https://blog.csdn.net/weixin_45606067/article/details/109629397



docker 安装地址

    官方源地址（比较慢）：
    sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
    
    阿里云：
    sudo yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    
    清华大学源：
    sudo yum-config-manager \
    --add-repo \
    https://mirrors.tuna.tsinghua.edu.cn/docker-ce/linux/centos/docker-ce.repo


docker hub下载加速配置

    sudo mkdir -p /etc/docker

    sudo tee /etc/docker/daemon.json <<-'EOF'
    {
    "registry-mirrors": ["https://076wf9i1.mirror.aliyuncs.com"]
    }
    EOF
    
    sudo systemctl daemon-reload
    
    sudo systemctl restart docker
