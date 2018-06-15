package com.fcore.boot.web.activiti;
/*package com.fcore.boot.controller.activiti;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Assert;
import org.junit.*;

public class ActivitiTest {
	@Test
	public void testDynamicDeploy() throws Exception {
	  BpmnModel model = new BpmnModel();
	  Process process = new Process();
	  model.addProcess(process);
	  process.setId("my-process");
	 
	  process.addFlowElement(createStartEvent());
	  process.addFlowElement(createUserTask("task1", "First task", "fred"));
	  process.addFlowElement(createUserTask("task2", "Second task", "john"));
	  process.addFlowElement(createEndEvent());
	 
	  process.addFlowElement(createSequenceFlow("start", "task1"));
	  process.addFlowElement(createSequenceFlow("task1", "task2"));
	  process.addFlowElement(createSequenceFlow("task2", "end"));
	 
	  // 2. Generate graphical information
	  new BpmnAutoLayout(model).execute();
	 
	  // 3. Deploy the process to the engine
	  Deployment deployment = activitiRule.getRepositoryService().createDeployment()
	    .addBpmnModel("dynamic-model.bpmn", model).name("Dynamic process deployment")
	    .deploy();
	 
	  // 4. Start a process instance
	  ProcessInstance processInstance = activitiRule.getRuntimeService()
	    .startProcessInstanceByKey("my-process");
	 
	  // 5. Check if task is available
	  List<> tasks = activitiRule.getTaskService().createTaskQuery()
	    .processInstanceId(processInstance.getId()).list();
	 
	 
	  // 6. Save process diagram to a file  
	  InputStream processDiagram = activitiRule.getRepositoryService()
	    .getProcessDiagram(processInstance.getProcessDefinitionId());
	  FileUtils.copyInputStreamToFile(processDiagram, new File("target/diagram.png"));
	 
	  // 7. Save resulting BPMN xml to a file
	  InputStream processBpmn = activitiRule.getRepositoryService()
	    .getResourceAsStream(deployment.getId(), "dynamic-model.bpmn");
	  FileUtils.copyInputStreamToFile(processBpmn, 
	    new File("target/process.bpmn20.xml"));
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
}
*/