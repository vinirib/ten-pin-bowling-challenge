package com.jobsity.util;

public class ResourcesFileReaderUtil {

    public static String getFile(String resourcePath) {
        return Thread.currentThread().getContextClassLoader()
                .getResource(resourcePath).getFile();
    }
}
