package com.fcore.boot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@WebFilter(filterName="paramFilter",urlPatterns="/*")
public class ParamFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(ParamFilter.class);
	@Override
	public void destroy() {
		logger.info("########过滤器destroy########");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		 HttpServletRequest hr=(HttpServletRequest) request;  
		    
         String url = hr.getServletPath().trim();  
         if (url.contains("")){  
        	 filterChain.doFilter(hr, response);  
         }else{  
            ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper((HttpServletRequest)request);  
            filterChain.doFilter(requestWrapper, response);  
         }  
		logger.info("########执行过滤器########");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("########过滤器init########");
	}

}
