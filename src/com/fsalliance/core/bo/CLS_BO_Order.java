package com.fsalliance.core.bo;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.fsalliance.core.po.TabOrder;
import com.fsalliance.core.po.TabOrderDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.vo.CLS_VO_Order;
import com.fsalliance.core.vo.CLS_VO_Order_I;
import com.fsalliance.core.vo.CLS_VO_Result;
/**
 * 订单信息
 * @author yuanxueyuan
 *
 */
@Transactional
public class CLS_BO_Order {
	
	@Resource(name = "TabOrderDAO")
	private TabOrderDAO tabOrderDAO;
	
	/**
	 * 根据订单类型查询订单
	 * @param order
	 * @return
	 */
	public CLS_VO_Result getOrderByType(CLS_VO_Order_I order){
		CLS_VO_Result result = new CLS_VO_Result();
		List<CLS_VO_Order> orders = tabOrderDAO.findOrderByType(order.getUserId(), order.getOrderType(), order.getPageNo(), order.getPageSize());
		if (orders == null || orders.size() <= 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_ORDER_NO_HAVE);
			return result;
		}
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent(orders);
		return result;
	}
}
