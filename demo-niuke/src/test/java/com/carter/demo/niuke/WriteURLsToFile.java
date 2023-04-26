package com.carter.demo.niuke;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteURLsToFile {
    public static void main(String[] args) throws IOException {
        String url = "https://www.hao123.com";
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("answer"))) {
            for (Element link : links) {
                String href = link.attr("abs:href");
                if (!href.isEmpty()) {
                    System.out.println(href);
                    writer.write(href);
                    writer.newLine(); // 换行
                }
            }
        }
    }
}