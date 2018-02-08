package com.fsalliance.core.po;

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TabComputer entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.fsalliance.core.po.TabComputer
  * @author MyEclipse Persistence Tools 
 */

public class TabComputerDAO extends HibernateDaoSupport  {
		 private static final Log log = LogFactory.getLog(TabComputerDAO.class);
		//property constants
	public static final String _SNAME = "SName";
	public static final String _SIP = "SIp";
	public static final String _ITYPE = "IType";
	public static final String _SUSER = "SUser";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TabComputer transientInstance) {
        log.debug("saving TabComputer instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TabComputer persistentInstance) {
        log.debug("deleting TabComputer instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TabComputer findById( java.lang.String id) {
        log.debug("getting TabComputer instance with id: " + id);
        try {
            TabComputer instance = (TabComputer) getHibernateTemplate()
                    .get("com.fsalliance.core.po.TabComputer", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TabComputer instance) {
        log.debug("finding TabComputer instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding TabComputer instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TabComputer as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findBySName(Object SName
	) {
		return findByProperty(_SNAME, SName
		);
	}
	
	public List findBySIp(Object SIp
	) {
		return findByProperty(_SIP, SIp
		);
	}
	
	public List findByIType(Object IType
	) {
		return findByProperty(_ITYPE, IType
		);
	}
	
	public List findBySUser(Object SUser
	) {
		return findByProperty(_SUSER, SUser
		);
	}
	

	public List findAll() {
		log.debug("finding all TabComputer instances");
		try {
			String queryString = "from TabComputer";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TabComputer merge(TabComputer detachedInstance) {
        log.debug("merging TabComputer instance");
        try {
            TabComputer result = (TabComputer) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TabComputer instance) {
        log.debug("attaching dirty TabComputer instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TabComputer instance) {
        log.debug("attaching clean TabComputer instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TabComputerDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TabComputerDAO) ctx.getBean("TabComputerDAO");
	}
}