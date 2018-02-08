package com.fsalliance.core.rest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.util.ConfigManager;
import com.fsalliance.core.vo.CLS_VO_TB_Favorite;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkCouponGetRequest;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.request.TbkUatmFavoritesGetRequest;
import com.taobao.api.request.TbkUatmFavoritesItemGetRequest;
import com.taobao.api.response.TbkCouponGetResponse;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkItemGetResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;
import com.taobao.api.response.TbkUatmFavoritesGetResponse;
import com.taobao.api.response.TbkUatmFavoritesItemGetResponse;


  
@Controller  
@RequestMapping("/Goods")  
public class CLS_TaoBao {  

//	private String appkey = ConfigManager.getInstance().getConfigItem("taobao_lianmeng_app_key", "");
//	private String app_secret = ConfigManager.getInstance().getConfigItem("taobao_lianmeng_app_secret", "");
//	private String app_url = ConfigManager.getInstance().getConfigItem("taobao_lianmeng_app_url", "");
	private String appkey = "24784125";
	private String app_secret = "b2aa1b5afe56ba5979f75e6fb4ab37a6";
	private String app_url = "http://gw.api.taobao.com/router/rest";


	/**
	 * 查询选品库
	 * @param req
	 * @param resp
	 * @param pageNo
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value="/favoriteGroup")
	public void getFavoriteGroup(HttpServletRequest req, HttpServletResponse resp, long pageNo, long pageSize) throws Exception  {
		System.out.println("app_url: "+app_url);
		System.out.println("appkey: "+appkey);
		System.out.println("app_secret: "+app_secret);
		System.out.println("pageNo: "+pageNo);
		System.out.println("pageSize: "+pageSize);
		TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
		TbkUatmFavoritesGetRequest favoriteRequest = new TbkUatmFavoritesGetRequest();
		favoriteRequest.setPageNo(pageNo);
		favoriteRequest.setPageSize(pageSize);
		favoriteRequest.setFields("favorites_title,favorites_id,type");
		favoriteRequest.setType(1L);
		TbkUatmFavoritesGetResponse response = client.execute(favoriteRequest);
		String body = response.getBody();
		System.out.println(body);
		resp.getWriter().println(body);
	}
	
	/**
	 * 查询选品库中的信息
	 * @param req
	 * @param resp
	 * @param platForm
	 * @param pageSize
	 * @param adzoneId
	 * @param uuid
	 * @param favoritesId
	 * @param pageNo
	 * @throws Exception
	 */
	@RequestMapping(value="/favoriteItem")
	public void getFavoriteItem(HttpServletRequest req, HttpServletResponse resp, CLS_VO_TB_Favorite good) throws Exception  {
		TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
		TbkUatmFavoritesItemGetRequest favoriteItemRequest = new TbkUatmFavoritesItemGetRequest();
		favoriteItemRequest.setPlatform(good.getPlatForm());
		favoriteItemRequest.setPageSize(good.getPageSize());
		favoriteItemRequest.setAdzoneId(good.getAdzoneId());
		favoriteItemRequest.setUnid(good.getUuid());
		favoriteItemRequest.setFavoritesId(good.getFavoritesId());
		favoriteItemRequest.setPageNo(good.getPageNo());
		favoriteItemRequest.setFields("num_iid,coupon_info,coupon_click_url,coupon_remain_count,coupon_start_time,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick,shop_title,zk_final_price_wap,event_start_time,event_end_time,tk_rate,status,type");
		TbkUatmFavoritesItemGetResponse rsp = client.execute(favoriteItemRequest);
		resp.getWriter().println(rsp.getBody());
	}
	
	/**
	 * 搜索商品
	 * @param req
	 * @param resp
	 * @param platForm
	 * @param pageSize
	 * @param adzoneId
	 * @param uuid
	 * @param favoritesId
	 * @param pageNo
	 * @throws Exception
	 */
	@RequestMapping(value="/searchGoods")
	public void getSearchGoods(HttpServletRequest req, HttpServletResponse resp) throws Exception  {
		TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
		TbkItemGetRequest searchRequest = new TbkItemGetRequest();
		searchRequest.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
		searchRequest.setQ("女装");
		searchRequest.setCat("16,18");
		searchRequest.setItemloc("杭州");
		searchRequest.setSort("tk_rate_des");
		searchRequest.setIsTmall(false);
		searchRequest.setIsOverseas(false);
		searchRequest.setStartPrice(10L);
		searchRequest.setEndPrice(10L);
		searchRequest.setStartTkRate(123L);
		searchRequest.setEndTkRate(123L);
		searchRequest.setPlatform(1L);
		searchRequest.setPageNo(123L);
		searchRequest.setPageSize(20L);
		TbkItemGetResponse rsp = client.execute(searchRequest);
		resp.getWriter().println(rsp.getBody());
		
		
	}
	
	/**
	 * 查询商品详情
	 * @param platForm 链接形式：1：PC，2：无线，默认：１
	 * @param goodId 商品ID串，用,分割，从taobao.tbk.item.get接口获取num_iid字段，最大40个
	 */
	@RequestMapping(value="/goodInfo")
	public void getGoodInfo(HttpServletRequest req, HttpServletResponse resp, long platForm, String goodId) throws Exception  {
		TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
		TbkItemInfoGetRequest goodInfoRequest = new TbkItemInfoGetRequest();
		goodInfoRequest.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url");
		goodInfoRequest.setPlatform(platForm);
		goodInfoRequest.setNumIids(goodId);
		TbkItemInfoGetResponse rsp = client.execute(goodInfoRequest);
		resp.getWriter().println(rsp.getBody());
	}
	
	/**
	 * 生成淘口令
	 * @param userId 淘宝用户ID
	 * @param text 口令弹框内容
	 * @param url 口令跳转目标页
	 * @param logo 口令弹框logoURL
	 * @param ext 扩展字段JSON格式
	 */
	@RequestMapping(value="/code")
	public void getPassword(HttpServletRequest req, HttpServletResponse resp, String userId, String text, String url, String logo, String ext) throws Exception  {
		TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
		TbkTpwdCreateRequest passwordRequest = new TbkTpwdCreateRequest();
		passwordRequest.setUserId(userId);
		passwordRequest.setText(text);
		passwordRequest.setUrl(url);
		passwordRequest.setLogo(logo);
		passwordRequest.setExt(ext);
		TbkTpwdCreateResponse rsp = client.execute(passwordRequest);
		resp.getWriter().println(rsp.getBody());
	}
	
	/**
	 * 好券清单API【导购】
	 * @param req
	 * @param resp
	 * @param platForm
	 * @param pageSize
	 * @param adzoneId
	 * @param uuid
	 * @param favoritesId
	 * @param pageNo
	 * @throws Exception
	 */
	@RequestMapping(value="/Custom")
	public void getCustomGoods(HttpServletRequest req, HttpServletResponse resp, long adzoneid, long platform, long pageNo, long pageSize) throws Exception  {
		TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
		TbkDgItemCouponGetRequest customRequest = new TbkDgItemCouponGetRequest();
		customRequest.setAdzoneId(adzoneid);
		customRequest.setPlatform(platform);
		customRequest.setCat("");
		customRequest.setPageSize(pageSize);
		customRequest.setQ("");
		customRequest.setPageNo(pageNo);
		TbkDgItemCouponGetResponse rsp = client.execute(customRequest);
		resp.getWriter().println(rsp.getBody());
	}
	
	
	/**
	 * 获取所有的订单
	 * @param req
	 * @param resp
	 * @param platForm
	 * @param pageSize
	 * @param adzoneId
	 * @param uuid
	 * @param favoritesId
	 * @param pageNo
	 * @throws Exception
	 */
	@RequestMapping(value="/orders")
	public void getOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception  {
		/*TaobaoClient client = new DefaultTaobaoClient(APP_KEY, APP_SECRET, APP_URL);
		TbkOrderGetRequest req = new TbkOrderGetRequest();
		req.setFields("tb_trade_parent_id,tb_trade_id,num_iid,item_title,item_num,price,pay_price,seller_nick,seller_shop_title,commission,commission_rate,unid,create_time,earning_time,tk3rd_pub_id,tk3rd_site_id,tk3rd_adzone_id");
		req.setStartTime(StringUtils.parseDateTime("2016-05-23 12:18:22"));
		req.setSpan(600L);
		req.setPageNo(1L);
		req.setPageSize(20L);
		req.setTkStatus(1L);
		req.setOrderQueryType("settle_time");
		TbkOrderGetResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());*/
		
		/*TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
		TbkRebateOrderGetRequest req = new TbkRebateOrderGetRequest();
		req.setFields("tb_trade_parent_id,tb_trade_id,num_iid,item_title,item_num,price,pay_price,seller_nick,seller_shop_title,commission,commission_rate,unid,create_time,earning_time");
		req.setStartTime(StringUtils.parseDateTime("2015-03-05 13:52:08"));
		req.setSpan(600L);
		req.setPageNo(1L);
		req.setPageSize(20L);
		TbkRebateOrderGetResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());*/
		/*TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
		TbkDgItemCouponGetRequest req1 = new TbkDgItemCouponGetRequest();
		req1.setAdzoneId(123L);
		req1.setPlatform(1L);
		req1.setCat("16,18");
		req1.setPageSize(1L);
		req1.setQ("女装");
		req1.setPageNo(1L);
		TbkDgItemCouponGetResponse rsp = client.execute(req1);
		System.out.println(rsp.getBody());*/
		
	}
	
	/**
	 * 获取优惠券信息
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping(value="/getCouponInfo")
	public void getCouponInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception  {
		TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
		TbkCouponGetRequest req1 = new TbkCouponGetRequest();
		req1.setMe("nfr%2BYTo2k1PX18gaNN%2BIPkIG2PadNYbBnwEsv6mRavWieOoOE3L9OdmbDSSyHbGxBAXjHpLKvZbL1320ML%2BCF5FRtW7N7yJ056Lgym4X01A%3D");
		req1.setItemId(543267361167L);
		req1.setActivityId("");
		TbkCouponGetResponse rsp = client.execute(req1);
		System.out.println(rsp.getBody());
	}
	
	@RequestMapping(value="/test")
	public void test(HttpServletRequest req, HttpServletResponse resp, String userId, String text, String url, String logo, String ext) throws Exception  {
//		TaobaoClient client = new DefaultTaobaoClient(app_url, appkey, app_secret);
//		TbkTpwdCreateRequest passwordRequest = new TbkTpwdCreateRequest();
//		passwordRequest.setUserId(userId);
//		passwordRequest.setText(text);
//		passwordRequest.setUrl(url);
//		passwordRequest.setLogo(logo);
//		passwordRequest.setExt(ext);
//		TbkTpwdCreateResponse rsp = client.execute(passwordRequest);
//		resp.getWriter().println(rsp.getBody());
		 
	}
	
	
} 