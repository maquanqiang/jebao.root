package com.jebao.p2p.web.utils.toolbox;

import com.jebao.p2p.web.utils.constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/11/9.
 * 为静态文件增加版本号--目前版本号使用的时文件的MD5的值
 */
public class UrlHelperTool {
    private static final Map<String, String> urlMap = new ConcurrentHashMap<String, String>();

    public String href(String hrefKey) {
        String hrefVal = urlMap.get(hrefKey);
        if (hrefVal != null) return hrefVal;
        hrefVal = urlMap.get(hrefKey.toLowerCase());
        if (hrefVal != null) return hrefVal;
        String file = "/static" + hrefKey;//static是静态文件的根目录
        //
        InputStream is = getInputStream(file);
        if (is == null) {
            hrefVal = hrefKey + "?throwException-NoFoundFile";
            urlMap.put(hrefKey, hrefVal);
            return "\"" + hrefVal + "\"";
        }
        try {
            String md5 = getMD5(is);
            hrefVal = hrefKey + "?" + md5;
            urlMap.put(hrefKey, hrefVal);
            return "\"" + hrefVal + "\"";
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private InputStream getInputStream(String fileName) {
        return getClass().getResourceAsStream(fileName);
    }

    private String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {
        StringBuffer md5 = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] dataBytes = new byte[1024];
        int nread = 0;
        while ((nread = is.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }
        ;
        byte[] mdbytes = md.digest();
        // convert the byte to hex format
        for (int i = 0; i < mdbytes.length; i++) {
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return md5.toString();
    }

    public String getApiOrigin() {
        String apiOrgin = Constants.WebApiOrgin;
        if (apiOrgin.charAt(apiOrgin.length() - 1) == '/'){
            apiOrgin = apiOrgin.substring(0,apiOrgin.length() - 1);
        }
        return apiOrgin;
    }

    public String getH5Origin() {
        String h5Orgin = Constants.H5_ORGIN;
        if (h5Orgin.charAt(h5Orgin.length() - 1) == '/'){
            h5Orgin = h5Orgin.substring(0,h5Orgin.length() - 1);
        }
        return h5Orgin;
    }

    /**
     * 手续费（提现）
     *
     * @return
     */
    public String getFee() {
        return Constants.COMMISSION_CHARGE;
    }

    /**
     * 静态资源文件的路径
     * @return
     */
    public String getStaticFilePath() {
        return Constants.STATIC_FILE_PATH;
    }
}
