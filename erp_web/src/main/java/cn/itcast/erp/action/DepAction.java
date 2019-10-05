package cn.itcast.erp.action;
import cn.itcast.erp.biz.*;
import cn.itcast.erp.entity.Dep;
import com.alibaba.fastjson.JSON;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 部门Action
 */
public class DepAction {
    private IDepBiz depBiz;

    public void setDepBiz(IDepBiz depBiz) {
        this.depBiz = depBiz;
    }

    public void list(){
        List<Dep> list = depBiz.getList();
        String jsonString = JSON.toJSONString(list);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
