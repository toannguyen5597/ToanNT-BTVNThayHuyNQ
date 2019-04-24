package com.Dictionary.ToanNT.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class ListDetail {
    List<UserDetail> listUserDetail = new ArrayList<UserDetail>();
    List<WordDetail> listWordDetail = new ArrayList<WordDetail>();

    public List<UserDetail> getListUserDetail() {
        return listUserDetail;
    }

    public void setListUserDetail(List<UserDetail> listUserDetail) {
        this.listUserDetail = listUserDetail;
    }

    public List<WordDetail> getListWordDetail() {
        return listWordDetail;
    }

    public void setListWordDetail(List<WordDetail> listWordDetail) {
        this.listWordDetail = listWordDetail;
    }
}
