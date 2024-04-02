package com.wangyousong.util;

import java.io.File;

public class BatchFileDeleteUtils {

    /**
     * 删除文件名以点开头的文件
     * @param file 目录或者文件路径
     * @return 是否成功删除
     */
    public static boolean deleteFilesWhichStartWithDot(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFilesWhichStartWithDot(f);
                }
            }
        }

        if (file.getName().startsWith(".")) {
            return file.delete();
        }

        return true;
    }
}
