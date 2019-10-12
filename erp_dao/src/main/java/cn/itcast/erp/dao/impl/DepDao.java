package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 部门数据访问类
 */
public class DepDao extends BaseDao<Dep> implements IDepDao{

    public DetachedCriteria getDetachedCriteria(Dep dep1,Dep dep2,Object param){
        DetachedCriteria dc =DetachedCriteria.forClass(Dep.class);
        if(null != dep1){
            //是否输入部门名称
            if(null != dep1.getName() && dep1.getName().trim().length() > 0){
                //MatchMode.ANYWHERE => % %
                //MatchMode.END =>      %name
                //MatchMode.START  =>   name%
                dc.add(Restrictions.like("name", dep1.getName(), MatchMode.ANYWHERE));
            }
            //是否输入部门的电话
            if(null != dep1.getTele() && dep1.getTele().trim().length() > 0){
                dc.add(Restrictions.like("tele", dep1.getTele(), MatchMode.ANYWHERE));
            }
        }
        return dc;
    }

}
