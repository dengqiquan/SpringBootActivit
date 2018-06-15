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
import com.fcore.boot.entity.SysRole;
import com.fcore.boot.entity.SysUser;
import com.fcore.boot.service.SysRoleService;
import com.fcore.boot.utils.CommUtil;
import com.fcore.boot.utils.DateTimeUtil;
import com.fcore.boot.web.BaseController;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value=CommonConstants.ROOT_VIEWS+"/sysRole")
public class SysRoleController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping(value="/list")
	public String list(Model model,SysRole sysRole) {
		Pager pager = sysRoleService.findForPager(sysRole);
		model.addAttribute("pager", pager);
		model.addAttribute("sysRole", sysRole);
		return "/views/sysRole/list";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Model model,SysRole sysRole) {
		if(sysRole.getId()!=null && sysRole.getId()>0){
			sysRole = sysRoleService.getById(sysRole.getId());
		}
		model.addAttribute("sysRole", sysRole);
		return "/views/sysRole/edit";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(HttpServletResponse response,SysRole sysRole){
		SysUser user = this.getSessionUser();
		JSONObject object = new JSONObject();
		if(sysRole.getId() != null && sysRole.getId() >0){
			sysRole.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysRole.setUpdateUserId(user.getId());
			sysRoleService.update(sysRole);
			object.put("state",1);
		}else{
			sysRole.setCreateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysRole.setCreateUserId(user.getId());
			sysRole.setIsDelete(1);
			long id = sysRoleService.add(sysRole);
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
			SysRole sysRole = sysRoleService.getById(Long.parseLong(id));
			sysRole.setUpdateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			sysRole.setUpdateUserId(user.getId());
			sysRole.setIsDelete(2);
			sysRoleService.update(sysRole);
		}
		CommUtil.writeJson(response, object.toString());
	}
	
	
	@RequestMapping("/getById")
	@ResponseBody
	public SysRole getById(HttpServletRequest request){
		SysRole sysRole = null;
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			sysRole = sysRoleService.getById(Long.parseLong(id));
		}
		return sysRole;
	}
}
