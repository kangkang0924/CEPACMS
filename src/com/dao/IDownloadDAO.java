package com.dao;

import java.util.List;

import com.model.Download;

public interface IDownloadDAO {
	//分页
    public List<Download> queryAll(int startIndex,int pageSize);
    //计算总数
    public int getCount();
    //添加
    public int add(Download download);
    //删除
    public int delete(int downloadID);
    //修改
    public int update(Download download);
    //编辑
    public Download queryByKey(int downloadID);
    //模糊查询
    public List<Download> queryByWords(String mName);
}
