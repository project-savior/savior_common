package com.jerry.savior_common.constants;

/**
 * @author 22454
 */
public interface PatternConstants {
    /**
     * base64图片前缀
     */
    String BASE64_IMAGE_PREFIX_REGEXP = "^data:image/\\w+;base64,";
    /**
     * 手机号
     */
    String TELEPHONE_REGEXP = "(13\\d|14[579]|15[^4\\D]|17[^49\\D]|18\\d)\\d{8}";
    /**
     * 邮箱
     */
    String EMAIL_REGEXP = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    /**
     * IPV4
     */
    String IP_V4_REGEXP = "\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}";
    /**
     * 中文
     */
    String CHINESE_REGEXP = "[\\u4e00-\\u9fa5]+";
    /**
     * 双字节字符（包含汉字）
     */
    String DOUBLE_BYTE_CHAR_REGEXP = "[^\\x00-\\xff]+";
    /**
     * 时间（时:分:秒）
     */
    String TIME_REGEXP = "([01]?\\d|2[0-3]):[0-5]?\\d:[0-5]?\\d";
    /**
     * 身份证
     */
    String ID_CARD_REGEXP = "\\d{17}[0-9Xx]|\\d{15}";
    /**
     * 日期（年-月-日）
     */
    String DATE_REGEXP = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    /**
     * 正整数
     */
    String POSITIVE_INTEGER_REGEXP = "[1-9]\\d*";
    /**
     * 负整数
     */
    String NEGATIVE_INTEGER_REGEXP = "-[1-9]\\d*";
    /**
     * 空白行
     */
    String SPACE_LINE_REGEXP = "\\s";
    /**
     * QQ
     */
    String QQ_REGEXP = "[1-9]([0-9]{5,11})";
    /**
     * 邮政编码
     */
    String POSTAL_CODE_REGEXP = "\\d{6}";
    /**
     * 纯数字
     */
    String PURE_NUMBER_REGEXP = "^[0-9]";
    /**
     * 纯英文字母（不区分大小写）
     */
    String PURE_ENGLISH_CHAR_REGEXP = "^[A-Za-z]+";
    /**
     * 纯大写字母
     */
    String PURE_ENGLISH_UPPERCASE_CHAR_REGEXP = "^[A-Z]+";
    /**
     * 纯小写字母
     */
    String PURE_ENGLISH_LOWERCASE_CHAR_REGEXP = "^[a-z]+";
    /**
     * 数字+字母
     */
    String NUMBER_AND_ENGLISH_CHAR_REGEXP = "^[A-Za-z0-9]+";
    /**
     * 数字+字母+下划线
     */
    String NUMBER_AND_ENGLISH_CHAR_AND_UNDERLINE_REGEXP = "^\\w+";

    /**
     * 密码（字母开头，长度6~18，包含字符、数字、下划线）
     */
    String PASSWORD_REGEXP = "^[a-zA-Z]\\w{5,17}";
}
