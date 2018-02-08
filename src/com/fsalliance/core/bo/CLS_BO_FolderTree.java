package com.fsalliance.core.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.fsalliance.core.po.TabFile;
import com.fsalliance.core.po.TabFileDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.util.CLS_FSAlliance_Types;
import com.fsalliance.core.vo.CLS_VO_FolderTree;
import com.fsalliance.core.vo.CLS_VO_Result;
	/** 
	* <p>文件夹管理操作</p> 
	* 
	* @author 03865
	* @version 1.00 2015/07/03 03865 
	* @see TabFileDAO 
	*/ 

@Transactional
public class CLS_BO_FolderTree {
	@Resource(name = "TabFileDAO")
	private TabFileDAO tabFileDAO;
	
	/**
	 *  文件夹内容查询
	 *  
	 *  @return 文件夹树
	 *  @throws null 
	 *  @see CLS_VO_Result 
	 */
	public CLS_VO_Result queryFolderTree(){
		CLS_VO_Result result = new CLS_VO_Result();
		List<TabFile> list = tabFileDAO.findByITypeId(CLS_FSAlliance_Types.FILE_TYPE_FOLAD1);
		List<CLS_VO_FolderTree> fileTrees = new ArrayList<CLS_VO_FolderTree>();
        TreeMap<String, CLS_VO_FolderTree> treeMap =  new TreeMap<String, CLS_VO_FolderTree>();
        for(int i = 0; i < list.size(); ++i){
        	TabFile tabFile = list.get(i);
            CLS_VO_FolderTree obj = new CLS_VO_FolderTree();
            obj.setId(tabFile.getSId());
            obj.setCaption(tabFile.getSFileName());
            obj.setParentId(tabFile.getSParentId());
            obj.setTypeId(tabFile.getITypeId());
            obj.setFilePath(tabFile.getSFilePath());
            obj.setFileType(tabFile.getSFileType());
            fileTrees.add(obj);
        }
         List<CLS_VO_FolderTree> businessChildrenArrayList = new ArrayList<CLS_VO_FolderTree>();
         for (int i = 0; i < fileTrees.size(); i++) {
             treeMap.put(fileTrees.get(i).getId(),fileTrees.get(i));
         }
         Set<String> Set = treeMap.keySet();
         //循环MAP
         for (Iterator iterator = Set.iterator(); iterator.hasNext();) {
             String strReasonID = (String)iterator.next();
             //获得MAP中该键（strReasonID）的bean
             CLS_VO_FolderTree tabBusinessTree = treeMap.get(strReasonID);
             //找到当前对象父节点id
             String strParentID = tabBusinessTree.getParentId();
             //根据父节点ID找到节点的bean
             CLS_VO_FolderTree businessTreeParentBean = treeMap.get(strParentID);
             //判断父节点是否存在
             if (businessTreeParentBean != null) {
                 //把子节点加入到父节点下
                 businessTreeParentBean.getItems().add(tabBusinessTree);
             }
        }
        businessChildrenArrayList.add(treeMap.get("root"));
        System.gc();
        result.setContent(businessChildrenArrayList);
        result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}
	/**
	 *  根据父节点查询子文件
	 *  
	 *  @param String parentId 父节点 
	 *  @return 子文件夹树
	 *  @throws null 
	 *  @see CLS_VO_Result 
	 *  @see CLS_VO_FolderTree 文件 vo层 
	 */
	public CLS_VO_Result queryFolderGrid(String parentId){
		CLS_VO_Result result = new CLS_VO_Result();
		TabFile queryFile = new TabFile();
		queryFile.setSParentId(parentId);
		List<TabFile> list = tabFileDAO.findByExample(queryFile);
		CLS_VO_FolderTree  TabFile=new CLS_VO_FolderTree();
//		for (int i = 0; i < list.size(); i++) {
//			queryFile.setITypeId(list.get(i).getITypeId());
//			
//		}
		result.setContent(list);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}
	/**
	 *  根据子节点查询父节点
	 *  
	 *  @param String parentId 子节点 
	 *  @return 父节点
	 *  @throws null 
	 *  @see CLS_VO_Result 
	 *  @see CLS_VO_FolderTree 文件 vo层 
	 */
	public CLS_VO_Result queryFolderParent(String id){
		CLS_VO_Result result = new CLS_VO_Result();
		TabFile tab =  tabFileDAO.findById(id);
		String parentId = tab.getSParentId();
		//文件夹最底层 
		String endParent = "8888";
		if(endParent.endsWith(parentId)){
			result.setContent(tab);
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
		}else{
			result.setContent(tab);
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		}
		return result;
	}
	/**
	 *  根据ID查询文件
	 *  
	 *  @param String sId 文件ID
	 *  @return 文件
	 *  @throws null 
	 *  @see CLS_VO_Result 
	 */
	public TabFile queryTabFileById(String sId){
		CLS_VO_Result result = new CLS_VO_Result();
		TabFile file = tabFileDAO.findTabFileById(sId);
		return file;
	}
}
