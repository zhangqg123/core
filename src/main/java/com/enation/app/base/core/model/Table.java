package com.enation.app.base.core.model;

import com.enation.framework.database.NotDbField;
import com.enation.framework.database.PrimaryKeyField;

/**
 * 会员实体
 * @author kanon 2015-9-21 添加会员实体注释
 *
 */
public class Table implements java.io.Serializable {
	private Integer table_id;	//会员ID
	private String name;		//会员用户名
	private String table_url;		//电子邮箱

	@PrimaryKeyField
	public Integer getTable_id() {
		return table_id;
	}

	public void setTable_id(Integer table_id) {
		this.table_id = table_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTable_url() {
		return table_url;
	}

	public void setTable_url(String table_url) {
		this.table_url = table_url;
	}
	
}