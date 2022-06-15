package org.example.mianshi.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 作者:     carter
 * 创建日期:  2020/3/31 14:24
 * 描述:     TODO
 */

public class CustomerClassLoaderApp extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = loadClassFromFile(name);

        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassFromFile(String name) {

        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(name.replace(".", File.separator) + ".class");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                byteArrayOutputStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buffer = byteArrayOutputStream.toByteArray();


        return buffer;
    }
}
