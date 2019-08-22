    
###     接口防刷调用

    @AccessLimit(seconds=5, maxCount=5, needLogin=true)
    @RequestMapping(value="/path", method=RequestMethod.GET)
    @ResponseBody
    public Result<String> getMiaoshaPath(HttpServletRequest request, MiaoshaUser user,
    		@RequestParam("goodsId")long goodsId,
    		@RequestParam(value="verifyCode", defaultValue="0")int verifyCode
    		) {
    }
    
###     防刷实现

    @Service
    public class AccessInterceptor extends HandlerInterceptorAdapter {
    	@Autowired
    	MiaoshaUserService userService;
    	@Autowired
    	RedisService redisService;
    	@Override
    	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    			throws Exception {
    		if (handler instanceof HandlerMethod) {
    			MiaoshaUser user = getUser(request, response);
    			UserContext.setUser(user);
    			HandlerMethod hm = (HandlerMethod) handler;
    			AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
    			if (accessLimit == null) {
    				return true;
    			}
    			int seconds = accessLimit.seconds();
    			int maxCount = accessLimit.maxCount();
    			boolean needLogin = accessLimit.needLogin();
    			String key = request.getRequestURI();
    			if (needLogin) {
    				if (user == null) {
    					render(response, CodeMsg.SESSION_ERROR);
    					return false;
    				}
    				key += "_" + user.getId();
    			} else {
    				// do nothing
    			}
    			AccessKey ak = AccessKey.withExpire(seconds);
    			Integer count = redisService.get(ak, key, Integer.class);
    			if (count == null) {
    				redisService.set(ak, key, 1);
    			} else if (count < maxCount) {
    				redisService.incr(ak, key);
    			} else {
    				render(response, CodeMsg.ACCESS_LIMIT_REACHED);
    				return false;
    			}
    		}
    		return true;
    	}
    
    	private void render(HttpServletResponse response, CodeMsg cm) throws Exception {
    		response.setContentType("application/json;charset=UTF-8");
    		OutputStream out = response.getOutputStream();
    		String str = JSON.toJSONString(Result.error(cm));
    		out.write(str.getBytes("UTF-8"));
    		out.flush();
    		out.close();
    	}
    
    	private MiaoshaUser getUser(HttpServletRequest request, HttpServletResponse response) {
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
    		if (cookies == null || cookies.length <= 0) {
    			return null;
    		}
    		for (Cookie cookie : cookies) {
    			if (cookie.getName().equals(cookiName)) {
    				return cookie.getValue();
    			}
    		}
    		return null;
    	}
    }
    
###     本地线程

    public class UserContext {
    	private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();
    	public static void setUser(MiaoshaUser user) {
    		userHolder.set(user);
    	}
    	public static MiaoshaUser getUser() {
    		return userHolder.get();
    	}
    }
    
###     从本地线程拿User对象

    @Service
    public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    	@Autowired
    	MiaoshaUserService userService;
    	public boolean supportsParameter(MethodParameter parameter) {
    		Class<?> clazz = parameter.getParameterType();
    		return clazz == MiaoshaUser.class;
    	}
    	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
    			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    		return UserContext.getUser();
    	}
    }

###     cookie

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
    	redisService.set(MiaoshaUserKey.token, token, user);
    	Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
    	cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
    	cookie.setPath("/");
    	response.addCookie(cookie);
    }