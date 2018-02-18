package com.fsalliance.core.bo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fsalliance.core.po.TabUser;
import com.fsalliance.core.po.TabUserDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.util.InvertCodeGenerator;
import com.fsalliance.core.util.Tools;
import com.fsalliance.core.vo.CLS_VO_Result;
import com.fsalliance.core.vo.CLS_VO_User_I;

@Transactional
public class CLS_BO_Login {
	
	@Resource(name = "TabUserDAO")
	private TabUserDAO tabUserDAO;
	
	/**
	 * 登录
	 * @param user 用户信息
	 * @return
	 */
	public CLS_VO_Result login(CLS_VO_User_I user){
		CLS_VO_Result result = new CLS_VO_Result();
		List<TabUser> list = tabUserDAO.findBySPhoneNum(user.getPhoneNum());
		if (list.size() <= 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_USER_NOT_EXIST);
			return result;
		}
		TabUser tabUserR = null;
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			TabUser tabUser = list.get(i);
			if (tabUser.getSPassword().equals(user.getPassword())) {
				flag = true;
				tabUserR = tabUser;
				break;
			}
		}
		if (flag == true) {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(tabUserR);
		}else{
			result.setRet(CLS_FSAlliance_Error.ERROR_PASSWORD);
		}
		return result;
	}
	//查询所用户
	public CLS_VO_Result queryUser(){
		CLS_VO_Result result = new CLS_VO_Result();
		List<CLS_VO_User_I> userlist = tabUserDAO.findAll();
		result.setContent(userlist);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}
	
	//注册新用户
	public CLS_VO_Result registerUser(CLS_VO_User_I user){
		CLS_VO_Result result = new CLS_VO_Result();
		List<TabUser> list = tabUserDAO.findBySPhoneNum(user.getPhoneNum());
		TabUser tabUser = new TabUser();
		if (list.size() <= 0) {
			String id = UUID.randomUUID().toString();
			tabUser.setSUserId(id);
			tabUser.setSPhoneNum(user.getPhoneNum());
			tabUser.setSPassword(user.getPassword());
			
			tabUser.setSName(user.getPhoneNum());
			String shareCode = InvertCodeGenerator.getStringRandom(6);
			List<TabUser> list_user = tabUserDAO.findBySInviteNum(user.getInviteNum());
			tabUser.setSInviteNum(shareCode);
			if (list_user.size() > 0) {
				tabUser.setSParentId(list_user.get(0).getSUserId());
			} else {
				tabUser.setSParentId("aaaaaaa");
			}
		
			 Timestamp ts = Timestamp.valueOf(Tools.translateTime(new Date()));  
			
			tabUser.setDtCreateTime(ts);
			tabUser.setDtLoginTime(ts);
			tabUserDAO.save(tabUser);
			//result.setRet(ret);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_USERID_EXIST);
			return result;
		}
		
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent(tabUser);
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
	 * 更新用户的登录时间
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updateLoginTime(CLS_VO_User_I user) {
		CLS_VO_Result result = new CLS_VO_Result();
		TabUser tabUser = tabUserDAO.findById(user.getUserId());
		if (tabUser == null) {
			result.setRet(CLS_FSAlliance_Error.ERROR_USER_NOT_EXIST);
		} else {
			tabUser.setDtLoginTime(user.getLoginTime());
			TabUser mergeUser = tabUserDAO.merge(tabUser);
			if(mergeUser == null) {
				result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
			} else {
				result.setRet(CLS_FSAlliance_Error.ERROR_OK);
				result.setContent(mergeUser);
			}
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
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa"+ret);
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
        factory.setSizeThreshold(1024 * 1024 * 5);
        // 设置临时文件夹为temp
        factory.setRepository(new File(temp));
        // 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        String path = null;
        // 解析结果放在List中
        try {
            List<FileItem> list = servletFileUpload.parseRequest(req);

            for (FileItem item : list) {
                String name = item.getFieldName();
                InputStream is = null;
				try {
					is = item.getInputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                try {
                    path = upload+"\\"+item.getName();
                    inputStream2File(is, path);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
             }
            result.setRet(CLS_FSAlliance_Error.ERROR_OK);
        	result.setContent(path);
            out.write(path);  //这里我把服务端成功后，返回给客户端的是上传成功后路径
        	
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
  

}
