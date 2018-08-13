package com.liangjinhai.supercat.sys.entity;


import java.io.Serializable;

/**
 * 角色资源
 * 
 * @author Administrator
 *
 */
public class RoleMenu implements Serializable {

	private Integer id;

	private Role role;

	private Menu menu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
