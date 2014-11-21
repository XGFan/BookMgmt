package com.priInfo.corbook;

import java.util.List;

import com.bean.corbook.Corbook;
import com.bean.corbook.CorbookDAO;

public class corbookServiceImpl implements corbookService {
    CorbookDAO corbkdao;

    public CorbookDAO getCorbkdao() {
        return corbkdao;
    }

    public void setCorbkdao(CorbookDAO corbkdao) {
        this.corbkdao = corbkdao;
    }

    public List<Corbook> findByIdcorsem(String idcorsem) {
        List<Corbook> list = corbkdao.findByIdcorsem(idcorsem);
        return list;
    }
}
