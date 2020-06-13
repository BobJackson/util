package com.wangyousong.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Bob
 */
public class BatchFileRenameUtils {
    private static final Logger logger = Logger.getLogger(BatchFileRenameUtils.class.getName());

    /**
     * <p>
     * 在批量修改文件名中，存在这样一种需求：很多文件的文件名前面有一段公共的文本信息，比如 xxx网站xxx资源 + 文件的真实有效名称，
     * 我们需要的只是文件的真实有效名称，需要批量重命名文件，下面的方法就是解决这个需求的。
     * </p>
     * <p>
     * 去掉文件名前段部分的关键字,例如keywords关键字=XXX网站提供下载,截取之前的文件名是：</br>
     * XXX网站提供下载-01Redis入门.mp4,<br></br>
     * XXX网站提供下载-02Redis基本数据结构.mp4，<br></br>
     * XXX网站提供下载-03Redis分布式锁实现.mp4<br></br>
     * 去掉之后就是：<br></br>
     * 01Redis入门.mp4,<br></br>
     * 02Redis基本数据结构.mp4，<br></br>
     * 03Redis分布式锁实现.mp4<br></br>
     * </p>
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
                .forEach(f -> {
                    if (f.renameTo(new File(buildNewFileName(keywords, dir, f)))) {
                        logger.info(f.getAbsolutePath() + "\t 文件名修改成功!");
                    }
                });

    }

    private static String buildNewFileName(String keywords, File dir, File f) {
        return dir + File.separator + f.getName().substring(keywords.length());
    }

    /**
     * <p>
     * 批量修改指定目录下的文件夹名称，文件夹名称存放在该目录下的文本文件中。
     * </p>
     * <p>
     * 例如修改前文件夹结构
     * <pre>
     *     -1
     *      -保存文件名的文本文件1.txt (内容是武则天传)
     *     -2
     *      -保存文件名的文本文件2.txt (内容是狄仁杰传)
     *     -3
     *      -保存文件名的文本文件3.txt (内容是康熙传)
     * </pre>
     * 其中需要修改的文件夹1，2，3是同一层级，其目录下存放的一个文本文件，文本内容是各自的需要修改的文件名。
     * </p>
     *
     * @param path           需要修改文件夹的父目录
     * @param deleteTextFile 是否删除文本文件
     * @throws IOException IO异常
     */
    public static void replaceFilenameWithTextFileContent(String path, boolean deleteTextFile) throws IOException {
        File baseDir = new File(path);
        for (File file : Objects.requireNonNull(baseDir.listFiles())) {
            File[] subFiles = file.listFiles();

            assert subFiles != null;
            List<File> txtFiles = Arrays.stream(subFiles)
                    .filter(t -> t.getName().contains(".txt"))
                    .collect(Collectors.toList());

            String filename = FileUtils.readFileToString(txtFiles.get(0), "UTF-8");

            String grandFatherPath = file.getParentFile().getAbsolutePath();
            boolean renameResult = file.renameTo(new File(grandFatherPath + File.separator + filename));
            if (renameResult) {
                logger.info(file.getAbsolutePath() + "\t 文件名修改成功!");
                if (deleteTextFile) {
                    txtFiles.get(0).deleteOnExit();
                }
            }
        }
    }


}