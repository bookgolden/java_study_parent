
######  参看博文

    https://www.cnblogs.com/yorian/archive/2012/02/06/2340438.html
    https://blog.csdn.net/boyheroes/article/details/84376221
    
    
###### 查看磁盘情况

    [root@storm4 ~]# fdisk -l
    [root@storm4 ~]# df -l
    
    [root@storm4 ~]# fdisk /dev/sda

###### 格式化该新添加的分区
    [root@storm4 ~]# mkfs.ext3 /dev/sda3
    mke2fs 1.41.12 (17-May-2010)
    /dev/sda3 is mounted; will not make a filesystem here!
    [root@storm4 ~]# umount /dev/sda3
    umount: /dev/sda3: not mounted
    
    格式化出错，提示挂载失败，但是要卸载它却有提示没有挂载
    原因是：/etc/fstab 下的swap行引起的，将其注掉即可
    
    [root@storm4 ~]# cat /etc/fstab

    UUID=65657b82-1863-458d-9484-9e24e93f0e65 /                       ext4    defaults        1 1
    UUID=d59f8097-a3bb-44a0-a964-1c90a241cf5b /boot                   ext4    defaults        1 2
    UUID=9f85a7f7-2ede-421b-9c9f-fa31bded5dd9 swap                    swap    defaults        0 0
    tmpfs                   /dev/shm                tmpfs   defaults        0 0
    devpts                  /dev/pts                devpts  gid=5,mode=620  0 0
    sysfs                   /sys                    sysfs   defaults        0 0
    proc                    /proc                   proc    defaults        0 0
    
----
######  创建挂载目录

    [root@storm4 ~]# mkdir /sda3
    [root@storm4 ~]# mount /dev/sda3 /sda3
    [root@storm4 ~]# umount /sda3
    umount: /sda3: device is busy.
            (In some cases useful info about processes that use
             the device is found by lsof(8) or fuser(1))
    
    查看占用进程，kill掉即可 
    
    [root@storm4 ~]# fuser -m /sda3/
    /sda3/:               2959c
    [root@storm4 ~]# kill -9 2959
    [root@storm4 ~]# umount /dev/sda3
    OK~ 结束

---

###### 光盘镜像文件的挂接(mount)

    #mkdir /mnt/vcdrom
    注：建立一个目录用来作挂接点(mount point)
    #mount -o loop -t iso9660 /home/sunky/mydisk.iso /mnt/vcdrom
    注：使用/mnt/vcdrom就可以访问盘镜像文件mydisk.iso里的所有文件了


