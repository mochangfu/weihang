/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: SpecialInfo
 * Author: 25414
 * Date: 2019/5/2 20:26
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.entity;

import com.hg.gama.gamashop.model.Special;
import com.hg.gama.gamashop.model.SpecialOption;

import java.util.List;

public class SpecialInfo  extends Special {

    private List<SpecialOption> specifaOptionList;

    public List<SpecialOption> getSpecifaOptionList() {
        return specifaOptionList;
    }

    public void setSpecifaOptionList(List<SpecialOption> specifaOptionList) {
        this.specifaOptionList = specifaOptionList;
    }
}
