package com.liangjinhai.supercat.sys.mapper;

import com.liangjinhai.supercat.sys.entity.DictionaryData;

import java.util.List;
/**
 * @Author: liangJinHai
 * @Date: 2018/10/8 12:18
 * @Description:
 */
public interface DictionaryDataMapper {

    int create(DictionaryData dictionaryData);

    DictionaryData findOne(Integer id);

    List<DictionaryData> findByType(String type);
}
