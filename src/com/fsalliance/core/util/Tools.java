package com.fsalliance.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
/**
 * 
 * 类说明：工具类 
 *
 * @作者及日期：liuhengheng    2014-8-13
 * @修改人及日期：liuhengheng    2014-8-13
 * @修改描述：
 * @当前版本 ：1.0
 */
public class Tools {
    /**
     * 
     * 方法说明：判断一个对象是否为空 
     *
     * @param obj
     * @return 
     * @作者及日期：liuhengheng    2014-8-13
     * @修改人及日期：liuhengheng    2014-8-13
     * @修改描述：
     * @其他：
     */
    public static boolean isNull(Object obj){
        return obj == null;
    }
    /**
     * 
     * 方法说明：判断字符串是否为空 
     *
     * @param str
     * @return 
     * @作者及日期：liuhengheng    2014-8-13
     * @修改人及日期：liuhengheng    2014-8-13
     * @修改描述：
     * @其他：
     */
    public static boolean isNull(String str){
        return str == null || "".equals(str);
    }
    /**
     * 
     * 方法说明：判断列表是否为空 
     *
     * @param col
     * @return 
     * @作者及日期：liuhengheng    2014-8-13
     * @修改人及日期：liuhengheng    2014-8-13
     * @修改描述：
     * @其他：
     */
    public static boolean isNull(Collection col){
        return col == null || col.isEmpty();
    }
    
}
