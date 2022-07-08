package com.service;

import java.util.List;

import com.dao.IUploadDAO;
import com.dao.impl.UploadDAOImpl;
import com.model.Upload;
import com.util.PageUtil;

public class UploadService {
	private IUploadDAO dao = new UploadDAOImpl();

    //分页
    public List<Upload> queryAll(PageUtil pageUtil){
        return dao.queryAll(pageUtil.getStartIndex(),pageUtil.getPageSize());
    }

    //计算总数
    public int getCount(){
        return dao.getCount();
    }

    //添加
    public boolean add(Upload upload){
        return dao.add(upload) > 0;
    }

    //删除
    public boolean delete(int uploadID){
        return dao.delete(uploadID) > 0 ;
    }

    //修改
    public boolean update(Upload upload){
        return dao.update(upload) > 0;
    }

    //编辑
    public Upload edit(int uploadID){
        return dao.queryByKey(uploadID);
    }


    //模糊查询
    public List<Upload> queryByKeyWords(String mName){
        return dao.queryByWords(mName);
    }
}
