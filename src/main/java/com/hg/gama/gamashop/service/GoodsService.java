package com.hg.gama.gamashop.service;

import com.hg.gama.gamashop.entity.GoodsClassifyCard;
import com.hg.gama.gamashop.entity.GoodsDetaill;
import com.hg.gama.gamashop.entity.SpecialInfo;
import com.hg.gama.gamashop.jpadao.*;
import com.hg.gama.gamashop.model.*;
import com.hg.gama.gamautil.MathUtilGama;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

//import org.springframework.data.domain.Page;

@Component
public class GoodsService {

    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    GoodsCaseDao goodsCaseDao;
    @Autowired
    TypeService typeService;

    @Autowired
    SpecialOptionRepository specialOptionRepository;

    @Autowired
    SpecialCombinationPriceRepostory specialCombinationPriceRepo;
    @Autowired
    SpecialRepository specialRepository;

    public GoodsCase findGoodsCaseById(Integer id){
        return goodsCaseDao.findById(id).get();
    }
    /*public Integer reduceGoodsCaseAmount(Integer reduceCount,Integer id){
        return goodsCaseDao.reduceAmount(reduceCount,id);
    }*/
    //
    public Goods getGoodsById(Integer id) {
        return goodsRepository.findById(id).get();
    }

    //Example分页
    public Page<Goods> getGoodsPage0(Goods goods,Pageable page) {
        Example<Goods> example =Example.of(goods);
        return goodsRepository.findAll(example,page);
    }
    //首页商品分类
    public List<GoodsClassifyCard> getGoodsClassify(Pageable page) {
        List<Type> typeList= typeService.getAllGoodsType();
        List<GoodsClassifyCard> goodsClassifyCardList=   new ArrayList<>();
        Goods goods= new Goods();
        typeList.stream().forEach(type -> {
            goods.setType(type.getCode());
            List<Goods> goodsList=  goodsRepository.findAllByType(page,type.getId());
            GoodsClassifyCard goodsClassifyCard=new GoodsClassifyCard();
            goodsClassifyCard.setType(type);
            goodsClassifyCard.setGoodsList(goodsList);
            if(goodsList.size()>0) goodsClassifyCardList.add(goodsClassifyCard);
        });
        return goodsClassifyCardList;
    }

    //根据goodsId查询组装完整的商品详情
    public GoodsDetaill getGoodsDetaill(Integer goodsId){
        GoodsDetaill  goodsDetaill =new GoodsDetaill();
        goodsDetaill.setAmountSum(0);
        //商品基本信息
        Goods goods;
        //商品规格信息
        List<SpecialInfo> specialInfoList= new ArrayList<>();
        //商品规格组合价格信息
        Map<String ,Integer> specialPriceMap =new HashMap<>();
        //商品规格组合
        Map<String , GoodsCase> goodsCaseMap =new HashMap<>();
        goods= getGoodsById(goodsId);
        List<Special> specialList =specialRepository.findAllByGoodsId(goodsId);
        Map<Integer ,List<SpecialOption> > noSpecialOptionsMap  =new HashMap<>();
        Map<Integer ,List<Integer> > noSpecialOptionIdsMap  =new HashMap<>();

        for (Special special: specialList) {
            SpecialInfo specialInfo=new SpecialInfo();
            BeanUtils.copyProperties(special,specialInfo);
            List<SpecialOption> specialOptionList =specialOptionRepository.findAllBySpecialId(special.getId());
            //升序排序
            specialOptionList.sort((SpecialOption s1,SpecialOption s2)->s1.getNo().compareTo(s2.getNo()));
            specialInfo.setSpecifaOptionList(specialOptionList);
            specialInfoList.add(specialInfo);
            //按照序號分組
            noSpecialOptionsMap.put(special.getNo(),specialOptionList);
            //获得排列组合原数据
            List<Integer> specialOptionids=specialOptionList.stream().map(SpecialOption::getId).collect(Collectors.toList());
            noSpecialOptionIdsMap.put(special.getNo(),specialOptionids);
        }

        //获得所有排列组合
        Map<String, Set<Integer>>  strSetMap =   MathUtilGama.getCombination(noSpecialOptionIdsMap);
        //升序排序
        specialInfoList.sort((Special s1,Special s2)->s1.getNo().compareTo(s2.getNo()));
        List<GoodsCase> specialCombinations= specialCombinationPriceRepo.findAllByGoodsId(goodsId);

        specialCombinations.stream().forEach(s->{
            List<Integer> list= MathUtilGama.parseSpilteStrToList(s.getSpecialOptionIdList());

            String keyStr= MathUtilGama.getKeyFromMap(strSetMap,new HashSet<>(list));

            if(StringUtils.isEmpty(keyStr)){
                //specialCombinationPriceRepo.deleteById(s.getId());
                return;
            }
            specialPriceMap.put(keyStr,s.getPrice());
            goodsCaseMap.put(keyStr,s);
            goodsDetaill.setAmountSum(goodsDetaill.getAmountSum()+s.getStock());
        });
        goodsDetaill.setGoods(goods);
        goodsDetaill.setSpecialInfoList(specialInfoList);
        goodsDetaill.setSpecialPriceMap(specialPriceMap);
        goodsDetaill.setGoodsCaseMap(goodsCaseMap);
        return goodsDetaill;
    }

}
