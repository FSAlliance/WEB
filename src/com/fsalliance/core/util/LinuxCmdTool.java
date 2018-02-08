package com.fsalliance.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LinuxCmdTool extends Thread {
	
	private String cmdStr;
	
	public LinuxCmdTool(String cmdStr){
		this.cmdStr = cmdStr;
	}
    /**
     * 
     * 方法说明：执行linux命令，不带输出 
     *
     * @param cmdStr 要执行的linux命令
     * @return       执行命令的进程
     * @throws IOException 
     * @作者及日期：liuhengheng    2014-4-8
     * @修改人及日期：liuhengheng    2014-4-8
     * @修改描述：
     * @其他：
     */
    public static Process doLinuxCmdNoOut(String cmdStr) throws IOException {
        ArrayList<String> cmds = new ArrayList<String>();
        cmds.add("bash");
        cmds.add("-c");
        cmds.add(cmdStr);
        ProcessBuilder pb = new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process exec = pb.start();
        return exec;
    }
    /**
     * 
     * 方法说明：执行cmd命令，带输出。ps：如果开启一个进程，可能很久无法返回
     *
     * @param cmdStr
     * @return
     * @throws IOException 
     * @作者及日期：liuhengheng    2014-8-15
     * @修改人及日期：liuhengheng    2014-8-15
     * @修改描述：
     * @其他：
     */
    public static void doLinuxCmd(String cmdStr) {
    	
    	LinuxCmdTool linuxCmdTool = new LinuxCmdTool(cmdStr);
    	linuxCmdTool.start();
        
        //return "";
    }
    
    @Override
	public void run() {
		super.run();
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {

			Process exec = doLinuxCmdNoOut(this.cmdStr);
	        inputStream = (InputStream) exec.getInputStream();
	        inputStreamReader = new InputStreamReader(inputStream);
	        bufferedReader = new BufferedReader(inputStreamReader);
	        StringBuilder builder = new StringBuilder();
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {//控制台输出
                builder.append(temp);
                //System.out.println(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	if(bufferedReader != null){
            		bufferedReader.close();
            	}
            	if(inputStreamReader != null){
            		inputStreamReader.close();
            	}
            	if(inputStream != null){
            		inputStream.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}
