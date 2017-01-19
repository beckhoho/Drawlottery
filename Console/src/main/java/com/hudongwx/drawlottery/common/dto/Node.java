package com.hudongwx.drawlottery.common.dto;

/**
 * 一个简单的 key-value 单位.
 * Date: 2017/1/19 0019
 * Time: 17:39
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class Node {
    private String key;

    public Node(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
