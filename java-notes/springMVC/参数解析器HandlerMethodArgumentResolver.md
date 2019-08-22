    
###     自定义方法参数解析器HandlerMethodArgumentResolver作用：
    
    对请求方法的参数进行检查，通过supportsParameter方法对请求方法的参数进行逐个检查，当supportsParameter返回true时，则会调用rresolveArgument方法，当supportsParameter返回false时（则不会调用resolveArgument） 接着校验下一个参数
    
###     案例1、
####     自定义解析器
    @Service
    public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    	@Autowired
    	MiaoshaUserService userService;
    
    	public boolean supportsParameter(MethodParameter parameter) {
    	    			
    		// if(MiaoshaUser.class == parameter.getParameterType()){}
    		
    		// 可得到方法上的注解
    		// 可得到方法名
    		// 可得到方法参数的注解
    		// 可得到方法参数名
    		Annotation[] ann = parameter.getMethodAnnotations();
    		for (Annotation an : ann) {
    			an.annotationType();
    		}
    		parameter.getMethod().getName();
    		parameter.getParameterName();
    		Class<?> clazz = parameter.getParameterType();
    		return clazz == MiaoshaUser.class;
    	}
    
    	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
    			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

    		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
    		String passValue = request.getParameter(parameter.getParameterName());
    		String paramToken = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
    		String cookieToken = getCookieValue(request, MiaoshaUserService.COOKI_NAME_TOKEN);
    		if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
    			return null;
    		}
    		String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
    		return userService.getByToken(response, token);
    	}
    
    	private String getCookieValue(HttpServletRequest request, String cookiName) {
    		Cookie[] cookies = request.getCookies();
    		for (Cookie cookie : cookies) {
    			if (cookie.getName().equals(cookiName)) {
    				return cookie.getValue();
    			}
    		}
    		return null;
    	}
    }
    
####     将解析器加载进容器
    @Configuration
    public class WebConfig extends WebMvcConfigurerAdapter {
    	@Autowired
    	UserArgumentResolver userArgumentResolver;
    
    	@Override
    	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    		argumentResolvers.add(userArgumentResolver);
    	}
    }

### 案例2、

    @Component
    public class UserBeanResolver implements HandlerMethodArgumentResolver {
        @Autowired
        private UserInterface userInterface;
        private static final String TOKEN_NAME = "token";
        private static final String TOKEN_HEADER_NAME = "utoken";
    
        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            methodParameter.
            return methodParameter.getParameterAnnotation(UserBean.class) != null && methodParameter.getParameterType().equals(P2puserBean.class);
        }
    
        @Override
        public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
            UserBean userBeanAnnotation = methodParameter.getParameterAnnotation(UserBean.class);
            String token = ((ServletWebRequest) nativeWebRequest).getRequest().getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                token = ((ServletWebRequest) nativeWebRequest).getRequest().getHeader(TOKEN_HEADER_NAME);
            }
            if (userBeanAnnotation.checked() && StringUtils.isBlank(token)) {
                throw new BusinessException(BasicErrorEnum.USER_NOT_FOUND);
            }
            P2puserBean userBean = getUserInfoByToken(token);
            if (userBeanAnnotation.checked() && userBean == null) {
                throw new BusinessException(BasicErrorEnum.USER_NOT_FOUND);
            }
            return userBean;
        }
    
        private P2puserBean getUserInfoByToken(String token) {
            DataModel<P2puserBean> result = userInterface.userInfo(token);
            return result.getData();
        }
    }
    
    @Configuration
    public class WebConfig extends WebMvcConfigurationSupport {
        @Autowired
        private UserBeanResolver userBeanResolver;
        @Autowired
        ConcurrentControlInterceptor concurrentControlInterceptor;
    
        @Override
        protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            argumentResolvers.add(userBeanResolver);
        }
    
        @Override
        protected void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(concurrentControlInterceptor).addPathPatterns("/**");
        }
    
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }