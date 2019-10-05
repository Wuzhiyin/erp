package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 部门数据访问类
 */
public class DepDao extends HibernateDaoSupport implements IDepDao {
    /**
     * 查询全部列表
     * @return
     */
    public List<Dep> getList() {
        return (List<Dep>) getHibernateTemplate().find("from cn.itcast.erp.entity.Dep");
    }
}
