package com.guotion.material.service.util;

/**
 * Created by Administrator on 14-10-8.
 */
public class DataBaseUtil {
    //备份windows上的mysql
    public static boolean backupOnWindows(String command) throws Exception {
        //return doCommand(new String[]{"sh","-c",command});
        return doCommand(new String[]{"cmd","/c",command});
    }
    //恢复windows上的mysql
    public static boolean recoverOnWindoes(String command) throws Exception {
        //return doCommand(new String[]{"sh","-c",command});
        return doCommand(new String[]{"cmd","/c",command});

    }

    protected static boolean doCommand(String[] command) throws Exception{
        try{
            Process process = Runtime.getRuntime().exec(command);
            int processResult = process.waitFor();
            if(processResult == 0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
