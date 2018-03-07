package com.fsalliance.core.po;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TabUserAlipay entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.fsalliance.core.po.TabUserAlipay
 * @author MyEclipse Persistence Tools
 */

public class TabUserAlipayDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TabUserAlipayDAO.class);
	// property constants
	public static final String _SUSER_ID = "SUserId";

	protected void initDao() {
		// do nothing
	}

	public void save(TabUserAlipay transientInstance) {
		log.debug("saving TabUserAlipay instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabUserAlipay persistentInstance) {
		log.debug("deleting TabUserAlipay instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(java.lang.String id) {
		log.debug("deleting TabUserAlipay instance with id: " + id);
		try {
			String deleteString = "delete from TabUserAlipay as model where model.id = ?";
			getHibernateTemplate().bulkUpdate(deleteString, id);
			getHibernateTemplate().clear();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void modify(TabUserAlipay instance) {
		log.debug("modifing TabUserAlipay instance");
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("update TabUserAlipay set ");
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
				if (name.equals("SAlipayOrderid")) {
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

	public TabUserAlipay findById(java.lang.String id) {
		log.debug("getting TabUserAlipay instance with id: " + id);
		try {
			TabUserAlipay instance = (TabUserAlipay) getHibernateTemplate()
					.get("com.fsalliance.core.po.TabUserAlipay", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabUserAlipay instance) {
		log.debug("finding TabUserAlipay instance by example");
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
		log.debug("finding TabUserAlipay instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TabUserAlipay as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySUserId(Object SUserId) {
		return findByProperty(_SUSER_ID, SUserId);
	}

	public List findAll() {
		log.debug("finding all TabUserAlipay instances");
		try {
			String queryString = "from TabUserAlipay";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabUserAlipay merge(TabUserAlipay detachedInstance) {
		log.debug("merging TabUserAlipay instance");
		try {
			TabUserAlipay result = (TabUserAlipay) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabUserAlipay instance) {
		log.debug("attaching dirty TabUserAlipay instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabUserAlipay instance) {
		log.debug("attaching clean TabUserAlipay instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabUserAlipayDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabUserAlipayDAO) ctx.getBean("TabUserAlipayDAO");
	}
	
	public List<TabUserAlipay> getUserOrderByUserID(String orderID, String userID) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(" SELECT * FROM TAB_USER_ALIPAY WHERE S_ALIPAY_ORDERID = ? AND S_USER_ID = ? ");
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(strBuf.toString());
		query.setParameter(0, orderID);
		query.setParameter(1, userID);
		List obj = query.list();
		Iterator iterator = obj.iterator();
		List<TabUserAlipay> tabUserAlipayList = new ArrayList<TabUserAlipay>();
		while (iterator.hasNext()) {
			TabUserAlipay tabUserAlipay = new TabUserAlipay();
			Object[] object = (Object[]) iterator.next();
			if (object[0] != null) {
				tabUserAlipay.setSAlipayOrderid(object[0].toString());
			}
			if (object[1] != null) {
				tabUserAlipay.setSUserId(object[1].toString());
			}
			tabUserAlipayList.add(tabUserAlipay);
		}
		return tabUserAlipayList;
	}
}