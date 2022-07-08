package com.service;

import java.util.List;

import com.dao.IDownloadDAO;
import com.dao.impl.DownloadDAOImpl;
import com.model.Download;
import com.util.PageUtil;

public class DownloadService {
	private IDownloadDAO dao = new DownloadDAOImpl();

    //分页
    public List<Download> queryAll(PageUtil pageUtil){
        return dao.queryAll(pageUtil.getStartIndex(),pageUtil.getPageSize());
    }

    //计算总数
    public int getCount(){
        return dao.getCount();
    }

    //添加
    public boolean add(Download download){
        return dao.add(download) > 0;
    }

    //删除
    public boolean delete(int downloadID){
        return dao.delete(downloadID) > 0 ;
    }

    //修改
    public boolean update(Download download){
        return dao.update(download) > 0;
    }

    //编辑
    public Download edit(int downloadID){
        return dao.queryByKey(downloadID);
    }


    //模糊查询
    public List<Download> queryByKeyWords(String mName){
        return dao.queryByWords(mName);
    }
}
