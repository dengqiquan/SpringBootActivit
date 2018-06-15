package com.fcore.boot.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    
    private Map<String , String[]> params = new HashMap<String, String[]>();

	public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        this.params.putAll(request.getParameterMap());
        this.modifyParameterValues();
    }
	
    //重载一个构造方法
    public ParameterRequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {
        this(request);
        //这里将扩展参数写入参数表
        addAllParameters(extendParams);
    }
    
    //将parameter的值去除空格后重写回去
    public void modifyParameterValues(){
    	Set<String> set =params.keySet();
    	Iterator<String> it=set.iterator();
	    while(it.hasNext()){
           String key= (String) it.next();
           String[] values = params.get(key);
           values[0] = values[0].trim();
           params.put(key, values);
	     }
    }
    
    
    @Override //重写getParameter，代表参数从当前类中的map获取
    public String getParameter(String name) {
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }
    //同上
    public String[] getParameterValues(String name) {
         return params.get(name);
    }

   //增加多个参数
   public void addAllParameters(Map<String , Object>otherParams) {
        for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
            addParameter(entry.getKey() , entry.getValue());
        }
    }

    //增加参数
    public void addParameter(String name , Object value) {
        if(value != null) {
            if(value instanceof String[]) {
                params.put(name , (String[])value);
            }else if(value instanceof String) {
                params.put(name , new String[] {(String)value});
            }else {
                params.put(name , new String[] {String.valueOf(value)});
            }
        }
    }
}