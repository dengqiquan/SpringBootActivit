package com.fcore.boot.web.sys;
/*package com.fcore.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fcore.boot.dao.PersonDao;
import com.fcore.boot.entity.Person;

@RestController  
public class ObjectRedisController {  
  
    protected static Logger logger=LoggerFactory.getLogger(ObjectRedisController.class);  
      
    @Autowired  
    PersonDao personDao;  
      
    @RequestMapping("/setPerson")  
    public void set(String id,String name,Integer age){  
        logger.info("访问setPerson:id={},name={},age={}",id,name,age);  
        Person person=new Person(id,name,age);  
        personDao.save(person);  
    }  
      
    @RequestMapping("/getPerson")  
    public Person getPerson(String id){  
        return personDao.getPerson(id);  
    }  
      
}  
*/