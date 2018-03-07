package com.fsalliance.core.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.fsalliance.core.po.TabOrderDAO;
import com.fsalliance.core.po.TabUserAlipay;
import com.fsalliance.core.po.TabUserAlipayDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.vo.CLS_VO_Order;
import com.fsalliance.core.vo.CLS_VO_Result;
import com.fsalliance.core.vo.CLS_VO_User_Order;
/**
 * 关联用户的淘宝帐号
 * @author yuanxueyuan
 *
 */
@Transactional
public class CLS_BO_User_Order {
	
	@Resource(name = "TabUserAlipayDAO")
	private TabUserAlipayDAO tabUserAlipayDAO;
	
	@Resource(name = "TabOrderDAO")
	private TabOrderDAO tabOrderDAO;

    /**
     * 添加订单号-淘宝号
     * @param user 
     * @return
     */
	public CLS_VO_Result addOrderTaobao(CLS_VO_User_Order order){
		//截取四位数字
		String myorder = "";
		if (order.getOrderId().length() >= 4) {
			myorder = order.getOrderId().substring(order.getOrderId().length() - 4, order.getOrderId().length());
        }
		CLS_VO_Result result = new CLS_VO_Result();
		
		//根据订单号查询订单
		List<CLS_VO_Order> orderList = tabOrderDAO.findOrderById(order.getOrderId());
		if (orderList == null || orderList.size() <= 0 || order == null) {
			result.setRet(CLS_FSAlliance_Error.ERROR_ORDER_NO_HAVE);
			return result;
		}
		//根据淘宝号查询订单
		TabUserAlipay userAlipay = tabUserAlipayDAO.findById(myorder);
		if (userAlipay != null && !userAlipay.getSUserId().equals(order.getUserId())) {
			//已经有人利用了此淘宝账号
			result.setRet(CLS_FSAlliance_Error.ERROR_ORDER_HAVE);
			return result;
		}
		
		//根据用户id以及淘宝账号查询关联表中有没有
		List<TabUserAlipay> list = tabUserAlipayDAO.getUserOrderByUserID(myorder,order.getUserId());
		if (list == null || list.size() <= 0) {
			//关联表中没有此淘宝账号则插入数据并返回
			TabUserAlipay tabUserAlipay = new TabUserAlipay();
			tabUserAlipay.setSAlipayOrderid(myorder);
			tabUserAlipay.setSUserId(order.getUserId());
			tabUserAlipayDAO.save(tabUserAlipay);
		}
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent(orderList.get(0));
		return result;
	}

	
}
