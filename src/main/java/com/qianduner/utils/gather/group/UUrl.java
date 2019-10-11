package com.qianduner.utils.gather.group;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;


/**
 * URL工具类
 *
 * @author Laver
 * @date 2009-10-06
 */
public class UUrl {

    /**
     * 字符串url编码
     *
     * @param
     * @return
     */
    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /**
     * Map类型参数URL编码
     *
     * @param map
     * @return
     */
    public static String urlEncode(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String keyString = entry.getKey().toString();
            if (U.isNotEmpty(entry.getValue())) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(String.format("%s=%s", urlEncode(keyString), urlEncode(entry.getValue().toString())));
            }
        }
        return sb.toString();
    }

    public static File downUrlFile(String urlString) {
        URL url = null;
        File file = null;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            url = new URL(urlString);
            InputStream inputStream = url.openStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            inputStream.close();
            byte[] btImg = outStream.toByteArray();
            BufferedOutputStream stream = null;
            try {
                file = File.createTempFile("pattern", "." + "jpg");
                System.out.println("临时文件位置：" + file.getCanonicalPath());
                FileOutputStream fstream = new FileOutputStream(file);
                stream = new BufferedOutputStream(fstream);
                stream.write(btImg);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return file;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String requestURL(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
        urlcon.setDoOutput(true);
        urlcon.setRequestMethod("GET");
        //urlcon.setRequestProperty("Content-Type", "application/json");
        urlcon.setRequestProperty("contentType", "UTF8");
        urlcon.setRequestProperty("Accept-Charset", "UTF8");
        urlcon.connect();         //获取连接
        InputStream is = urlcon.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "utf-8"));
        StringBuffer bs = new StringBuffer();
        String l = null;
        while ((l = buffer.readLine()) != null) {
            bs.append(l);
        }
        System.out.println(bs.toString());
        return bs.toString();
    }

}
