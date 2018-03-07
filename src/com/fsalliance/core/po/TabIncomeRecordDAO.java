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
 * TabIncomeRecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.fsalliance.core.po.TabIncomeRecord
 * @author MyEclipse Persistence Tools
 */

public class TabIncomeRecordDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TabIncomeRecordDAO.class);
	// property constants
	public static final String _SUSER_ID = "SUserId";
	public static final String _IINCOME_STATUS = "IIncomeStatus";
	public static final String _DINCOME_BALANCE_NUM = "DIncomeBalanceNum";

	protected void initDao() {
		// do nothing
	}

	public void save(TabIncomeRecord transientInstance) {
		log.debug("saving TabIncomeRecord instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabIncomeRecord persistentInstance) {
		log.debug("deleting TabIncomeRecord instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(java.lang.Integer id) {
		log.debug("deleting TabIncomeRecord instance with id: " + id);
		try {
			String deleteString = "delete from TabIncomeRecord as model where model.id = ?";
			getHibernateTemplate().bulkUpdate(deleteString, id);
			getHibernateTemplate().clear();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void modify(TabIncomeRecord instance) {
		log.debug("modifing TabIncomeRecord instance");
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("update TabIncomeRecord set ");
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

	public TabIncomeRecord findById(java.lang.Integer id) {
		log.debug("getting TabIncomeRecord instance with id: " + id);
		try {
			TabIncomeRecord instance = (TabIncomeRecord) getHibernateTemplate()
					.get("com.fsalliance.core.po.TabIncomeRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabIncomeRecord instance) {
		log.debug("finding TabIncomeRecord instance by example");
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
		log.debug("finding TabIncomeRecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TabIncomeRecord as model where model."
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

	public List findByIIncomeStatus(Object IIncomeStatus) {
		return findByProperty(_IINCOME_STATUS, IIncomeStatus);
	}

	public List findByDIncomeBalanceNum(Object DIncomeBalanceNum) {
		return findByProperty(_DINCOME_BALANCE_NUM, DIncomeBalanceNum);
	}

	public List findAll() {
		log.debug("finding all TabIncomeRecord instances");
		try {
			String queryString = "from TabIncomeRecord";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabIncomeRecord merge(TabIncomeRecord detachedInstance) {
		log.debug("merging TabIncomeRecord instance");
		try {
			TabIncomeRecord result = (TabIncomeRecord) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabIncomeRecord instance) {
		log.debug("attaching dirty TabIncomeRecord instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabIncomeRecord instance) {
		log.debug("attaching clean TabIncomeRecord instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabIncomeRecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabIncomeRecordDAO) ctx.getBean("TabIncomeRecordDAO");
	}
	
	
	/**
	 * 获取用户收入记录
	 * @param userId 用户ID
	 * 
	 * @return
	 */
	public List getIncomeRecordList(String userId, int pageNo, int pageSize){
		StringBuffer strBuf = new StringBuffer();
		int start = (pageNo - 1) * pageSize;
		strBuf.append(" SELECT * FROM TAB_INCOME_RECORD  WHERE S_USER_ID = :userId ORDER BY DT_INCOME_TIME desc" );
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(strBuf.toString());
		query.setFirstResult(start).setMaxResults(pageSize);
		query.setParameter("userId", userId);
		List list = query.list();
		return list;
  }  
}