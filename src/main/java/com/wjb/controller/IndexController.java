package com.wjb.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/16.
 */
@Controller
public class IndexController {


    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("my")
    public String my() {
        return "pages/my";
    }

    @GetMapping("hacker")
    public String hacker() {
        return "pages/hacker";
    }

    /**
     * 10秒钟爬一张图片
     *
     * @Author:
     * @params:
     * @Date:14:01 2017/11/17
     */
    @ResponseBody
    @GetMapping("image")
    public List<String> getImage() throws IOException {
        int index = 0;
        int number = 1;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String urlPath = "http://588ku.com/beijing/0-34-pxnum-0-8-0-0-0-" + (++index);
            Document doc = Jsoup.connect(urlPath).timeout(10000).get();
            Elements elements = doc.select("div.pic-box >a  >img[data-original]");
            for (Element e : elements) {
                String src = e.attr("data-original");
                System.out.println(src);
                list.add(src);
            }

        }


//            try {
//                doc = Jsoup.connect(urlPath).timeout(10000).get();
//                // 获取后缀为jpg的图片的元素集合
//                Elements jpg = doc.select("img[src$=.jpg]");
//
//
//
//                // 遍历元素
//                for (Element e : pngs) {
//                    String src = e.attr("src");// 获取img中的src路径
//                    // 获取后缀名
//                    String imageName = src.substring(src.lastIndexOf("/") + 1,
//                            src.length());
//                    // 连接url
//                    URL url;
//                    try {
//                        url = new URL(src);
//                        URLConnection uri = url.openConnection();
//                        // 获取数据流
//                        InputStream is = uri.getInputStream();
//                        // 写入数据流
//                        OutputStream os = new FileOutputStream(new File(
//                                "F:/imgs", imageName));
//
//                        byte[] buf = new byte[3072];
//                        int i = 0;
//                        while ((i = is.read()) != -1) {
//                            os.write(i);
//                        }
//　　　　　　　　　　　　　　　　os.close();
//                    } catch (MalformedURLException e1) {
//                        e1.printStackTrace();
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//
//                }
//
//            } catch (IOException e2) {
//                e2.printStackTrace();
//            }
        return list;
    }


}
