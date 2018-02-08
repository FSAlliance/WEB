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
 * TabFile entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.fsalliance.core.po.TabFile
 * @author MyEclipse Persistence Tools
 */

public class TabFileDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TabFileDAO.class);
	// property constants
	public static final String _SPARENT_ID = "SParentId";
	public static final String _SFILE_PATH = "SFilePath";
	public static final String _SFILE_NAME = "SFileName";
	public static final String _ITYPE_ID = "ITypeId";
	public static final String _SAUTHOR = "SAuthor";
	public static final String _SFILE_SIZE = "SFileSize";
	public static final String _SFILE_TYPE = "SFileType";
	public static final String _SID = "SId";


	protected void initDao() {
		// do nothing
	}

	public void save(TabFile transientInstance) {
		log.debug("saving TabFile instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabFile persistentInstance) {
		log.debug("deleting TabFile instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(java.lang.String id) {
		log.debug("deleting TabFile instance with id: " + id);
		try {
			String deleteString = "delete from TabFile as model where model.id = ?";
			getHibernateTemplate().bulkUpdate(deleteString, id);
			getHibernateTemplate().clear();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void modify(TabFile instance) {
		log.debug("modifing TabFile instance");
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("update TabFile set ");
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

	public TabFile findById(java.lang.String id) {
		log.debug("getting TabFile instance with id: " + id);
		try {
			TabFile instance = (TabFile) getHibernateTemplate().get(
					"com.fsalliance.core.po.TabFile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabFile instance) {
		log.debug("finding TabFile instance by example");
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
		log.debug("finding TabFile instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TabFile as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySParentId(Object SParentId) {
		return findByProperty(_SPARENT_ID, SParentId);
	}

	public List findBySFilePath(Object SFilePath) {
		return findByProperty(_SFILE_PATH, SFilePath);
	}

	public List findBySFileName(Object SFileName) {
		return findByProperty(_SFILE_NAME, SFileName);
	}

	public List findByITypeId(Object ITypeId) {
		return findByProperty(_ITYPE_ID, ITypeId);
	}

	public List findBySAuthor(Object SAuthor) {
		return findByProperty(_SAUTHOR, SAuthor);
	}

	public List findBySFileSize(Object SFileSize) {
		return findByProperty(_SFILE_SIZE, SFileSize);
	}

	public List findBySFileType(Object SFileType) {
		return findByProperty(_SFILE_TYPE, SFileType);
	}
	public TabFile findTabFileById(Object Sid) {
		List list = findByProperty(_SID, Sid);
		if(list!=null&&list.size()>0)
		{
			return (TabFile)list.get(0);
		}
		return null;
	}
	
	public List findAll() {
		log.debug("finding all TabFile instances");
		try {
			String queryString = "from TabFile";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabFile merge(TabFile detachedInstance) {
		log.debug("merging TabFile instance");
		try {
			TabFile result = (TabFile) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabFile instance) {
		log.debug("attaching dirty TabFile instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabFile instance) {
		log.debug("attaching clean TabFile instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabFileDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabFileDAO) ctx.getBean("TabFileDAO");
	}
}