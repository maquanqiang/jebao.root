package com.jebao.p2p.web.api.responseModel.datavm;

import com.jebao.jebaodb.entity.base.TbBaseRegionInfo;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jack on 2016/12/27.
 */
public class RegionVM extends ViewModel{
    public RegionVM(TbBaseRegionInfo entity){
        this.code=entity.getRiCode();
        this.name=entity.getRiName();
        this.level= entity.getRiLevel() == null?0:entity.getRiLevel();


    }
    public RegionVM(String parentCode,List<TbBaseRegionInfo> regionList){
        this.code = parentCode;
        this.name="China";
        this.children = getChildList(parentCode,regionList);
    }

    /**
     * 构造父子级关系
     */
    private List<RegionVM> getChildList(String parentCode,List<TbBaseRegionInfo> regionList){
        List<RegionVM> childList = new ArrayList<>();
        List<TbBaseRegionInfo> regionChildList = regionList.stream().filter(item -> parentCode.equals(item.getRiParentCode())).collect(Collectors.toList());
        for (TbBaseRegionInfo regionItem:regionChildList) {
            RegionVM itemVM = new RegionVM(regionItem);
            List<RegionVM> itemChildList = getChildList(regionItem.getRiCode(),regionList);
            itemVM.setChildren(itemChildList);
            childList.add(itemVM);
        }
        return childList;
    }

    /**
     * 智能划分 省、市、区县
     */
    public List<RegionVM> smartGroup(){
        for (RegionVM item:this.children) {
            smartFilter(item,this.children);
        }
        return this.children;
    }
    private void smartFilter(RegionVM item,List<RegionVM> siblingList){
        if (item.level == 1){
            for (RegionVM cityOrCountryItem:item.children) {
                smartFilter(cityOrCountryItem,item.children);
            }
            item.children = item.children.stream().filter(o->o.level==2).collect(Collectors.toList());
        }else if (item.level == 2){
            // siblingList 已排序的列表，一个城市下的区县位于 该市的索引+1 到 下一个城市的索引之间
            int countryBeginIndex = 0;
            int countryEndIndex = 0;
            for (int i=0;i<siblingList.size();i++){
                RegionVM loopItem = siblingList.get(i);
                if (loopItem.code.equals(item.code)){
                    countryBeginIndex = i + 1;
                    continue;
                }
                if (loopItem.level == 2 && countryBeginIndex > 0){
                    countryEndIndex = i;
                    break; //寻找到了结束点
                }
            }
            if (countryEndIndex > countryBeginIndex){
                item.children = siblingList.subList(countryBeginIndex,countryEndIndex);
            }else{
                item.children = null;
            }

        }
    }

    private String code;
    private String name;
    private int level;
    private List<RegionVM> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<RegionVM> getChildren() {
        return children;
    }

    public void setChildren(List<RegionVM> children) {
        this.children = children;
    }
}
