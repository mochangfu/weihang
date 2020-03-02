/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: MathUtilGama
 * Author: 25414
 * Date: 2019/5/2 22:25
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamautil;
import com.alibaba.druid.support.json.JSONUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class MathUtilGama {

    public static  List<Integer> parseSpilteStrToList(String spilteStr){
        try{
            String jsonIntStr="["+spilteStr+"]";
            List<Integer> intList =   (List<Integer>) JSONUtils.parse(jsonIntStr);
            if(intList==null||intList.size()==0)return null;
            //Collections.sort(intList);
            return intList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static String parseIntListToSplitStrAsc(List<Integer> intList) {
        try {
            if(intList==null||intList.size()==0)return null;
            //Collections.sort(intList);
            return StringUtils.join(intList.toArray(), ",");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //
    public static String getKeyStringAsc(List<Integer> intList) {
        //Collections.sort(intList);
        if(intList==null||intList.size()==0){ return null; }
        StringBuffer keyString=new StringBuffer("");
        intList.stream().forEach(s->{
            keyString.append(s+"_");
        });
        return keyString.toString();
    }
    /*
    根据key大小做有序排列组合
     */
    public  static Map<String, Set<Integer>> getCombination(Map< Integer,List<Integer>>  intListMap){
        List<Integer> keyList=new ArrayList(intListMap.keySet());
        Collections.sort(keyList);

        Map<String, Set<Integer>> strSetMap=new HashMap<>();
        if(intListMap.size()>0){
            for(Integer no:keyList){
                List<Integer> digits=intListMap.get(no);
                if(digits==null||digits.size()==0)continue;
                if(strSetMap.size()==0){
                    for (Integer dig:digits) {
                        Set<Integer>  set= new HashSet();
                        set.add(dig);
                        strSetMap.put(dig+"_",set);
                    }
                }else {
                    Map<String, Set<Integer>> tempSetMap=strSetMap;
                    Map<String, Set<Integer>> newSetMap=new HashMap<>();
                    for (Integer dig:digits) {
                        strSetMap.forEach((k,v)->{
                            Set<Integer>  digSet=new HashSet(v);
                            digSet.add(dig);
                            newSetMap.put(k+""+dig+"_",digSet);
                        });
                    }
                    strSetMap=newSetMap;
                }
            }
        }
        return  strSetMap;
    }
    //获得规格-价格对照map
    public  static String getKeyFromMap(Map<String, Set<Integer>>  strSetMap, Set<Integer> idSet){
        final StringBuffer keyStr=new StringBuffer("");
        strSetMap.forEach((k,v)->{
            if(v.containsAll(idSet)){
                keyStr.append(k);
            }
        });
    return StringUtils.isEmpty(keyStr.toString())?null:keyStr.toString();
    }
}
