package com.priInfo.corbook;

import java.util.List;

import com.bean.corbook.Corbook;


public interface corbookService {
	//通过idcorsem查找corbook类型
  public List<Corbook> findByIdcorsem (String idcorsem);
}
