package com.fcore.boot.web.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcore.boot.bean.CommonConstants;
import com.fcore.boot.bean.Pager;
import com.fcore.boot.entity.SysUser;
import com.fcore.boot.utils.CommUtil;
import com.fcore.boot.utils.DateTimeUtil;
import com.fcore.boot.web.BaseController;

import net.sf.json.JSONObject;

import com.fcore.boot.entity.SysPermission;
import com.fcore.boot.service.SysPermissionService;

@Controller
@RequestMapping(value=CommonConstants.ROOT_VIEWS+"/sysPermission")
public class SysPermissionController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(SysPermissionController.class);

	@Autowired
	private SysPermissionService sysPermissionService;
	
	@RequestMapping(value="/list")
	public String list(Model model,SysPermission sysPermission) {
		Pager pager = sysPermissionService.findForPager(sysPermission);
		model.addAttribute("pager", pager);
		model.addAttribute("sysPermission", sysPermission);
		return "/views/sysPermission/list";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Model model,SysPermission sysPermission) {
		if(sysPermission.getId()!=null && sysPermission.getId()>0){
			sysPermission = sysPermissionService.getById(sysPermission.getId());
		}
		model.addAttribute("sysPermission", sysPermission);
		return "/views/sysPermission/edit";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(HttpServletResponse response,SysPermission sysPermission){
		SysUser user = this.getSessionUser();
		JSONObject object = new JSONObject();
		if(sysPermission.getId() != null && sysPermission.getId() >0){
			sysPermission.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysPermission.setUpdateUserId(user.getId());
			sysPermissionService.update(sysPermission);
			object.put("state",1);
		}else{
			sysPermission.setCreateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysPermission.setCreateUserId(user.getId());
			sysPermission.setIsDelete(1);
			long id = sysPermissionService.add(sysPermission);
			object.put("state",1);
		}
		CommUtil.writeJson(response, object.toString());
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public void deleteById(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		SysUser user = this.getSessionUser();
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			SysPermission sysPermission = sysPermissionService.getById(Long.parseLong(id));
			sysPermission.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysPermission.setUpdateUserId(user.getId());
			sysPermission.setIsDelete(2);
			sysPermissionService.update(sysPermission);
		}
		CommUtil.writeJson(response, object.toString());
	}
	
	
	@RequestMapping("/getById")
	@ResponseBody
	public SysPermission getById(HttpServletRequest request){
		SysPermission sysPermission = null;
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			sysPermission = sysPermissionService.getById(Long.parseLong(id));
		}
		return sysPermission;
	}
}
