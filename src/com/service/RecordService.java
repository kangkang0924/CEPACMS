package com.service;

import com.dao.IRecordDAO;
import com.dao.impl.RecordDAOImpl;
import com.model.Record;
import com.util.PageUtil;

import java.util.List;

public class RecordService {

    private IRecordDAO dao = new RecordDAOImpl();

    //分页
    public List<Record> queryAll(PageUtil pageUtil){
        return dao.queryAll(pageUtil.getStartIndex(),pageUtil.getPageSize());
    }

    //计算总数
    public int getCount(){
        return dao.getCount();
    }

    //添加
    public boolean add(Record record){
        return dao.add(record) > 0;
    }

    //删除
    public boolean delete(int rId){
        return dao.delete(rId) > 0 ;
    }

    //修改
    public boolean update(Record record){
        return dao.update(record) > 0;
    }

    //编辑
    public Record edit(int rId){
        return dao.queryByKey(rId);
    }


    //模糊查询
    public List<Record> queryByKeyWords(String mName){
        return dao.queryByWords(mName);
    }
}
