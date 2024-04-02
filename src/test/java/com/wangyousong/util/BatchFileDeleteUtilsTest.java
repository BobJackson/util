package com.wangyousong.util;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class BatchFileDeleteUtilsTest {

    @Test
    public void should_delete_files() {
        boolean deleted = BatchFileDeleteUtils.deleteFilesWhichStartWithDot(new File("D:\\temp\\test"));
        assertTrue(deleted);
    }
}