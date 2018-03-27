/**
 * 
 */
package com.risenb.util;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月11日 上午8:54:26 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class TestBean2Map {

	public static void main(String[] args) {
		Dog info = new Dog();
		info.setCountry("cc");
	//	info.setName("Dog");

		info.setCategory("gram");
		info.setOwner("wang");

		System.out.println(MapUtil.beanToMap(info, false));
	}
}
class animal {
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}

class Dog extends animal {
	private String country;
	private String category;
	private String owner;
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}