package com.hudongwx.drawlottery.common.constants;

import com.hudongwx.drawlottery.common.dto.Node;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用常量类.
 * Date: 2017/1/5 0005
 * Time: 11:12
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Component
@ConfigurationProperties(locations = {"classpath:project/constants.properties"})
public class CommonConstants {

    /**
     * 显示/隐藏.
     */
    private static final String SHOW = "1";
    private static final String HIDE = "0";

    /**
     * 是/否.
     */
    private static final String YES = "1";
    private static final String NO = "0";


    /**
     * 对/错.
     */
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    /**
     * 可用/不可用.
     */
    private static final int VALID = 1;
    private static final int INVALID = 0;

    /**
     * 常量字段 空字符串 .
     */
    private static final String EMPTY_STRING = "";
    /**
     * 默认分页行数
     */
    private int maxPageSize;

    public String getEmptyString() {
        return EMPTY_STRING;
    }

    public int getMaxPageSize() {
        return maxPageSize;
    }

    public void setMaxPageSize(int maxPageSize) {
        this.maxPageSize = maxPageSize;
    }

    public String getSHOW() {
        return SHOW;
    }

    public String getHIDE() {
        return HIDE;
    }

    public String getYES() {
        return YES;
    }

    public String getNO() {
        return NO;
    }

    public static String getTRUE() {
        return TRUE;
    }

    public String getFALSE() {
        return FALSE;
    }

    public int getVALID() {
        return VALID;
    }

    public int getINVALID() {
        return INVALID;
    }


    public Integer convertGenre(String genreLabel) {
        Integer genre = null;
        switch (genreLabel) {
            case "虚拟":
                genre = 0;
                break;
            case "实体":
                genre = 1;
                break;
            case "实体不可快递":
                genre = 2;
                break;
        }
        return genre;
    }

    public String convertGenre(Integer genre) {
        if (null == genre)
            return getEmptyString();
        String genreLabel = null;
        switch (genre) {
            case 0:
                genreLabel = "虚拟";
                break;
            case 1:
                genreLabel = "实体";
                break;
            case 2:
                genreLabel = "实体不可快递";
                break;
        }
        return genreLabel;
    }

    public List<Node> createGenres() {
        List<Node> list = new ArrayList<>();
        list.add(new Node("虚拟", 0));
        list.add(new Node("实体", 1));
        list.add(new Node("实体不可快递", 2));
        return list;
    }

    public List<Node> createExchangeState() {
        List<Node> list = new ArrayList<>();
        list.add(new Node("未兑奖", 0));
        list.add(new Node("已兑奖", 1));
        return list;
    }

    public List<Node> createCardNotEnough() {
        List<Node> list = new ArrayList<>();
        list.add(new Node("无", 0));
        list.add(new Node("充值卡不足", 1));
        return list;
    }
}
