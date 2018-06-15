package com.fcore.boot.entity;

public class TestPOJO {

	private Long id;
    private String name;
    private int age;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "TestPOJO [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
    
    
}
