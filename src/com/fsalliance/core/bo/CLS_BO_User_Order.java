package com.fsalliance.core.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.fsalliance.core.po.TabUser;
import com.fsalliance.core.po.TabUserAlipay;
import com.fsalliance.core.po.TabUserAlipayDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Commen;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.vo.CLS_VO_Result;
import com.fsalliance.core.vo.CLS_VO_USER_ORDER;
/**
 * 关联用户的淘宝帐号
 * @author yuanxueyuan
 *
 */
@Transactional
public class CLS_BO_User_Order {
	
	@Resource(name = "TabUserAlipayDAO")
	private TabUserAlipayDAO tabUserAlipayDAO;

    /**
     * 添加订单号-淘宝号
     * @param user 
     * @return
     */
	public CLS_VO_Result addOrderTaobao(CLS_VO_USER_ORDER order){
		CLS_VO_Result result = new CLS_VO_Result();
		List<TabUserAlipay> list = tabUserAlipayDAO.findBySUserId(order.getUserId());
		
		//如果存在则不让其进行存储，返回绑定成功
		for(TabUserAlipay tabUserAlipay : list) {
			if(tabUserAlipay.getSAlipayOrderid().equals(order.getOrderId())) {
				result.setRet(CLS_FSAlliance_Error.ERROR_ORDER_HAVE);
				return result;
			}
		}
		
		//只允许一个人关联5个淘宝号
		if (list.size() < CLS_FSAlliance_Commen.MAX_ORDER_NUM) {
			TabUserAlipay tabUserAlipay = new TabUserAlipay();
			tabUserAlipay.setSAlipayOrderid(order.getOrderId());
			tabUserAlipay.setSUserId(order.getUserId());
			tabUserAlipayDAO.save(tabUserAlipay);
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_ORDER_MAX);
		}
		return result;
	}

	
}
