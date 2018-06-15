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

import com.fcore.boot.entity.SysUserRole;
import com.fcore.boot.service.SysUserRoleService;

@Controller
@RequestMapping(value=CommonConstants.ROOT_VIEWS+"/sysUserRole")
public class SysUserRoleController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(SysUserRoleController.class);

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@RequestMapping(value="/list")
	public String list(Model model,SysUserRole sysUserRole) {
		Pager pager = sysUserRoleService.findForPager(sysUserRole);
		model.addAttribute("pager", pager);
		model.addAttribute("sysUserRole", sysUserRole);
		return "/views/sysUserRole/list";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Model model,SysUserRole sysUserRole) {
		if(sysUserRole.getId()!=null && sysUserRole.getId()>0){
			sysUserRole = sysUserRoleService.getById(sysUserRole.getId());
		}
		model.addAttribute("sysUserRole", sysUserRole);
		return "/views/sysUserRole/edit";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(HttpServletResponse response,SysUserRole sysUserRole){
		SysUser user = this.getSessionUser();
		JSONObject object = new JSONObject();
		if(sysUserRole.getSysUserId() != null && sysUserRole.getSysUserId() >0){
			sysUserRole.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysUserRole.setUpdateUserId(user.getId());
			sysUserRoleService.update(sysUserRole);
			object.put("state",1);
		}else{
			sysUserRole.setCreateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysUserRole.setCreateUserId(user.getId());
			sysUserRole.setIsDelete(1);
			long id = sysUserRoleService.add(sysUserRole);
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
			SysUserRole sysUserRole = sysUserRoleService.getById(Long.parseLong(id));
			sysUserRole.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysUserRole.setUpdateUserId(user.getId());
			sysUserRole.setIsDelete(2);
			sysUserRoleService.update(sysUserRole);
		}
		CommUtil.writeJson(response, object.toString());
	}
	
	
	@RequestMapping("/getById")
	@ResponseBody
	public SysUserRole getById(HttpServletRequest request){
		SysUserRole sysUserRole = null;
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			sysUserRole = sysUserRoleService.getById(Long.parseLong(id));
		}
		return sysUserRole;
	}
}
