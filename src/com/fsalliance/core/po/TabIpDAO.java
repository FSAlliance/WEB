package com.fsalliance.core.po;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for TabIp
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.fsalliance.core.po.TabIp
 * @author MyEclipse Persistence Tools
 */

public class TabIpDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TabIpDAO.class);
	// property constants
	public static final String _SNAME = "SName";
	public static final String _SIP = "SIp";
	public static final String _SMAC = "SMac";
	public static final String _ITYPE = "IType";

	protected void initDao() {
		// do nothing
	}

	public void save(TabIp transientInstance) {
		log.debug("saving TabIp instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabIp persistentInstance) {
		log.debug("deleting TabIp instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TabIp findById(java.lang.String id) {
		log.debug("getting TabIp instance with id: " + id);
		try {
			TabIp instance = (TabIp) getHibernateTemplate().get(
					"com.fsalliance.core.po.TabIp", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabIp instance) {
		log.debug("finding TabIp instance by example");
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
		log.debug("finding TabIp instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TabIp as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySName(Object SName) {
		return findByProperty(_SNAME, SName);
	}

	public List findBySIp(Object SIp) {
		return findByProperty(_SIP, SIp);
	}

	public List findBySMac(Object SMac) {
		return findByProperty(_SMAC, SMac);
	}

	public List findByIType(Object IType) {
		return findByProperty(_ITYPE, IType);
	}

	public List findAll() {
		log.debug("finding all TabIp instances");
		try {
			String queryString = "from TabIp";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabIp merge(TabIp detachedInstance) {
		log.debug("merging TabIp instance");
		try {
			TabIp result = (TabIp) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabIp instance) {
		log.debug("attaching dirty TabIp instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabIp instance) {
		log.debug("attaching clean TabIp instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabIpDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabIpDAO) ctx.getBean("TabIpDAO");
	}
}