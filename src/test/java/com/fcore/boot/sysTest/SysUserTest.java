/*package com.fcore.boot.sysTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fcore.boot.entity.SysUser;
import com.fcore.boot.service.SysUserService;
import com.fcore.boot.service.UserRealm;
import com.fcore.boot.utils.DateTimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SysUserTest {
	private static Logger log = LoggerFactory.getLogger(SysUserTest.class);

	@Autowired
	private SysUserService userService;
	@Autowired
	private UserRealm userRealm;

	@Before
	public void init() {
		log.info("提示信息：测试初始化!");
	}

	@Test
	public void addSysUser() {
		SysUser user = new SysUser();
		user.setIsDelete(1);
		user.setLoginName("zjk");
		user.setUserName("张居开");
		user.setSalt(UUID.randomUUID().toString());
		user.setPassword(userRealm.shiroMd5("123456", user.getSalt(), 2));
		long id = userService.add(user);
		assertTrue(id > 0);
	}

	@Test
	public void getUser() {
		SysUser sysUser = userService.getById(1l);
		assertTrue(sysUser!=null);
	}
	
	@Test
	public void UpdateUser() {
		SysUser sysUser = userService.getById(1l);
		String createTime = DateTimeUtil.getNowDateStr(DateTimeUtil.yyyy_MM_dd_HH_mm_ss);
		sysUser.setCreateTime(createTime);
		userService.update(sysUser);
		sysUser = userService.getById(1l);
		assertEquals(sysUser.getCreateTime(),createTime);
	}
	
	public static String matcherStr(String str, Map<String, String> param) {
		StringBuffer sb = new StringBuffer();
		String regex = "\\$\\{(.+?)\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String name = matcher.group(1);// 键名
			String value = (String) param.get(name);// 键值
			if (value == null) {
				value = "";
			}
			matcher.appendReplacement(sb, value);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	public static void main(String[] args) {
        String str = "您收到来自${role}的工单${num}等待处理，查看${url}";
        Map<String, String> param = new HashMap<String, String>();
        param.put("role", "客户经理");
        param.put("num", "100001");
        param.put("url", "https://www.baidu.com");
        System.out.println(matcherStr(str, param));
    }
}
*/