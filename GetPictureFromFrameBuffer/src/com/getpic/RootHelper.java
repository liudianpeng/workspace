package com.getpic;

import java.io.DataOutputStream;

public class RootHelper {
    /** 
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限) 
     *  
     * @return 应用程序是/否获取Root权限 
     */  
    public static boolean upgradeRootPermission(String pkgCodePath) {  
        Process process = null;  
        DataOutputStream os = null;  
        try {  
            String cmd="chmod 777 " + pkgCodePath;  
            process = Runtime.getRuntime().exec("su"); //切换到root帐号  
            os = new DataOutputStream(process.getOutputStream());  
            os.writeBytes(cmd + "\n");
            os.writeBytes("chmod 777 /dev/graphics/fb0\n");
            os.writeBytes("exit\n");  
            os.flush();  
            process.waitFor();  
        } catch (Exception e) {  
            return false;  
        } finally {  
            try {  
                if (os != null) {  
                    os.close();  
                }  
                process.destroy();  
            } catch (Exception e) {  
            }  
        }  
        return true;  
    }  
}