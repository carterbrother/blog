package org.example.mianshi.filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * 说明：传统的文件copy
 * @author carter
 * 创建时间： 2020年03月26日 9:32 上午
 **/

public class JnioFileCopyApp {

    public static void main(String[] args) {

        final File d = new File("/data/appenvs/ndenv.properties");
        final File s = new File("/data/appenvs/env.properties");

        System.out.println(s.getAbsolutePath() + "source file content :" + s.exists());
        System.out.println(d.getAbsolutePath() +"target file content :" + d.exists());

        System.out.println("source content:");
        try {
            Files.lines(s.toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("do file copy !");

        copy(s, d);

        System.out.println(d.getAbsolutePath() +"target file content :" + d.exists());
        System.out.println("target content:");
        try {
            Files.lines(d.toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void copy(File s, File d) {

        try (
                final FileChannel sourceFileChannel = new FileInputStream(s).getChannel();

                final FileChannel targetFileChannel = new FileOutputStream(d).getChannel()
        ) {

           for (long count= sourceFileChannel.size();count>0;){

               final long transferTo = sourceFileChannel.transferTo(sourceFileChannel.position(), count, targetFileChannel);

               count-=transferTo;

           }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
