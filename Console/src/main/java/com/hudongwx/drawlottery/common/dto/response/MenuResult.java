package com.hudongwx.drawlottery.common.dto.response;

/**
 * 页面信息首页字段.
 * Date: 2017/1/8 0008
 * Time: 18:34
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class MenuResult {
    private String[] menuItems;

    public MenuResult(String[] menuItems) {
        this.menuItems = menuItems;
    }

    public String[] getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(String[] menuItems) {
        this.menuItems = menuItems;
    }
}
