package com.fsalliance.core.po;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TabSystemInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.fsalliance.core.po.TabSystemInfo
 * @author MyEclipse Persistence Tools
 */

public class TabSystemInfoDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TabSystemInfoDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(TabSystemInfo transientInstance) {
		log.debug("saving TabSystemInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabSystemInfo persistentInstance) {
		log.debug("deleting TabSystemInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TabSystemInfo findById(com.fsalliance.core.po.TabSystemInfoId id) {
		log.debug("getting TabSystemInfo instance with id: " + id);
		try {
			TabSystemInfo instance = (TabSystemInfo) getHibernateTemplate()
					.get("com.fsalliance.core.po.TabSystemInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabSystemInfo instance) {
		log.debug("finding TabSystemInfo instance by example");
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
		log.debug("finding TabSystemInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TabSystemInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TabSystemInfo instances");
		try {
			String queryString = "from TabSystemInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabSystemInfo merge(TabSystemInfo detachedInstance) {
		log.debug("merging TabSystemInfo instance");
		try {
			TabSystemInfo result = (TabSystemInfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabSystemInfo instance) {
		log.debug("attaching dirty TabSystemInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabSystemInfo instance) {
		log.debug("attaching clean TabSystemInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabSystemInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabSystemInfoDAO) ctx.getBean("TabSystemInfoDAO");
	}
}