package com.springx.bootdubbo.starter.rest.core;

import com.springx.bootdubbo.common.bean.RestContextBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.loader.jar.JarFile;
import org.springframework.context.support.StaticMessageSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.jar.JarEntry;

/**
 * @author <a href="mailto:vakinge@gmail.com">vakin</a>
 * @description 处理国际化语言包
 * @date 2016年8月23日
 */
public class I18nUtils {

    private static Logger logger = LoggerFactory.getLogger(I18nUtils.class);

    private static StaticMessageSource messageSource;

    static {
        messageSource = new StaticMessageSource();
        // 全局的
        loadResourceBaseNames("gloal_i18n/");
        // 项目自身的
        loadResourceBaseNames("i18n/");
    }

    /**
     * @param dirName
     */
    private static void loadResourceBaseNames(String dirName) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(dirName);
        if (resource == null) {
            return;
        }
        if (resource.getProtocol().equals("file")) {
            File i18nBaseFile = new File(resource.getPath());
            if (!i18nBaseFile.isDirectory()) {
                return;
            }
            try {
                getFileProperties(i18nBaseFile);
            } catch (IOException e) {
                logger.error("load i18n properties file error", e);
            }
        } else if (resource.getProtocol().equals("jar")) {
            //file:/Users/lifesense/data/mio/mio-services/target/mio-services-1.0.0-SNAPSHOT.jar
            String jarFilePath = resource.getFile();
            if (jarFilePath.startsWith("file")) {
                jarFilePath = jarFilePath.substring("file:".length());
            }
            JarFile jarFile = getJarFile(jarFilePath);
            if (jarFile == null) {
                return;
            }
            try {
                getJarProperties(jarFile);
            } catch (IOException e) {
                logger.error("load i18n properties jar error", e);
            }
        }
    }

    /**
     * 架包链接
     */
    private static JarFile getJarFile(String path) {
        String[] jars = path.split("!");
        if (jars.length == 0) {
            return null;
        }
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(new File(jars[0]));
            if (jars.length == 1) {
                return jarFile;
            }

            for (int i = 1; i < jars.length; i++) {
                String jarPath = jars[i];
                if (!jarPath.endsWith("jar")) {
                    return jarFile;
                }
                if (jarPath.startsWith("/")) {
                    jarPath = jarPath.substring(1);
                }

                JarEntry entry = jarFile.getJarEntry(jarPath);
                jarFile = jarFile.getNestedJarFile(entry);
            }
        } catch (IOException e) {
            logger.error("load i18n properties error", e);
        }
        return jarFile;
    }

    /**
     * 获得架包内的所有配置文件
     *
     * @param jarFile
     * @throws IOException
     */
    private static void getJarProperties(JarFile jarFile) throws IOException {
        Enumeration<JarEntry> entryEnumeration = jarFile.entries();
        while (entryEnumeration.hasMoreElements()) {
            JarEntry jarEntry = entryEnumeration.nextElement();
            String name = jarEntry.getName();
            if (!name.endsWith(".properties")) {
                continue;
            }
            String fileName = name.substring(name.lastIndexOf("/") + 1,name.lastIndexOf("."));
            String[] files = fileName.split("_");
            if (files.length < 2) {
                continue;
            }
            logger.info("loadJarResource:{},{},{}", name, fileName, files);
            Locale locale = null;
            if (files.length == 2) {
                locale = Locale.forLanguageTag(files[1]);
            } else if (files.length == 3) {
                locale = Locale.forLanguageTag(files[1] + "-" + files[2]);
            }
            if (locale == null) {
                continue;
            }
            InputStream input = null;
            try {
                input = jarFile.getInputStream(jarEntry);
                process(locale, input);
            } finally {
                if (input != null) {
                    input.close();
                }
            }
        }
    }

    /**
     * 获得架包内的所有配置文件
     *
     * @throws IOException
     */
    private static void getFileProperties(File filePath) throws IOException {
        File[] files = filePath.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                getFileProperties(file);
                return;
            }
            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            String[] fileNames = fileName.split("_");
            if (fileNames.length < 2) {
                continue;
            }
            logger.info("loadFileResource:{},{},{}",file.getName(), fileName, fileNames);
            Locale locale = null;
            if (fileNames.length == 2) {
                locale = new Locale(fileNames[1]);
            } else if (fileNames.length == 3) {
                locale = new Locale(fileNames[1], fileNames[2]);
            }
            if (locale == null) {
                continue;
            }
            InputStream input = null;
            try {
                input = new FileInputStream(file);
                process(locale, input);
            } finally {
                if (input != null) {
                    input.close();
                }
            }
        }
    }

    private static void process(Locale locale, InputStream input) throws IOException {
        Properties pro = new Properties();
        pro.load(input);
        pro.forEach((k, v)->{
            // 写入缓存
            messageSource.addMessage(String.valueOf(k), locale, String.valueOf(v));
        });
    }

    public static String getMessage(String code, String defaultMessage) {
        RestContextBean header = RestContextBean.getInstance();
        return getMessage(code, new Object[0], getLocale(header), defaultMessage);
    }

    public static String getMessage(String code, Object[] params, String defaultMessage) {
        RestContextBean header = RestContextBean.getInstance();
        return getMessage(code, params, getLocale(header), defaultMessage);
    }

    public static String getMessage(String code, Locale locale, String defaultMessage) {
        return getMessage(code, new Object[0], locale, defaultMessage);
    }

    public static String getMessage(String code, Object[] params, Locale locale, String defaultMessage) {
        if (messageSource == null) {
            return defaultMessage;
        }
        try {
            String message = messageSource.getMessage(code, params, locale);
            return message;
        } catch (Exception e) {
            return defaultMessage;
        }
    }

    private static Locale defaultLocal() {
        return Locale.ENGLISH;
    }

    private static Locale getLocale(RestContextBean header) {
        String area = header.getArea();
        String language = header.getLanguage();
        if (StringUtils.isEmpty(language)) {
            return defaultLocal();
        }
        // 中国区分地区，其它国家不区分
        Locale locale;
        try {
            if (StringUtils.isEmpty(header.getArea())) {
                if (Locale.SIMPLIFIED_CHINESE.getLanguage().equals(header.getLanguage())) {
                    locale = Locale.SIMPLIFIED_CHINESE;
                } else {
                    locale = new Locale(language);
                }
            } else {
                locale = new Locale(language, area);
            }
        } catch (Exception e) {
            locale = defaultLocal();
        }
        return locale;
    }
}
