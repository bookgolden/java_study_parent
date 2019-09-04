    
    参见教程： https://blog.battcn.com/2017/07/14/other/git-cmd/
---
    将 暂存区 里 所有文件 恢复到 工作区
    git reset HEAD
    
    将 暂存区 里 指定文件 恢复到 工作区
    git reset HEAD about.html
        
    git add a.txt
    git commit -m "add a.txt"
-----

查看日志：

    git log
    git log --pretty=oneline
    git log --oneline
    git reflog

    D:\WorkSpace\java_study_parent>git reflog
    e482c3f (HEAD -> master) HEAD@{0}: commit: 'ddd'
    11aae8b HEAD@{1}: commit: ccc
    6c1da68 HEAD@{2}: commit: bbb
    f2181fb (origin/master, origin/HEAD) HEAD@{3}: commit: add plus
    a906f20 HEAD@{4}: commit: <E5><A2><9E><E5><8A><A0> <E8><AE><BE><E8><AE><A1><E6><A8><A1><E5><BC><8F>
    14fb0ab HEAD@{5}: clone: from https://github.com/bookgolden/java_study_parent.git

    HEAD@{移动到当前版本需要多少步}

    基于索引值操作[推荐]
        git reset --hard [局部索引值]
        git reset --hard a6ace91
    使用^符号：只能后退
        git reset --hard HEAD^
        注：一个^表示后退一步，n 个表示后退n 步
    使用~符号：只能后退
        git reset --hard HEAD~n
        注：表示后退n 步
    
    reset 命令的三个参数对比
        --soft 参数
            仅仅在本地库移动HEAD 指针
         ---------------------------------
        |          |  暂存区   |  工作区   |
        ----------------------------------
        |   本地库  |          |          |
        ----------------------------------
        --mixed 参数
            在本地库移动HEAD 指针
            重置暂存区
         ---------------------------------
        |          |          |    工作区  |
        -----------------------------------
        |   本地库  |  暂存区  |            |
        ----------------------------------
        --hard 参数
            在本地库移动HEAD 指针
            重置暂存区
            重置工作区
        ----------------------------------
        |          |          |          |
        ----------------------------------
        |   本地库  |  暂存区  |  工作区   |
        ----------------------------------
        
    删除文件并找回
        前提：删除前，文件存在时的状态提交到了本地库。
        操作：git reset --hard [指针位置]
            删除操作已经提交到本地库：指针位置指向历史记录
            删除操作尚未提交到本地库：指针位置使用HEAD

    比较文件差异
        git diff [文件名]
            将工作区中的文件和暂存区进行比较
        git diff [本地库中历史版本] [文件名]
            将工作区中的文件和本地库历史记录比较
        不带文件名比较多个文件

##### 撤销操作

    1. 修改后 未add（添加到暂存区） 需要撤销修改时：
           git checkout -- myfile.txt 或 手动删除工作区修改
           工作区 ： clean  暂存区： clean
    2. 修改后 add了（未commit） 再次修改文件  要撤销第二次修改时：
           git checkout -- myfile.txt (将暂存区恢复到工作区)
           暂存区有第一次的修改需要commit
    3. 修改后 add了（未commit），需要撤销修改时：
           git reset HEAD myfile.txt (将暂存区修改删除)
           此时工作区的修改还未撤销
           git checkout -- myfile.txt (撤销工作区修改)
    4. 修改后 add并commit了，需要撤销修改时：
           git reset --hard HEAD^  (版本回退)**


##### 分支操作
    
    1.查看分支 				git branch
    2.创建分支 				git branch <name>
    3.切换分支 				git checkout <name>
    4.合并某分支到当前分支  git merge <name>
    5.删除分支				git branch -d <name>


##### Bug分支

    1.将当前工作现场储藏
    	git stash
    2.创建bug分支 修改 提交到主干 删除bug分支
    3.恢复原工作现场
    	1.git stash apply（stash内容并不删除，git stash drap 删除）
    	2.git stash pop（恢复并删除stash）
    4.可多次stash，恢复的时候使用 git stash list 查看，
      恢复指定stash使用 git stash apply stash{0}
      
##### 标签

    1.创建标签
    	git tag －a “v1.0” -m “git learning version 1.0”
    2.推送标签
    	git push origin v1.0  /   git push origin —tags
    3.删除标签
    	git tag －d v1.0
    4.删除远程标签
    	its push origin :refs/tags/v1.0








