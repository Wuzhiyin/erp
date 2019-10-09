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
    private Dep dep2;
    private Object param;

    public Dep getDep1() {
        return dep1;
    }

    public void setDep1(Dep dep1) {
        this.dep1 = dep1;
    }

    public Dep getDep2() {
        return dep2;
    }

    public void setDep2(Dep dep2) {
        this.dep2 = dep2;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
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
        List<Dep> list = depBiz.getList(dep1,dep2,param,firstResult, rows);
        long total = depBiz.getCount(dep1,dep2,param);
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
    /**新增，修改*/
    private Dep dep;

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
    }

    /**
     * 新增
     * @param
     */
    public void add(){
        //{"success":true,"message":""}
        //返回前端的JSON数据
        Map<String, Object> rtn = new HashMap<String, Object>();
        try {
            depBiz.add(dep);
            rtn.put("success",true);
            rtn.put("message","新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            rtn.put("success",false);
            rtn.put("message","新增失败");
        }
        write(JSON.toJSONString(rtn));
    }
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    /**
     * 删除
     * @param jsonString
     */
    public void delete(){

        try {
            depBiz.delete(id);
            ajaxReturn(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxReturn(false, "删除失败");
        }
    }
    /**
     * 通过编辑查询对象
     */
    public void get(){
        Dep dep = depBiz.get(id);
        String jsonString = JSON.toJSONString(dep);
        System.out.println("转换前：" + jsonString);
        //{"name":"管理员组","tele":"000011","uuid":1}
        String jsonStringAfter = mapData(jsonString, "dep");
        System.out.println("转换后：" + jsonStringAfter);
        write(jsonStringAfter);
    }
    /**
     * 修改
     */
    public void update(){
        try {
            depBiz.update(dep);
            ajaxReturn(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxReturn(false, "修改失败");
        }
    }

    /**
     * //{"name":"管理员组","tele":"000011","uuid":1}
     * @param jsonString JSON数据字符串
     * @param prefix 要加上的前缀
     * @return  {"dep.name":"管理员组","dep.tele":"000011","dep.uuid":1}
     */
    public String mapData(String jsonString, String prefix){
        Map<String, Object> map = JSON.parseObject(jsonString);

        //存储key加上前缀后的值
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //给每key值加上前缀
        for(String key : map.keySet()){
            dataMap.put(prefix + "." + key, map.get(key));
        }
        return JSON.toJSONString(dataMap);
    }

    public void ajaxReturn(boolean success, String message){
        //返回前端的JSON数据
        Map<String, Object> rtn = new HashMap<String, Object>();
        rtn.put("success",success);
        rtn.put("message",message);
        write(JSON.toJSONString(rtn));
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
