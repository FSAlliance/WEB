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
import org.hibernate.SQLQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TabCashRecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.fsalliance.core.po.TabCashRecord
 * @author MyEclipse Persistence Tools
 */

public class TabCashRecordDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TabCashRecordDAO.class);
	// property constants
	public static final String _SUSER_ID = "SUserId";
	public static final String _ICASH_STATUS = "ICashStatus";
	public static final String _DCASH_BALANCE_NUM = "DCashBalanceNum";

	protected void initDao() {
		// do nothing
	}

	public void save(TabCashRecord transientInstance) {
		log.debug("saving TabCashRecord instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabCashRecord persistentInstance) {
		log.debug("deleting TabCashRecord instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(java.lang.Integer id) {
		log.debug("deleting TabCashRecord instance with id: " + id);
		try {
			String deleteString = "delete from TabCashRecord as model where model.id = ?";
			getHibernateTemplate().bulkUpdate(deleteString, id);
			getHibernateTemplate().clear();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void modify(TabCashRecord instance) {
		log.debug("modifing TabCashRecord instance");
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("update TabCashRecord set ");
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
				if (name.equals("IId")) {
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

	public TabCashRecord findById(java.lang.Integer id) {
		log.debug("getting TabCashRecord instance with id: " + id);
		try {
			TabCashRecord instance = (TabCashRecord) getHibernateTemplate()
					.get("com.fsalliance.core.po.TabCashRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabCashRecord instance) {
		log.debug("finding TabCashRecord instance by example");
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
		log.debug("finding TabCashRecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TabCashRecord as model where model."
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

	public List findByICashStatus(Object ICashStatus) {
		return findByProperty(_ICASH_STATUS, ICashStatus);
	}

	public List findByDCashBalanceNum(Object DCashBalanceNum) {
		return findByProperty(_DCASH_BALANCE_NUM, DCashBalanceNum);
	}

	public List findAll() {
		log.debug("finding all TabCashRecord instances");
		try {
			String queryString = "from TabCashRecord";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabCashRecord merge(TabCashRecord detachedInstance) {
		log.debug("merging TabCashRecord instance");
		try {
			TabCashRecord result = (TabCashRecord) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabCashRecord instance) {
		log.debug("attaching dirty TabCashRecord instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabCashRecord instance) {
		log.debug("attaching clean TabCashRecord instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabCashRecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabCashRecordDAO) ctx.getBean("TabCashRecordDAO");
	}
	
	
	/**
	 * 获取用户提现记录
	 * @param userId 用户ID
	 * 
	 * @return
	 */
	public List getPresentRecordList(String userId, int pageNo, int pageSize){
		StringBuffer strBuf = new StringBuffer();
		int start = (pageNo - 1) * pageSize;
		strBuf.append(" SELECT * FROM TAB_CASH_RECORD WHERE S_USER_ID = :userId");
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(strBuf.toString());
		query.setFirstResult(start).setMaxResults(pageSize);
		query.setParameter("userId", userId);
		List list = query.list();
		return list;
  } 
}