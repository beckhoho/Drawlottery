package com.hudongwx.drawlottery.common.dto;

import java.util.UUID;

/**
 * 一个简单的 text-value 单位.
 * Date: 2017/1/19 0019
 * Time: 17:39
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class Node {
    private String text;
    private Object value;
    private String key;

    public Node(String text, Object value) {
        this.text = text;
        this.value = value;
        key = UUID.randomUUID().toString();
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
