
    public class LoginInterceptor implements HandlerInterceptor {}
    
###     或：

    public class LoginInterceptor extends HandlerInterceptorAdapter {
    	protected static final Log logger = LogFactory.getLog(LoginInterceptor.class);
    	@Override
    	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    			throws Exception {
    		logger.info("==preHandle===>>>请求处理之前进行调用（Controller方法调用之前）");
    		String username = (String) request.getSession().getAttribute("username");
    		if (null == username || "".equals(username)) {
    			request.getRequestDispatcher("/login").forward(request, response);
    //			response.sendRedirect("/login");
    //			returnJson(response, "hello");
    			return false;
    		}
    		return true;
    	}
    
    	@Override
    	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    			ModelAndView modelAndView) throws Exception {
    		logger.info("==postHandle===>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    	}
    
    	@Override
    	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    			throws Exception {
    		logger.info("==afterCompletion===>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    	}
    
    	@Override
    	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
    			throws Exception {
    		logger.info("==afterConcurrentHandlingStarted===");
    		super.afterConcurrentHandlingStarted(request, response, handler);
    	}
    	
    	private static void returnJson(HttpServletResponse response, String json) throws Exception {
    		PrintWriter writer = null;
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("text/html; charset=utf-8");
    		try {
    			writer = response.getWriter();
    			writer.print(json);
    		} catch (IOException e) {
    			logger.error("response error", e);
    		} finally {
    			if (writer != null)
    				writer.close();
    		}
    	}
    }

    @Configuration
    public class MyMvcConfig {
    	@Bean
    	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
    		return new WebMvcConfigurerAdapter() {
    			@Override
    			public void addInterceptors(InterceptorRegistry registry) {
    				registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
    			}
    		};
    	}
    }
