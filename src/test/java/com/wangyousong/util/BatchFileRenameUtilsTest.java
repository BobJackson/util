package com.wangyousong.util;


import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BatchFileRenameUtilsTest {

    private String pathname;
    private String keywords;

    @Before
    public void setUp() {
        pathname = "D:\\BaiduYunDownload\\test\\1";
        keywords = "[www.52yzzy.com 吾爱优质资源网] ";
    }


    @Test
    public void replaceFilenameWithKeywordsAtHead() throws Exception {
        BatchFileRenameUtils.removeFilenameWithKeywordsAtHead(pathname, keywords);
        try {
            assertEquals(Arrays.stream(new File(pathname)
                    .listFiles()).filter(a -> a.getName().contains(keywords))
                    .count(), 0);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                System.out.println("测试的文件夹不存在，或者没有文件！");
            }
        }

    }


}
