/*package com.fcore.boot.sysTest;

import com.fcore.entity.DbEntity;
import com.fcore.entity.FileEntity;
import com.fcore.entity.FlagEntity;
import com.fcore.service.CodeFactoryService;

public class CodegenerateUtil {

	public static void main(String[] args) {
		codeRun("sys_role", "zhangjukai", false, "127.0.0.1","spring_boot","root","123456");
	}

	*//**
	 * 生成器方法入口
	 * @param tablename 表名
	 * @param author  作者
	 * @param isCover 是否覆盖之前的文件
	 * @param ip 数据库ip
	 * @param dbname  数据库名 方法中有数据库账号／密码／数据库端口，需要调整请修改
	 *//*
	private static void codeRun(String tablename, String author,
			boolean isCover, String ip, String dbname,String username,String pwd) {
		String basePackage = "com.fcore.boot";
		String realPath = CodegenerateUtil.class.getResource("/").toString();
		realPath = realPath.substring(0, realPath.indexOf("/target/")) + "/";
		String projectPath = (realPath + "src/main/").replaceAll("file:", "");
		System.out.println("filepath:" + projectPath);
		try {
			DbEntity dbEntity = new DbEntity("com.mysql.jdbc.Driver","jdbc:mysql://" + ip + ":3306/" + dbname + "", username,pwd);
			// DbEntity dbEntity = new DbEntity("org.postgresql.Driver",
			// "jdbc:postgresql://"+ip+":5432/"+dbname+"", "ofbiz",
			// "ofbiz!#@$");
			FileEntity fileEntity = new FileEntity(projectPath, basePackage,author, isCover);
			FlagEntity flagEntity = new FlagEntity();
			// 控制不生成aciton web.xml page，默认为true
			flagEntity.setCreateAction(true);
			flagEntity.setCreateWebXml(false);
			flagEntity.setCreatePage(true);
			flagEntity.setCreatePropertie(false);
			flagEntity.setCreateService(true);
			flagEntity.setCreateDao(false);
			flagEntity.setCreateIDao(false);
			flagEntity.setCreateMapperXml(true);
			flagEntity.setCreateMapperJava(true);
			flagEntity.setCreateIservice(true);
			// 设置加载模版方式为从引用项目加载
			fileEntity.setTempateReadType(2);
			// 设置模版路径
			fileEntity.setTemplatePath("src/main/resources/ftl");
			CodeFactoryService.codeGenerateRun(tablename, dbEntity, fileEntity,flagEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
*/