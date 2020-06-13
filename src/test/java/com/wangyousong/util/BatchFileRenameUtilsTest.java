package com.wangyousong.util;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class BatchFileRenameUtilsTest {


    @Test
    public void replaceFilenameWithKeywordsAtHead() {
        String pathname = "D:\\BaiduYunDownload\\test\\1";
        String keywords = "[www.52yzzy.com 吾爱优质资源网] ";
        BatchFileRenameUtils.removeFilenameWithKeywordsAtHead(pathname, keywords);
        try {
            assertEquals(Arrays.stream(Objects.requireNonNull(new File(pathname)
                    .listFiles())).filter(a -> a.getName().contains(keywords))
                    .count(), 0);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                System.out.println("测试的文件夹不存在，或者没有文件！");
            }
        }

    }

    @Test
    public void replaceFilenameWithTextFileContent() throws IOException {
        BatchFileRenameUtils.replaceFilenameWithTextFileContent("D:\\Xunlei Download\\temp", true);
    }


}
