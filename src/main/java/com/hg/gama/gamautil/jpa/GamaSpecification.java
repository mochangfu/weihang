/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 25414
 * @create 2019/5/1
 * @since 1.0.0
 */
package com.hg.gama.gamautil.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class GamaSpecification<T>  implements Specification<T> {

    private Map<String ,String> propertyMap =  new HashMap();
    private String dateName;
    private Date beginDate;
    private Date endDate;
    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public GamaSpecification(Map<String ,String> propertyMap, String dateName,Date beginDate,Date endDate){
        this.dateName=dateName;
        this.beginDate=beginDate;
        this.endDate=endDate;
        this.propertyMap =propertyMap;
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<Predicate>();

        propertyMap.forEach((k, v)->{
            if (v!=null) {
                predicates.add(cb.equal(root.get(k).as(String.class), v ));
            }
        });
        if(StringUtils.isEmpty(dateName)){
            if (beginDate!=null) {
                //大于或等于传入时间
                predicates.add(cb.greaterThanOrEqualTo(root.get(dateName).as(Date.class), beginDate));
            }
            if (endDate!=null) {
                //小于或等于传入时间
                predicates.add(cb.lessThanOrEqualTo(root.get(dateName).as(Date.class), endDate));
            }
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
/*

    public List<AdviceEntity> serach(String serach, String stime, String etime) {
        List<AdviceEntity> resultList = null;
        Specification<AdviceEntity> querySpecifi = new Specification<AdviceEntity>() {
            @Override
            public Predicate toPredicate(Root<AdviceEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(stime)) {
                    //大于或等于传入时间
                    predicates.add(cb.greaterThanOrEqualTo(root.get("commitTime").as(String.class), stime));
                }
                if (StringUtils.isNotBlank(etime)) {
                    //小于或等于传入时间
                    predicates.add(cb.lessThanOrEqualTo(root.get("commitTime").as(String.class), etime));
                }
                if (StringUtils.isNotBlank(serach)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + serach + "%"));
                }
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        resultList = this.adviceDao.findAll(querySpecifi);
        return resultList;
    }
*/

}
