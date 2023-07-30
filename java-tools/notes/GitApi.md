
### 依赖
    <dependency>
      <groupId>org.eclipse.jgit</groupId>
      <artifactId>org.eclipse.jgit</artifactId>
      <version>5.11.1.202105131744-r</version>
    </dependency>
    <dependency>
      <groupId>org.eclipse.jgit</groupId>
      <artifactId>org.eclipse.jgit.ssh.apache</artifactId>
      <version>5.11.1.202105131744-r</version>
    </dependency>
    <dependency>
      <groupId>org.eclipse.jgit</groupId>
      <artifactId>org.eclipse.jgit.archive</artifactId>
      <version>4.11.0.201803080745-r</version>
    </dependency>
    <dependency>
      <groupId>com.jcraft</groupId>
      <artifactId>jsch</artifactId>
      <version>0.1.54</version>
    </dependency>
    
### 方法

    String repoUrl = "/Users/liuzhenbo/work/java-demo";
    /**
    * 绑定本地仓库到远程地址
    */
    UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider("bookgolden", "xiaobo123");
    
        /**
         * 克隆仓库
         *
         * @throws GitAPIException
         */
        @Test
        public void testPullRepo() throws GitAPIException {
            //设置远程服务器上的用户名和密码
            Git git = Git.cloneRepository().setURI("https://gitee.com/bookgolden/java-demo.git") //设置远程URI
                    .setBranch("master")   //设置clone下来的分支,默认master
                    .setDirectory(new File(repoUrl))  //设置下载存放路径
                    .setCredentialsProvider(provider) //设置权限验证
                    .call();
            StatusCommand status = git.status();
            Status call = status.call();
        }
    
        @Test
        public void testCreateNewRepo() throws IOException, GitAPIException, URISyntaxException {
            Git git = Git.init().setDirectory(new File(repoUrl)).call();
    //        Git call = Git.init().setGitDir(new File(repoUrl, ".git")).call();
    
            // 创建本地新仓库
    //        FileRepositoryBuilder.create(new File(repoUrl, ".git")).create();
    //        Runtime.getRuntime().exec("rm -rf /Users/liuzhenbo5/Downloads/需求/starlink-vms/vms/lzb-jdv-dev/.git/*");
    
    //        Repository repository = Git.open(new File(repoUrl)).getRepository();
    //        Git.wrap(repository).remoteSetUrl().setRemoteUri(new URIish("http://gitlab.example.com/starlnk-vms-test/lzb-jdv-test.git")).call();
    //        Git git1 = Git.open(new File(repoUrl));
    //        git1.pull().setCredentialsProvider(provider).call();
    
            Git.cloneRepository()
                    .setBare(true)
                    .setURI("http://gitlab.examp.com/starlnk-vms-test/lzb-jdv-test.git")
    //                .setDirectory(new File(repoUrl))
    .setGitDir(git.getRepository().getDirectory().getAbsoluteFile())
    .setCredentialsProvider(provider)
    .call();

    }

    @Test
    public void repositoryAPI() throws IOException, GitAPIException {
        Git git = Git.open(new File("/Users/liuzhenbo5/Downloads/需求/starlink-vms/vms/lzb-jdv-test"));
        Repository repository = git.getRepository();
        // 获取当前分支   master
        System.out.println(repository.getBranch());
        // 仓库绝对路径   /Users/liuzhenbo5/Downloads/需求/starlink-vms/vms/lzb-jdv-test
        System.out.println(repository.getWorkTree().getAbsoluteFile());
        System.out.println(repository.getDirectory().getParent());

        // 获取代码仓库名  lzb-jdv-test
        System.out.println(repository.getDirectory().getParentFile().getName());

        // 推送内容
        git.push().setCredentialsProvider(provider).call();
        // git.push().setForce(true).setCredentialsProvider(provider).call();

        // 拉取内容、指定分支
        git.pull().setRemoteBranchName("test_pull_branch").setCredentialsProvider(provider).call();
        // git.pull().setCredentialsProvider(provider).call();

        // 从远程获取最新版本到本地   不会自动合并 merge
        git.fetch().setCheckFetchedObjects(true).call();

        // 切换分支
        git.checkout().setName("test").call();
        // git.checkout().setName("refs/heads/"+branchName).setForce(true).call();

        // 分支重命名
        git.branchRename().setNewName("newName").call();

        // 提交代码到本地仓库
        git.commit().setMessage("test").call();

    }

    /**
     * 创建分支练习：
     * - 根据主干master新建分支并同步到远程仓库
     *
     * @throws IOException
     * @throws GitAPIException
     */
    @Test
    public void createBranchBaseMaster() throws IOException, GitAPIException {
        String newBranch = "nice-01";
        Git git = Git.open(new File(repoUrl));
        // 获取所有分支
        List<Ref> list = git.branchList().call();
        //检查新建的分支是否已经存在，如果存在则将已存在的分支强制删除并新建一个分支
        for (Ref ref : list) {
            if (ref.getName().equals("refs/heads/" + newBranch)) {
                git.branchDelete().setBranchNames("refs/heads/" + newBranch).setForce(true).call();
                break;
            }
        }
        //新建分支
        Ref ref = git.branchCreate().setName(newBranch).call();
        //推送到远程
        git.push().add(ref).setCredentialsProvider(provider).call();
        //git.push().setForce(true).setCredentialsProvider(provider).call();
    }

    /**
     * 设置配置信息
     *
     * @throws IOException
     */
    @Test
    public void setConfig() throws IOException {
        FileRepositoryBuilder.create(new File(repoUrl, ".git")).create();

        Git git = Git.open(new File(repoUrl));
        Repository repository = git.getRepository();
        StoredConfig config = repository.getConfig();
        config.setString("remote", "origin", "url", "https://gitee.com/bookgolden/java-demo");
        config.save();
    }

    /**
     * 初始化、绑定远程私服、拉取代码
     *
     * @throws IOException
     * @throws GitAPIException
     */
    @Test
    public void initAndBindingAndPull() throws IOException, GitAPIException {
        // 1、初始化仓库
        Git git = Git.init().setDirectory(new File(repoUrl)).call();
        String format = String.format("rm -rf %s/.git/*", repoUrl);
        // 2、去删除掉 .git/* 的所有文件
        // 3、绑定远程私服
        Git.cloneRepository()
                .setBare(true)
                .setURI("https://gitee.com/bookgolden/java-demo.git")
                .setCredentialsProvider(provider)
                .setGitDir(new File(repoUrl + "/.git"))
                .call();
        // 4、拉取新代码
        git.pull().setCredentialsProvider(provider).call();
    }

    @Test
    public void test() throws IOException, GitAPIException {

    }

