

    import javax.servlet.ServletContextEvent;
    import javax.servlet.ServletContextListener;
    
    import com.bjpowernode.erp.domain.Acl;
    import com.bjpowernode.erp.domain.Resource;
    
    /**
     * 在服务器（Tomcat），启动的时候做一些初始化操作
     * 采用缺省适配器改善代码风格
     * @author Administrator
     *
     */
    public class InitERPListener extends AbstractServletContextListerner {
    
    	@Override
    	public void contextInitialized(ServletContextEvent sce) {
    		System.out.println("初始化ERP数据......");
    		sce.getServletContext().setAttribute("root", Resource.ROOT);
    		sce.getServletContext().setAttribute("subSys", Resource.SUB_SYS);
    		sce.getServletContext().setAttribute("module", Resource.MODULE);
    		sce.getServletContext().setAttribute("menu", Resource.MENU);
    		sce.getServletContext().setAttribute("operate", Resource.OPERATE);
    		
    		sce.getServletContext().setAttribute("U", Acl.USER);
    		sce.getServletContext().setAttribute("R", Acl.ROLE);
    		
    	}
    }
