package com.hudongwx.drawlottery.common.dto.paramBody;

import org.springframework.web.multipart.MultipartFile;

/**
 * Drawlottery.
 * Date: 2017/1/11 0011
 * Time: 19:39
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class TestBody {
    private MultipartFile file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {

        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    private String name;
}
