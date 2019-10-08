package cn.itcast.erp.action;
import cn.itcast.erp.biz.*;
import cn.itcast.erp.entity.Dep;
import com.alibaba.fastjson.JSON;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门Action
 */
public class DepAction {
    private IDepBiz depBiz;

    public void setDepBiz(IDepBiz depBiz) {
        this.depBiz = depBiz;
    }

    /**
     * 属性驱动:条件查询
     */
    private Dep dep1;

    public Dep getDep1() {
        return dep1;
    }

    public void setDep1(Dep dep1) {
        this.dep1 = dep1;
    }
    private int page;//页码
    private int rows;//每页的记录数

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    /**
     * 条件查询
     */
    public void getList(){
        System.out.println("页码：" + page + " 记录数:" + rows);
        int firstResult = (page -1) * rows;
        List<Dep> list = depBiz.getList(dep1,firstResult, rows);
        long total = depBiz.getCount(dep1);
        //{total: total, rows:[]}
        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("total", total);
        mapData.put("rows", list);
        //把部门列表转JSON字符串
        String listString = JSON.toJSONString(mapData);
        write(listString);
    }

    /**
     * 查询所有部门
     */
    public void list(){
        List<Dep> list = depBiz.getList();
        String jsonString = JSON.toJSONString(list);
        write(jsonString);
    }

    public void write(String jsonString) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
