package com.jebao.p2p.web.api.responseModel.tempTest;

import com.jebao.jebaodb.entity.TbTempTest;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Dto的概念
 * Created by Administrator on 2016/10/21.
 */
public class TempTestData extends ViewModel {

    //转为数据库实体数据转为响应数据
    public static TempTestData toViewData(TbTempTest entity)
    {
        TempTestData result=new TempTestData();
        result.setId(entity.getId());
        result.setName(entity.getUsername());
        return result;
    }
    public static List<TempTestData> toViewDataList(List<TbTempTest> entityList)
    {
        if(entityList==null)return null;
        List<TempTestData> result=new ArrayList<TempTestData>();
        for (TbTempTest entity:entityList)
        {
            TempTestData item=toViewData(entity);
            result.add(item);
        }
        return result;
    }
    //===================================================
    private Integer id;

    private String Name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
