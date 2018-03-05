package com.fsalliance.core.po;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TabOrder entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.fsalliance.core.po.TabOrder
 * @author MyEclipse Persistence Tools
 */

public class TabOrderDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TabOrderDAO.class);
	// property constants
	public static final String _SAUCTION_TITLE = "SAuctionTitle";
	public static final String _SAUCTION_ID = "SAuctionId";
	public static final String _SEXNICKNAME = "SExnickname";
	public static final String _IAUCTION_NUM = "IAuctionNum";
	public static final String _DPAY_PRICE = "DPayPrice";
	public static final String _IPAY_STATUS = "IPayStatus";
	public static final String _ITKBIZTAG = "ITkbiztag";
	public static final String _DDISCOUNT_AND_SUBSIDY_TO_STRING = "DDiscountAndSubsidyToString";
	public static final String _DSHARE_RATE = "DShareRate";
	public static final String _DTOTAL_ALIPAY_FEE_STRING = "DTotalAlipayFeeString";
	public static final String _DFEE_STRING = "DFeeString";
	public static final String _DSETTLEMENT_AMOUNT = "DSettlementAmount";
	public static final String _DFORECAST_INCOME = "DForecastIncome";
	public static final String _DFINAL_DISCOUNT_TOSTRING = "DFinalDiscountTostring";
	public static final String _DCOMMISSION_AMOUNT = "DCommissionAmount";
	public static final String _DSUBSIDY_RATIO = "DSubsidyRatio";
	public static final String _DSUBSIDY_AMOUNT = "DSubsidyAmount";
	public static final String _ISUBSIDY_TYPE = "ISubsidyType";
	public static final String _ITERMINAL_TYPE = "ITerminalType";
	public static final String _STHIRD_SERVICE = "SThirdService";
	public static final String _SCATEGORY = "SCategory";
	public static final String _SSOURCE_MEDIUM = "SSourceMedium";
	public static final String _SSOURCE_MEDIUM_NAME = "SSourceMediumName";
	public static final String _SADSENSE_ID = "SAdsenseId";
	public static final String _SADSENSE_NAME = "SAdsenseName";

	protected void initDao() {
		// do nothing
	}

	public void save(TabOrder transientInstance) {
		log.debug("saving TabOrder instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabOrder persistentInstance) {
		log.debug("deleting TabOrder instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(java.lang.String id) {
		log.debug("deleting TabOrder instance with id: " + id);
		try {
			String deleteString = "delete from TabOrder as model where model.id = ?";
			getHibernateTemplate().bulkUpdate(deleteString, id);
			getHibernateTemplate().clear();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void modify(TabOrder instance) {
		log.debug("modifing TabOrder instance");
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("update TabOrder set ");
			Object idVale = null;

			List<Object> objs = new ArrayList<Object>();
			Field[] field = instance.getClass().getDeclaredFields();
			for (int i = 0; i < field.length; ++i) {
				String name = field[i].getName();
				Object val = null;
				try {
					Method m = instance.getClass().getMethod(
							"get" + name.substring(0, 1).toUpperCase()
									+ name.substring(1));
					val = m.invoke(instance); // getter
				} catch (Exception e) {
					continue;
				}
				if (val == null) {
					continue;
				}
				if (name.equals("SId")) {
					idVale = val;
					continue;
				}
				if (objs.size() != 0) {
					hql.append(", ");
				}
				hql.append(name);
				hql.append(" = ? ");
				objs.add(val);
			}
			hql.append("where id = ? ");
			objs.add(idVale);

			getHibernateTemplate().bulkUpdate(hql.toString(), objs.toArray());
			getHibernateTemplate().clear();
			log.debug("modify successful");
		} catch (RuntimeException re) {
			log.error("modify failed", re);
			throw re;
		}
	}

	public TabOrder findById(java.lang.String id) {
		log.debug("getting TabOrder instance with id: " + id);
		try {
			TabOrder instance = (TabOrder) getHibernateTemplate().get(
					"com.fsalliance.core.po.TabOrder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabOrder instance) {
		log.debug("finding TabOrder instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TabOrder instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TabOrder as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySAuctionTitle(Object SAuctionTitle) {
		return findByProperty(_SAUCTION_TITLE, SAuctionTitle);
	}

	public List findBySAuctionId(Object SAuctionId) {
		return findByProperty(_SAUCTION_ID, SAuctionId);
	}

	public List findBySExnickname(Object SExnickname) {
		return findByProperty(_SEXNICKNAME, SExnickname);
	}

	public List findByIAuctionNum(Object IAuctionNum) {
		return findByProperty(_IAUCTION_NUM, IAuctionNum);
	}

	public List findByDPayPrice(Object DPayPrice) {
		return findByProperty(_DPAY_PRICE, DPayPrice);
	}

	public List findByIPayStatus(Object IPayStatus) {
		return findByProperty(_IPAY_STATUS, IPayStatus);
	}

	public List findByITkbiztag(Object ITkbiztag) {
		return findByProperty(_ITKBIZTAG, ITkbiztag);
	}

	public List findByDDiscountAndSubsidyToString(
			Object DDiscountAndSubsidyToString) {
		return findByProperty(_DDISCOUNT_AND_SUBSIDY_TO_STRING,
				DDiscountAndSubsidyToString);
	}

	public List findByDShareRate(Object DShareRate) {
		return findByProperty(_DSHARE_RATE, DShareRate);
	}

	public List findByDTotalAlipayFeeString(Object DTotalAlipayFeeString) {
		return findByProperty(_DTOTAL_ALIPAY_FEE_STRING, DTotalAlipayFeeString);
	}

	public List findByDFeeString(Object DFeeString) {
		return findByProperty(_DFEE_STRING, DFeeString);
	}

	public List findByDSettlementAmount(Object DSettlementAmount) {
		return findByProperty(_DSETTLEMENT_AMOUNT, DSettlementAmount);
	}

	public List findByDForecastIncome(Object DForecastIncome) {
		return findByProperty(_DFORECAST_INCOME, DForecastIncome);
	}

	public List findByDFinalDiscountTostring(Object DFinalDiscountTostring) {
		return findByProperty(_DFINAL_DISCOUNT_TOSTRING, DFinalDiscountTostring);
	}

	public List findByDCommissionAmount(Object DCommissionAmount) {
		return findByProperty(_DCOMMISSION_AMOUNT, DCommissionAmount);
	}

	public List findByDSubsidyRatio(Object DSubsidyRatio) {
		return findByProperty(_DSUBSIDY_RATIO, DSubsidyRatio);
	}

	public List findByDSubsidyAmount(Object DSubsidyAmount) {
		return findByProperty(_DSUBSIDY_AMOUNT, DSubsidyAmount);
	}

	public List findByISubsidyType(Object ISubsidyType) {
		return findByProperty(_ISUBSIDY_TYPE, ISubsidyType);
	}

	public List findByITerminalType(Object ITerminalType) {
		return findByProperty(_ITERMINAL_TYPE, ITerminalType);
	}

	public List findBySThirdService(Object SThirdService) {
		return findByProperty(_STHIRD_SERVICE, SThirdService);
	}

	public List findBySCategory(Object SCategory) {
		return findByProperty(_SCATEGORY, SCategory);
	}

	public List findBySSourceMedium(Object SSourceMedium) {
		return findByProperty(_SSOURCE_MEDIUM, SSourceMedium);
	}

	public List findBySSourceMediumName(Object SSourceMediumName) {
		return findByProperty(_SSOURCE_MEDIUM_NAME, SSourceMediumName);
	}

	public List findBySAdsenseId(Object SAdsenseId) {
		return findByProperty(_SADSENSE_ID, SAdsenseId);
	}

	public List findBySAdsenseName(Object SAdsenseName) {
		return findByProperty(_SADSENSE_NAME, SAdsenseName);
	}

	public List findAll() {
		log.debug("finding all TabOrder instances");
		try {
			String queryString = "from TabOrder";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabOrder merge(TabOrder detachedInstance) {
		log.debug("merging TabOrder instance");
		try {
			TabOrder result = (TabOrder) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabOrder instance) {
		log.debug("attaching dirty TabOrder instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabOrder instance) {
		log.debug("attaching clean TabOrder instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabOrderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabOrderDAO) ctx.getBean("TabOrderDAO");
	}
}