
    @RequestMapping(value="/to_list", produces="text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model,MiaoshaUser user) {
    	model.addAttribute("user", user);
    	//取缓存
    	String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
    	if(StringUtils.isNotEmpty(html)) {
    		return html;
    	}
    	List<GoodsVo> goodsList = goodsService.listGoodsVo();
    	model.addAttribute("goodsList", goodsList);
    	//SpringWebContext ctx = new SpringWebContext(request,response,request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
    	WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
    	//手动渲染
    	html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
    	if(StringUtils.isNotEmpty(html)) {
    		redisService.set(GoodsKey.getGoodsList, "", html);
    	}
    	return html;
    }




    