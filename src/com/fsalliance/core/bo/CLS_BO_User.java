package com.fsalliance.core.bo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.mapping.Array;
import org.springframework.transaction.annotation.Transactional;

import com.fsalliance.core.po.TabCashRecord;
import com.fsalliance.core.po.TabCashRecordDAO;
import com.fsalliance.core.po.TabIncomeRecordDAO;
import com.fsalliance.core.po.TabUser;
import com.fsalliance.core.po.TabUserAlipay;
import com.fsalliance.core.po.TabUserAlipayDAO;
import com.fsalliance.core.po.TabUserDAO;
import com.fsalliance.core.util.CLS_CreateMD5val;
import com.fsalliance.core.util.CLS_FSAlliance_Commen;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.util.InvertCodeGenerator;
import com.fsalliance.core.util.Tools;
import com.fsalliance.core.vo.CLS_VO_IncomeRecord;
import com.fsalliance.core.vo.CLS_VO_PresentRecord;
import com.fsalliance.core.vo.CLS_VO_Result;
import com.fsalliance.core.vo.CLS_VO_User_Order;
import com.fsalliance.core.vo.CLS_VO_User_I;

@Transactional
public class CLS_BO_User {
	
	@Resource(name = "TabUserDAO")
	private TabUserDAO tabUserDAO;
	
	@Resource(name = "TabUserAlipayDAO")
	private TabUserAlipayDAO tabUserAlipayDAO;
	
	@Resource(name = "TabCashRecordDAO")
	private TabCashRecordDAO tabCashRecordDao;
	@Resource(name = "TabIncomeRecordDAO")
	private TabIncomeRecordDAO tabIncomeRecordDAO;
	
	//查询所用户
	public CLS_VO_Result queryUser(){
		CLS_VO_Result result = new CLS_VO_Result();
		List<CLS_VO_User_I> userlist = tabUserDAO.findAll();
		result.setContent(userlist);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}

	//获取用户信息
	public CLS_VO_Result getuserInfo(CLS_VO_User_I user){
		
		CLS_VO_Result result = new CLS_VO_Result();
		TabUser tabUser = tabUserDAO.findById(user.getUserId());
		
		if (tabUser == null) {
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			result.setContent(tabUser);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(tabUser);
		}
		return result;
	}

	/**
	 * 更新支付宝帐号
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updateAlipayNum(CLS_VO_User_I user) {
		CLS_VO_Result result = new CLS_VO_Result();
		int ret = tabUserDAO.updateAlipayNum(user.getUserId(), user.getAlipayNum());
		if(ret > 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
		}
		return result;
	}

	/**
	 * 更新用户名
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updateUserName(CLS_VO_User_I user) {
		CLS_VO_Result result = new CLS_VO_Result();
		int ret = tabUserDAO.updateUserName(user.getUserId(), user.getUserName());
		if(ret > 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
		}
		return result;
	}

	/**
	 * 更新密码
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updatePassword(CLS_VO_User_I user) {
		CLS_VO_Result result = new CLS_VO_Result();
		int ret = tabUserDAO.updatePassword(user.getUserId(), user.getPassword());
		if(ret > 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
		}
		return result;
	}
	/**
	 * 更新用户头像
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updateUserHeadInfo(CLS_VO_User_I user) {
		
		CLS_VO_Result result = new CLS_VO_Result();
		int ret = tabUserDAO.updateUserPhoto(user.getUserId(), user.getUserPic());
		if(ret > 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
		}
		return result;
	}
	
	/**
	 * 更新用户头像
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	public CLS_VO_Result updateUserPhoto(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CLS_VO_Result result = new CLS_VO_Result();
		PrintWriter out = resp.getWriter();
		
        // 创建文件项目工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置文件上传路径
        String upload = req.getSession().getServletContext().getRealPath("photo");
     // 检测是否已经创建
		File dir = new File(upload);
		// 检测/创建数据库的文件夹
		if (dir.exists()) {
		} else {
			dir.mkdir();
		}
        // 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
        String temp = System.getProperty("java.io.tmpdir");
        // 设置缓冲区大小为 5M
        factory.setSizeThreshold(1024 * 1024 * 10);
        // 设置临时文件夹为temp
        factory.setRepository(new File(temp));
        // 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        String file_name = null;
        FileItem file_item = null;
        // 解析结果放在List中
        try {
            List<FileItem> list = servletFileUpload.parseRequest(req);

            for (FileItem item : list) {
            	String name = item.getFieldName();
                InputStream is = null;
				try {
					is = item.getInputStream();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                try {
                	file_name = item.getName();
                    String path = upload+"/"+item.getName();
                    inputStream2File(is, path);
                   
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
             }
            result.setRet(CLS_FSAlliance_Error.ERROR_OK);
        	
            out.write("http://39.107.106.248:3389/FSAlliance/photo/" +file_name);  //这里我把服务端成功后，返回给客户端的是上传成功后路径
        	
        } catch (FileUploadException e) {
            e.printStackTrace();
            out.write("failure");
            result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
        }

        out.flush();
        out.close();
		return result;
	
	}
	
	
	// 流转化成文件
    public static void inputStream2File(InputStream is, String savePath) throws Exception {
        File file = new File(savePath);
        InputStream inputSteam = is;
        BufferedInputStream fis = new BufferedInputStream(inputSteam);
        FileOutputStream fos = new FileOutputStream(file);
        int f;
        while ((f = fis.read()) != -1) {
            fos.write(f);
        }
        fos.flush();
        fos.close();
        fis.close();
        inputSteam.close();
    }
    
	/**
	 * 获取提现记录
	 * @param user
	 * @return
	 */
	public CLS_VO_Result getCashRecordList(String userID, int pageNo, int pageSize) {
		
		CLS_VO_Result result = new CLS_VO_Result();
		List<CLS_VO_PresentRecord> presentRecotds = new ArrayList<CLS_VO_PresentRecord>();
		List<CLS_VO_PresentRecord> presentRecotdlist = tabCashRecordDao.getPresentRecordList(userID, pageNo, pageSize);
		if (presentRecotdlist == null) {
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			result.setContent(presentRecotdlist);
		} else {
			Iterator irIterator = presentRecotdlist.iterator();
			while (irIterator.hasNext()) {
				Object[] objects= (Object[])irIterator.next();
				CLS_VO_PresentRecord clsVOPresentRecord = new CLS_VO_PresentRecord();
				if (objects[0] != null) {
					clsVOPresentRecord.setId(objects[0].toString());
				}
				if (objects[2] != null) {
					clsVOPresentRecord.setState(Integer.parseInt(objects[2].toString()));
				}
				if (objects[3] != null) {
					
					clsVOPresentRecord.setPresentTime(objects[3].toString());
				}
				if (objects[4] != null) {
					clsVOPresentRecord.setPresentMoneny(objects[4].toString());
				}
				presentRecotds.add(clsVOPresentRecord);
			}
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(presentRecotds);
		}
		return result;
	}
	
	
	/**
	 * 获取收入记录
	 * @param user
	 * @return
	 */
	public CLS_VO_Result getIncomeRecordList(String userID, int pageNo, int pageSize) {
		
		CLS_VO_Result result = new CLS_VO_Result();
		List<CLS_VO_IncomeRecord> incomeRecords = new ArrayList<CLS_VO_IncomeRecord>();
		List<CLS_VO_IncomeRecord> incomeRecordsList = tabIncomeRecordDAO.getIncomeRecordList(userID, pageNo, pageSize);
		if (incomeRecordsList == null) {
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			result.setContent(incomeRecordsList);
		} else {
			Iterator irIterator = incomeRecordsList.iterator();
			while (irIterator.hasNext()) {
				Object[] objects= (Object[])irIterator.next();
				CLS_VO_IncomeRecord clsVOIncomeRecord = new CLS_VO_IncomeRecord();
				if (objects[0] != null) {
					clsVOIncomeRecord.setId(objects[0].toString());
				}
				if (objects[2] != null) {
					clsVOIncomeRecord.setType(Integer.parseInt(objects[2].toString()));
				}
				if (objects[3] != null) {
					
					clsVOIncomeRecord.setIncomeTime(objects[3].toString());
				}
				if (objects[4] != null) {
					clsVOIncomeRecord.setIncomeMoneny(objects[4].toString());
				}
				incomeRecords.add(clsVOIncomeRecord);
			}
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(incomeRecords);
		}
		return result;
	}
	
	/**
	 * 提现
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updateBalance(String userId, Double money) {
		
		CLS_VO_Result result = new CLS_VO_Result();
		int ret = tabUserDAO.updateBalance(userId, money);
		if (ret > 0) {
			TabCashRecord tabCashRecord = new TabCashRecord();
			String id = UUID.randomUUID().toString();
			tabCashRecord.setIId(id);
			tabCashRecord.setSUserId(userId);
			Timestamp ts = Timestamp.valueOf(Tools.translateTime(new Date()));  
			tabCashRecord.setDtCashTime(ts);
			tabCashRecord.setDCashBalanceNum(money);
			tabCashRecord.setICashStatus(0);
			tabCashRecordDao.save(tabCashRecord);
			TabUser tabUser = tabUserDAO.findById(userId);
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(tabUser);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
			
		}
		return result;
	}
   
}
