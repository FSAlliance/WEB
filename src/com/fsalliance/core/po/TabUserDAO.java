package com.fsalliance.core.po;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.web.multipart.MultipartFile;

/**
 * A data access object (DAO) providing persistence and search support for
 * TabUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.fsalliance.core.po.TabUser
 * @author MyEclipse Persistence Tools
 */

public class TabUserDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TabUserDAO.class);
	// property constants
	public static final String _SPHONE_NUM = "SPhoneNum";
	public static final String _SNAME = "SName";
	public static final String _SPATH = "SPath";
	public static final String _SPASSWORD = "SPassword";
	public static final String _SUSER_PIC = "SUserPic";
	public static final String _SALIPAY_NUM = "SAlipayNum";
	public static final String _SINVITE_NUM = "SInviteNum";
	public static final String _SPARENT_ID = "SParentId";
	public static final String _DBALANCE_NUM = "DBalanceNum";
	public static final String _DCASHING = "DCashing";
	public static final String _DCASHED = "DCashed";
	public static final String _ISCORE_NUM = "IScoreNum";

	protected void initDao() {
		// do nothing
	}

	public void save(TabUser transientInstance) {
		log.debug("saving TabUser instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabUser persistentInstance) {
		log.debug("deleting TabUser instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(java.lang.String id) {
		log.debug("deleting TabUser instance with id: " + id);
		try {
			String deleteString = "delete from TabUser as model where model.id = ?";
			getHibernateTemplate().bulkUpdate(deleteString, id);
			getHibernateTemplate().clear();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void modify(TabUser instance) {
		log.debug("modifing TabUser instance");
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("update TabUser set ");
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
				if (name.equals("SUserId")) {
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

	public TabUser findById(java.lang.String id) {
		log.debug("getting TabUser instance with id: " + id);
		try {
			TabUser instance = (TabUser) getHibernateTemplate().get(
					"com.fsalliance.core.po.TabUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabUser instance) {
		log.debug("finding TabUser instance by example");
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
		log.debug("finding TabUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TabUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySPhoneNum(Object SPhoneNum) {
		return findByProperty(_SPHONE_NUM, SPhoneNum);
	}

	public List findBySName(Object SName) {
		return findByProperty(_SNAME, SName);
	}

	public List findBySPath(Object SPath) {
		return findByProperty(_SPATH, SPath);
	}

	public List findBySPassword(Object SPassword) {
		return findByProperty(_SPASSWORD, SPassword);
	}

	public List findBySUserPic(Object SUserPic) {
		return findByProperty(_SUSER_PIC, SUserPic);
	}

	public List findBySAlipayNum(Object SAlipayNum) {
		return findByProperty(_SALIPAY_NUM, SAlipayNum);
	}

	public List findBySInviteNum(Object SInviteNum) {
		return findByProperty(_SINVITE_NUM, SInviteNum);
	}

	public List findBySParentId(Object SParentId) {
		return findByProperty(_SPARENT_ID, SParentId);
	}

	public List findByDBalanceNum(Object DBalanceNum) {
		return findByProperty(_DBALANCE_NUM, DBalanceNum);
	}

	public List findByDCashing(Object DCashing) {
		return findByProperty(_DCASHING, DCashing);
	}

	public List findByDCashed(Object DCashed) {
		return findByProperty(_DCASHED, DCashed);
	}

	public List findByIScoreNum(Object IScoreNum) {
		return findByProperty(_ISCORE_NUM, IScoreNum);
	}

	public List findAll() {
		log.debug("finding all TabUser instances");
		try {
			String queryString = "from TabUser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabUser merge(TabUser detachedInstance) {
		log.debug("merging TabUser instance");
		try {
			TabUser result = (TabUser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabUser instance) {
		log.debug("attaching dirty TabUser instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabUser instance) {
		log.debug("attaching clean TabUser instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabUserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabUserDAO) ctx.getBean("TabUserDAO");
	}
	
	
	/**
	 * 更新登录时间
	 * @param userId 用户ID
	 * @param loginTime 登录时间
	 * @return
	 */
	public int updateLoginTime(String userId, Timestamp loginTime){
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(" UPDATE TAB_USER SET DT_LOGIN_TIME = ? WHERE S_USER_ID = ? ");
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(strBuf.toString());
		query.setParameter(0, loginTime);
		query.setParameter(1, userId);
		int result = query.executeUpdate();
		return result;
	}
	
	/**
	 * 更新支付宝帐号
	 * @param userId 用户ID
	 * @param alipayNum 支付宝帐号
	 * @return
	 */
	public int updateAlipayNum(String userId, String alipayNum){
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("UPDATE TAB_USER t SET t.S_ALIPAY_NUM = ? WHERE S_USER_ID = ? ");
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(strBuf.toString());
		query.setParameter(0, alipayNum);
		query.setParameter(1, userId);
		int result = query.executeUpdate();
		return result;
	}
	

	/**
	 * 更新用户名称
	 * @param userId 用户ID
	 * @param userName 用户名称
	 * @return
	 */
	public int updateUserName(String userId, String userName){
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(" UPDATE TAB_USER SET S_NAME = ? WHERE S_USER_ID = ? ");
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(strBuf.toString());
		query.setParameter(0, userName);
		query.setParameter(1, userId);
		int result = query.executeUpdate();
		return result;
	}

	/**
	 * 更新密码
	 * @param userId 用户ID
	 * @param password 密码
	 * @return
	 */
	public int updatePassword(String userId, String password){
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(" UPDATE TAB_USER SET S_PASSWORD = ? WHERE S_USER_ID = ? ");
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(strBuf.toString());
		query.setParameter(0, password);
		query.setParameter(1, userId);
		int result = query.executeUpdate();
		return result;
	}

	/**
	 * 更新用户头像
	 * @param userId 用户ID
	 * @param userName 用户头像路径
	 * @return
	 */
	public int updateUserPhoto(String userId, String  path){
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(" UPDATE TAB_USER SET S_USER_PIC = ? WHERE S_USER_ID = ? ");
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(strBuf.toString());
		query.setParameter(0, path);
		query.setParameter(1, userId);
		int result = query.executeUpdate();
		return result;
  } 
	
	/**
	 * 用户提现，更新余额
	 * @param userId 用户ID
	 * @param money 提现金额
	 * @return
	 */
	public int updateBalance(String userId, Double money){
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(" UPDATE TAB_USER SET D_BALANCE_NUM = D_BALANCE_NUM - ?, D_CASHING = D_CASHING + ? WHERE S_USER_ID = ? ");
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(strBuf.toString());
		query.setParameter(0, money);
		query.setParameter(1, money);
		query.setParameter(2, userId);
		int result = query.executeUpdate();
		return result;
  } 

}