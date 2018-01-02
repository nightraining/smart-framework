package org.smart4j.framework.mvc.impl;

import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.mvc.UploadHelper;
import org.smart4j.framework.mvc.ViewResolver;
import org.smart4j.framework.mvc.bean.Result;
import org.smart4j.framework.mvc.bean.View;
import org.smart4j.framework.util.MapUtil;
import org.smart4j.framework.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 默认视图解析器
 */
public class DefaultViewResolver implements ViewResolver {
    @Override
    public void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult) {
        if (actionMethodResult != null){
            // Action 返回值可为 View 或 Result
           if (actionMethodResult instanceof View){
               //若为 View ,则需考虑两种视图类型（重定向 或 转发）
               View view = (View) actionMethodResult;
               if (view.isRedirect()){
                   // 获取路径
                   String path = view.getPath();
                   // 重定向请求
                   WebUtil.redirectRequest(path, request, response);
               } else {
                   //获取路径
                   String path = FrameworkConstant.JSP_PATH + view.getPath();
                   // 初始化请求属性
                   Map<String, Object> data = view.getData();
                   if (MapUtil.isNotEmpty(data)){
                       for (Map.Entry<String, Object> entry : data.entrySet()){
                           request.setAttribute(entry.getKey(), entry.getValue());
                       }
                   }
                   // 请求转发
                   WebUtil.forwarRequest(path,request,response);
               }
           } else{
               // 若为 Result，则需考虑两种请求类型（文件上传 或 普通请求）
               Result result  = (Result) actionMethodResult;
               if (UploadHelper.isMultipart(request)){
                   //对于 multipart 类型，说明文件上传，需要转换为 HTML 格式并写入响应中
                   WebUtil.writeHTML(response, result);
               } else {
                   //对于其他类型，统一转换为 JSON 格式写入响应中
                   WebUtil.writeJSON(response, result);
               }
           }
        }
    }
}
