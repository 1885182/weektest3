package com.bawei.yk.bean;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/4 9:05
 * @Description:
 */
public class DataBean {
    private List<ListBean> list;
    private String sellerName;
    private boolean select;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
