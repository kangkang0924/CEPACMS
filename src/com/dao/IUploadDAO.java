package com.dao;

import java.util.List;

import com.model.Upload;

public interface IUploadDAO {
	//分页
    public List<Upload> queryAll(int startIndex,int pageSize);
    //计算总数
    public int getCount();
    //添加
    public int add(Upload upload);
    //删除
    public int delete(int uploadID);
    //修改
    public int update(Upload upload);
    //编辑
    public Upload queryByKey(int uploadID);
    //模糊查询
    public List<Upload> queryByWords(String mName);
}
