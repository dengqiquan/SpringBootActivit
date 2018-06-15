package com.fcore.boot.web.sys;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fcore.boot.bean.CommonConstants;
import com.fcore.boot.entity.SysUser;
import com.fcore.boot.service.SysUserService;
import com.fcore.boot.utils.CommUtil;
import com.fcore.boot.utils.DateTimeUtil;
import com.fcore.boot.web.BaseController;

@Controller
public class LoginController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private SysUserService userService; 

	/**
	 * Go login.jsp
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(@ModelAttribute("message") String message) {
		System.out.println("message:" + message);
		return "login";
	}

	/**
	 * Go login
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("passWord");

		UsernamePasswordToken upt = new UsernamePasswordToken(loginName, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(upt);
		} catch (UnknownAccountException uae) {
			redirectAttributes.addFlashAttribute("message", CommonConstants.NO_USER_MSG);
		} catch (IncorrectCredentialsException ice) {
			redirectAttributes.addFlashAttribute("message", CommonConstants.PWD_ERROR_MSG);
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			redirectAttributes.addFlashAttribute("message", CommonConstants.USER_INFO_ERROR_MSG);
		}
		// 验证是否登录成功
		if (subject.isAuthenticated()) {
			
			//更新用户登录记录
			SysUser  user = this.getSessionUser();
			user.setLastLoginTime(DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss));
			user.setLastLoginIp(CommUtil.getIpAddr(request));
			userService.update(user);
			return "redirect:/views/";
		} else {
			upt.clear();
			return "redirect:/login";
		}

	}

	/**
	 * Exit
	 * 
	 * @return
	 */
	@RequestMapping("logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/index";
	}
}
