package com.fcore.boot.web.activiti;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcore.boot.utils.ActivitiUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("activiti001")
public class Activiti001Controller {
	
	private static Logger logger = Logger.getLogger(Activiti001Controller.class);
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	FormService formService;

	@RequestMapping(value = "createProcess", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void createProcess() {
		BpmnModel model = new BpmnModel();
		Process process = new Process();
		model.addProcess(process);
		process.setId("my-process");
		process.setName("自定义流程001");
		StartEvent startEvent = ActivitiUtil.createStartEvent();

		List<FormProperty> properties = new ArrayList<FormProperty>();
		for (int i = 0; i < 5; i++) {
			FormProperty property = new FormProperty();
			property.setId("start_id_" + i);
			property.setName("start_name_" + i);
			properties.add(property);
		}
		startEvent.setFormProperties(properties);

		UserTask task1 = ActivitiUtil.createUserTask("task1", "First task", "fred");
		properties = new ArrayList<FormProperty>();
		for (int i = 0; i < 5; i++) {
			FormProperty property = new FormProperty();
			property.setId("task1_id_" + i);
			property.setName("task1_name_" + i);
			properties.add(property);
		}
		task1.setFormProperties(properties);
		
		
		process.addFlowElement(startEvent);
		process.addFlowElement(task1);
		process.addFlowElement(ActivitiUtil.createUserTask("task2", "Second task", "john"));
		process.addFlowElement(ActivitiUtil.createEndEvent());

		process.addFlowElement(ActivitiUtil.createSequenceFlow("start", "task1"));
		process.addFlowElement(ActivitiUtil.createSequenceFlow("task1", "task2"));
		process.addFlowElement(ActivitiUtil.createSequenceFlow("task2", "end"));

		// 2. Generate graphical information
		new BpmnAutoLayout(model).execute();

		// 3. Deploy the process to the engine
		Deployment deployment = repositoryService.createDeployment().addBpmnModel("dynamic-model.bpmn", model)
				.name("Dynamic process deployment").deploy();
	}
	
	@RequestMapping(value = "getProcessImage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void getProcessImage(){
		InputStream processDiagram = repositoryService.getProcessDiagram("my-process:1:17508");
		ActivitiUtil.inputstreamtofile(processDiagram, new File("target/diagram.png"));
	}
	
	@RequestMapping(value = "getProcessXml", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void getProcessXml(){
		InputStream processBpmn = repositoryService.getResourceAsStream("20001", "dynamic-model.bpmn");
		ActivitiUtil.inputstreamtofile(processBpmn,new File("target/process.bpmn20.xml"));
	}
	
	@RequestMapping(value = "getProcessParam", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void getProcessParam(){
		StartFormData data = formService.getStartFormData("my-process:1:17508");
		if(data.getFormProperties()!=null){
			logger.info(JSONArray.fromObject(data.getFormProperties()));
		}
	}
	
	@RequestMapping(value = "setProcessParam", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void setProcessParam(){
		BpmnModel bpmnModel = repositoryService.getBpmnModel("my-process:1:17508");
		Process process = bpmnModel.getMainProcess();
		StartEvent startEvent = (StartEvent) process.getFlowElement("start");
		List<FormProperty> properties = startEvent.getFormProperties();
		for (int i = 0; i < 5; i++) {
			FormProperty property = new FormProperty();
			property.setId("start_id_02_" + i);
			property.setName("start_name_02" + i);
			property.setType("string");
			properties.add(property);
		}
		startEvent.setFormProperties(properties);
		new BpmnAutoLayout(bpmnModel).execute();
		Deployment deployment = repositoryService.createDeployment().addBpmnModel("dynamic-model.bpmn", bpmnModel)
				.deploy();
	}
	
	@RequestMapping(value = "processInfos", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void processInfos(){
		BpmnModel bpmnModel = repositoryService.getBpmnModel("my-process:1:17508");
		Process process = bpmnModel.getMainProcess();
		Collection<FlowElement>  elements = process.getFlowElements();
		/*List<IdentityLink> links = repositoryService.getIdentityLinksForProcessDefinition("my-process:1:17508");
		for(IdentityLink identityLink:links){
			System.out.println(identityLink.getType());
		}*/
		for(FlowElement element : elements){
			System.out.println(element.getName());
		}
	}
	
	@RequestMapping(value = "startProcess", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> startProcess() {
		// 保存需求，给自己指派一条任务
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "张居开");
		variables.put("userId", "100001");
		variables.put("vacationMotivation", "I'm really tired!");
		// 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceById("my-process:2:20004", variables);
		return null;
	}
	
	@RequestMapping(value = "downloadFile")
	@ResponseBody
	public void download(String filePath,String fileName, HttpServletResponse response, HttpServletRequest request) {
		try {
			File file = new File(fileName);
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
}
