package com.fcore.boot.web.activiti;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("activiti")
public class ActivitiDemoController {
	private static Logger logger = Logger.getLogger(ActivitiDemoController.class);
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

	@RequestMapping(value = "startProcess", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> startProcess() {
		// 保存需求，给自己指派一条任务
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "张居开");
		variables.put("userId", "100001");
		variables.put("vacationMotivation", "I'm really tired!");
		// 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("sxlt_test", variables);
		return null;
	}

	@RequestMapping(value = "test", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void test() {
		/*
		 * BpmnModel model = new BpmnModel(); Process process = new Process();
		 * model.addProcess(process); process.setId("my_process");
		 * 
		 * process.addFlowElement(createStartEvent()); UserTask userTask1 =
		 * createUserTask("task01", "First task", "fred"); List<FormProperty>
		 * properties = new ArrayList<FormProperty>(); for(int i=0;i<5;i++){
		 * FormProperty property = new FormProperty(); property.setId("id_"+i);
		 * property.setName("name_"+i); properties.add(property); }
		 * userTask1.setFormProperties(properties);
		 * process.addFlowElement(userTask1);
		 * process.addFlowElement(createUserTask("task02", "Second task",
		 * "john")); process.addFlowElement(createEndEvent());
		 * 
		 * process.addFlowElement(createSequenceFlow("start", "task01"));
		 * process.addFlowElement(createSequenceFlow("task01", "task02"));
		 * process.addFlowElement(createSequenceFlow("task02", "end"));
		 * Deployment deployment = repositoryService.createDeployment()
		 * .addBpmnModel("dynamic_model.bpmn",
		 * model).name("Dynamic process deployment") .deploy();
		 */

		/*
		 * TaskFormData data = formService.getTaskFormData("10004");
		 * logger.info(formService.getRenderedTaskForm("10004"));
		 */
/*try {
	ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("my_process").latestVersion().singleResult();  
    BpmnModel bm = repositoryService.getBpmnModel(pd.getId());  
    ProcessDiagramGenerator.generateDiagram(bm, null);
} catch (Exception e) {
	// TODO: handle exception
}*/

		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("my_process").latestVersion().singleResult();  
	    BpmnModel bm = repositoryService.getBpmnModel(pd.getId());  
		//List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
	    DefaultProcessDiagramGenerator dpd = new DefaultProcessDiagramGenerator();
		InputStream imageStream = dpd.generateDiagram(bm, "png", null);
		
		/*ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId("7501").singleResult();
		InputStream processDiagram = repositoryService.getProcessDiagram(processDefinition.getId());

		//InputStream processDiagram = repositoryService.getProcessDiagram("my-process:1:7503");
		inputstreamtofile(processDiagram, new File("target/diagram.png"));*/

		/*
		 * InputStream processBpmn =
		 * repositoryService.getResourceAsStream("15001", "dynamic_model.bpmn");
		 * inputstreamtofile(processBpmn, new
		 * File("target/process.bpmn20.xml"));
		 */
	}

	public static void inputstreamtofile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected UserTask createUserTask(String id, String name, String assignee) {
		UserTask userTask = new UserTask();
		userTask.setName(name);
		userTask.setId(id);
		userTask.setAssignee(assignee);
		return userTask;
	}

	protected SequenceFlow createSequenceFlow(String from, String to) {
		SequenceFlow flow = new SequenceFlow();
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		return flow;
	}

	protected StartEvent createStartEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("start");
		return startEvent;
	}

	protected EndEvent createEndEvent() {
		EndEvent endEvent = new EndEvent();
		endEvent.setId("end");
		return endEvent;
	}

	public List<Map<String, Object>> getProcessTrace(String wfKey) throws Exception {  
        List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();  
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(wfKey).latestVersion().singleResult();  
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(pd.getId());  
        List<ActivityImpl> activitiList = processDefinition.getActivities();  
        InputStream xmlIs = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());  
        BpmnModel bm = new BpmnXMLConverter().convertToBpmnModel(new InputStreamSource(xmlIs), false, true);  
  
        Class<?> clazz = Class.forName("org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator");  
        Method method = clazz.getDeclaredMethod("initProcessDiagramCanvas", BpmnModel.class);  
        method.setAccessible(true);   
        ProcessDiagramGenerator pdc = (ProcessDiagramGenerator) method.invoke(clazz.newInstance(), bm); // 调用方法  
  
        clazz = Class.forName("org.activiti.engine.impl.bpmn.diagram.ProcessDiagramCanvas");  
        Field minXField = clazz.getDeclaredField("minX"); // 得到minX字段  
        Field minYField = clazz.getDeclaredField("minY");  
        minXField.setAccessible(true);  
        minYField.setAccessible(true);  
        int minX = minXField.getInt(pdc);// 最小的x值    
        int minY = minYField.getInt(pdc); // 最小的y的值  
  
        minX = minX > 0 ? minX - 5 : 0;  
        minY = minY > 0 ? minY - 5 : 0;  
        for (ActivityImpl activity : activitiList) {  
            Map<String, Object> activityInfo = new HashMap<String, Object>();  
            activityInfo.put("width", activity.getWidth());  
            activityInfo.put("height", activity.getHeight());  
            activityInfo.put("x", activity.getX() - minX);  
            activityInfo.put("y", activity.getY() - minY);  
            activityInfo.put("actId", activity.getId());  
            activityInfo.put("name", activity.getProperty("name"));    
            activityInfos.add(activityInfo);  
        }  
        return activityInfos;  
    }  
	
	@RequestMapping(value = "test001", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void test001(){
		ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionId("my-process:1:7503").singleResult();  
        String diagramResourceName = procDef.getDiagramResourceName();   
       InputStream imageStream = repositoryService.getResourceAsStream(procDef.getDeploymentId(), diagramResourceName);
       inputstreamtofile(imageStream, new File("target/diagram.png"));
	}
}
