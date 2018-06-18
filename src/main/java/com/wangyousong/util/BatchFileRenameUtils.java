package com.wangyousong.util;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

public class BatchFileRenameUtils {
    private static final Logger logger = Logger.getLogger(BatchFileRenameUtils.class.getName());

    /**
     * <p>
     * 在批量修改文件名中，存在这样一种需求：很多文件的文件名前面有一段公共的文本信息，比如 xxx网站xxx资源 + 文件的真实有效名称，</br>
     * 我们需要的只是文件的真实有效名称，需要批量重命名文件，下面的方法就是解决这个需求的。
     * </p>
     * <p>
     * 去掉文件名前段部分的关键字
     *
     * @param pathname 文件夹路径
     * @param keywords 关键字
     */
    public static void removeFilenameWithKeywordsAtHead(String pathname, String keywords) {
        File dir = new File(pathname);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            logger.warning(pathname + " 文件夹下没有文件!");
            return;
        }
        Arrays.stream(files)
                .filter(f -> f.getName().contains(keywords))
                .forEach(f -> f.renameTo(new File(buildNewFileName(keywords, dir, f))));

    }

    private static String buildNewFileName(String keywords, File dir, File f) {
        return dir + File.separator + f.getName().substring(keywords.length());
    }
}