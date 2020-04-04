package com.javayh.generator.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtil extends StringUtils {



    /**
     * 验证字符串
     *
     * @param url
     * @return
     */
    public static boolean checkWeChatUrl(String url) {
        if (url.length() <= 49) {
            String re1 = "(https)"
                    + "(:)"
                    + "(\\/)"
                    + "(\\/)"    // Any Single Character 3
                    + "(mp)"
                    + "(\\.)"    // Any Single Character 4
                    + "(weixin)"
                    + "(\\.)"
                    + "(qq)"
                    + "(\\.)"
                    + "(com)"
                    + "(\\/)"
                    + "(s)"
                    + "(\\/)"
                    + "((.*?))";
            Pattern p = Pattern.compile(re1, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(url);
            return m.matches();
        } else {
            return false;
        }
    }

    /**
     * 一次性判断多个或单个对象为空。
     *
     * @param objects
     * @return 只要有一个元素为Blank，则返回true
     * @author zhou-baicheng
     */
    public static boolean isBlank(Object... objects) {
        Boolean result = false;
        for (Object object : objects) {
            if (object == null || "".equals(object.toString()) || "null".equals(object.toString().trim())) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 获取随机字符串
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String getRandom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            //输出数字还是字母
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                //区大写还是小写
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }

        }
        return val.toLowerCase();
    }

    /**
     * 一次性判断多个或单个对象不为空。
     *
     * @param objects
     * @return 只要有一个元素不为Blank，则返回true
     * @author zhou-baicheng
     */
    public static boolean isNotBlank(Object... objects) {
        return !isBlank(objects);
    }

    public static boolean isBlank(String... objects) {
        Object[] object = objects;
        return isBlank(object);
    }

    public static boolean isNotBlank(String... objects) {
        Object[] object = objects;
        return isNotBlank(object);
    }

    public static boolean isBlank(String str) {
        Object object = str;
        return isBlank(object);
    }

    public static boolean isNotBlank(String str) {
        Object object = str;
        return isNotBlank(object);
    }

    /**
     * 判断一个字符串在数组中存在几个
     *
     * @param baseStr
     * @param strings
     * @return
     */
    public static int indexOf(String baseStr, String[] strings) {
        if (baseStr == null || baseStr.length() == 0 || strings == null || strings.length == 0) {
            return 0;
        }
        int i = 0;
        for (String string : strings) {
            boolean result = baseStr.equals(string);
            i = result ? ++i : i;
        }

        return i;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;
        strURL = strURL.trim();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                for (int i = 1; i < arrSplit.length; i++) {
                    strAllParam = arrSplit[i];
                }
            }
        }
        return strAllParam;
    }

    /**
     * 判断一个字符串是否为JSONObject,是返回JSONObject,不是返回null
     *
     * @param args
     * @return
     */
    public static JSONObject isJSONObject(String args) {
        JSONObject result = null;
        if (isBlank(args)) {
            return result;
        }
        try {
            return JSONObject.parseObject(args.trim());
        } catch (Exception e) {
            return result;
        }
    }

    /**
     * 判断一个字符串是否为JSONArray,是返回JSONArray,不是返回null
     *
     * @param args
     * @return
     */
    public static JSONArray isJSONArray(Object args) {
        JSONArray result = new JSONArray();
        if (isBlank(args)) {
            return null;
        }
        if (args instanceof JSONArray) {
            JSONArray arr = (JSONArray) args;
            for (Object json : arr) {
                if (json != null && json instanceof JSONObject) {
                    result.add(json);
                } else {
                    result.add(JSONObject.parse(json.toString()));
                }
            }
            return result;
        } else {
            return null;
        }

    }

    public static String trimToEmpty(Object str) {
        return (isBlank(str) ? "" : str.toString().trim());
    }

    /**
     * 将 Strig  进行 BASE64 编码
     *
     * @param str [要编码的字符串]
     * @param bf  [true|false,true:去掉结尾补充的'=',false:不做处理]
     * @return 编码 后的 字符串
     */
    public static String getBASE64(String str, boolean... bf) {
        if (StringUtils.isBlank(str)) return null;
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            byte[] textByte = str.getBytes("UTF-8");
            String base64 = encoder.encodeToString(textByte);
            //去掉 "="
            if (isNotBlank(bf) && bf[0]) {
                base64 = base64.replaceAll("=", "");
            }
            return base64;
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     *
     * @param s [要解码码的字符串]
     * @return 解码后的 字符串
     */

    public static String getStringBASE64(String s) {
        if (isBlank(s)) return "";
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            return new String(decoder.decode(s), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
     *
     * @param map
     * @return
     */
    public static String mapToGet(Map<? extends Object, ? extends Object> map) {
        String result = "";
        if (map == null || map.size() == 0) {
            return null;
        }

        Set<? extends Object> keys = map.keySet();

        for (Object key : keys) {
            result += ("&" + key.toString() + "=" + map.get(key).toString());
        }
        return isBlank(result) ? result : result.substring(1);
    }



    /**
     * 字符串转换成Unicode
     *
     * @param str
     * @return
     */
    public static String toUnicode(String str) {
        String[] as = new String[str.length()];
        String s1 = "";
        for (int i = 0; i < str.length(); i++) {
            int v = str.charAt(i);
            if (v >= 19968 && v <= 171941) {
                as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
                s1 = s1 + "\\u" + as[i];
            } else {
                s1 += s1 + str.charAt(i);
            }
        }
        return s1;
    }

    /**
     * unicode转换字符串
     *
     * @param str
     * @return
     */
    public static String unicodeToString(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] hex = str.split("\\\\u");
        for (int i = 0; i < hex.length; i++) {
            //转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            //追加成String
            stringBuffer.append((char) data);
        }
        return stringBuffer.toString();
    }

    /**
     * 合并数据
     *
     * @param v
     * @return
     */
    public static String merge(Object... v) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < v.length; i++) {
            sb.append(v[i]);
        }
        return sb.toString();
    }

    /**
     * 字符串转urlcode
     *
     * @param value
     * @return
     */
    public static String strToUrlcode(String value) {
        try {
            value = URLEncoder.encode(value, "utf-8");
            return value;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * urlcode转字符串
     *
     * @param value
     * @return
     */
    public static String urlcodeToStr(String value) {
        try {
            value = URLDecoder.decode(value, "utf-8");
            ;
            return value;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断字符串是否包含汉字
     *
     * @param txt
     * @return
     */
    public static Boolean containsCN(String txt) {
        if (isBlank(txt)) {
            return false;
        }
        for (int i = 0; i < txt.length(); i++) {
            String bb = txt.substring(i, i + 1);
            boolean cc = Pattern.matches("[\u4E00-\u9FA5]", bb);
            if (cc) return cc;
        }
        return false;
    }


    /**
     * Unicode文件的时候，会统一把BOM变成“\uFEFF"
     *
     * @param str
     * @return
     */
    public static String BOMTO2Str(String str) {
        return str.replaceAll("\uFEFF", "");
    }

    /**
     * 去掉HTML代码
     *
     * @param news
     * @return
     */
    public static String removeHtml(String news) {
        String s = news.replaceAll("amp;", "").replaceAll("<", "<").replaceAll(">", ">");

        Pattern pattern = Pattern.compile("<(span)?\\sstyle.*?style>|(span)?\\sstyle=.*?>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(s);
        String str = matcher.replaceAll("");

        Pattern pattern2 = Pattern.compile("(<[^>]+>)", Pattern.DOTALL);
        Matcher matcher2 = pattern2.matcher(str);
        String strhttp = matcher2.replaceAll(" ");


        String regEx = "(((http|https|ftp)(\\s)*((\\:)|：))(\\s)*(//|//)(\\s)*)?"
                + "([\\sa-zA-Z0-9(\\.|．)(\\s)*\\-]+((\\:)|(:)[\\sa-zA-Z0-9(\\.|．)&%\\$\\-]+)*@(\\s)*)?"
                + "("
                + "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])"
                + "|([\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*)*[\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*[\\sa-zA-Z]*"
                + ")"
                + "((\\s)*(\\:)|(：)(\\s)*[0-9]+)?"
                + "(/(\\s)*[^/][\\sa-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
        Pattern p1 = Pattern.compile(regEx, Pattern.DOTALL);
        Matcher matchhttp = p1.matcher(strhttp);
        String strnew = matchhttp.replaceAll("").replaceAll("(if[\\s]*\\(|else|elseif[\\s]*\\().*?;", " ");


        Pattern patterncomma = Pattern.compile("(&[^;]+;)", Pattern.DOTALL);
        Matcher matchercomma = patterncomma.matcher(strnew);
        String strout = matchercomma.replaceAll(" ");
        String answer = strout.replaceAll("[\\pP‘’“”]", " ")
                .replaceAll("\r", " ").replaceAll("\n", " ")
                .replaceAll("\\s", " ").replaceAll("　", "");


        return answer;
    }

    /**
     * 把数组的空数据去掉
     *
     * @param array
     * @return
     */
    public static List<String> arrayToEmpty(String[] array) {
        List<String> lsit = Arrays.stream(array).filter(val -> isNotBlank(val)).collect(Collectors.toList());
        return lsit;
    }

    /**
     * 把数组转换成set
     *
     * @param array
     * @return
     */
    public static Set<String> arrayToSet(String[] array) {
        Set<String> set = Arrays.stream(array).filter(val -> val != null).collect(Collectors.toSet());
        return set;
    }

    /**
     * serializable toString
     *
     * @param serializable
     * @return
     */
    public static String serializableToString(Serializable serializable) {
        if (serializable == null) {
            return null;
        }

        try {
            return (String) serializable;
        } catch (Exception e) {
            return serializable.toString();
        }
    }

    /**
     * 将emoji标签转换成utf8字符集保存进数据库
     *
     * @param str 需要转换的字符串
     * @return
     */
    public static String emojiConvert(String str) {
        String patternString = "([\\x{10000}-\\x{10ffff}\\ud800-\\udfff])";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb, "[[" + URLEncoder.encode(matcher.group(1), "UTF-8") + "]]");
            } catch (UnsupportedEncodingException e) {
                return str;
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * @param str 转换后的字符串
     * @return 转换前的字符串
     * @Description 还原utf8数据库中保存的含转换后emoji表情的字符串
     */
    public static String emojiRecovery(String str) {
        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb,
                        URLDecoder.decode(matcher.group(1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母转小写
     *
     * @param str
     * @return
     */
    public static String toFirstUpCase(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
    }

    public static boolean isjson(String string) {
        try {
            com.alibaba.fastjson.JSONObject jsonStr = com.alibaba.fastjson.JSONObject.parseObject(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    /**
     * float
     *
     * @param str1
     * @param str2
     */
    public static float levenshtein(String str1, String str2) {
        //计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        //建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        System.out.println("字符串\"" + str1 + "\"与\"" + str2 + "\"的比较");
        //取数组右下角的值，同样不同位置代表不同字符串的比较
        System.out.println("差异步骤：" + dif[len1][len2]);
        //计算相似度
        float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
        return similarity;
    }

    //得到最小值
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

    /**
     * 检查邮箱是否合法
     */
    public static Boolean checkEmail(String email) {
        if (email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查手机是否合法
     */
    public static Boolean checkPhone(String phone) {
        if (phone.matches("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")) {
            return true;
        } else {
            return false;
        }
    }

}
