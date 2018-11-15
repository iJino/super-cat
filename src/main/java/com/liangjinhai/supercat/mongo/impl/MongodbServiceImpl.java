package com.liangjinhai.supercat.mongo.impl;

import com.liangjinhai.supercat.common.util.ReflectUtil;
import com.liangjinhai.supercat.common.util.StringUtil;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MongodbServiceImpl {

    @Autowired
    protected MongoTemplate mongoTemplate;


    /**
     * @Author: liangJinHai
     * @Date: 2018/10/31 17:00
     * @Description: 单个对象保存
     */
    protected void insert(Object o){
        mongoTemplate.insert(o);
    }
    /**
     * @Author: liangJinHai
     * @Date: 2018/10/31 17:03
     * @Description: 实体集合保存
     */
    protected void insertAll(Collection<Object> objects){
        mongoTemplate.insertAll(objects);
    }
    /**
     * 根据条件删除
     * @param criatira
     * @param entityClass
     * @return 删除的条数
     */
    protected  long remove(Criteria criatira, Class<?> entityClass) {
        DeleteResult result = mongoTemplate.remove(new Query(criatira), entityClass);
        return result.getDeletedCount();
    }
    /**
     * 根据传入的ids 对指定集合进行删除,注解默认为：id
     * @param ids
     * @param entityClass
     * @return 删除的条数
     */
    protected  long remove(List<?> ids, Class<?> entityClass) {
        return remove(ids, entityClass, null);
    }
    /**
     * 根据传入的ids 对指定集合进行删除
     * @param ids
     * @param entityClass
     * @param idName
     * @return 删除的条数
     */
    protected  long remove(List<?> ids,Class<?> entityClass,String idName) {
        if(StringUtils.isBlank(idName)) {
            idName="id";
        }
        Criteria criatira=Criteria.where(idName).in(ids);
        Query query = new Query(criatira);
        DeleteResult result = mongoTemplate.remove(query, entityClass);
        return result.getDeletedCount();
    }
    /**
     * 根据id进行有选择地更新，如果entry属性值为空，则将该值设置为空（当只有一列时，会删除该列）
     * @param id
     * @param map
     * @param entityClass
     */
    protected long updateById(Object id, Map<String, Object> map, Class<?> entityClass) {
        Update update = new Update();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (null == entry.getValue()) {
                update.unset(entry.getKey());
            } else {
                update.set(entry.getKey(), entry.getValue());
            }
        }

        UpdateResult updateMulti = mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(id)), update, entityClass);
        return updateMulti.getModifiedCount();
    }

    /**
     * 指定更新對象的id 的名，根據傳入對象有選擇地更新
     * @param object ：更新的對象
     * @param idName : id名
     */
    protected  long updateEntry(Object object,String idName) {
        Map<String, Object> map = ReflectUtil.entryToMap(object);
        if(StringUtil.isBlank(idName)) {
            idName="id";
        }
        Object idVal = map.get(idName);
        map.remove(idName);
        return updateById(idVal, map, object.getClass());
    }
}
