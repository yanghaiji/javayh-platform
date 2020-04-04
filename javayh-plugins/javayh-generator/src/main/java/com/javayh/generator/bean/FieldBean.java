package com.javayh.generator.bean;

/**
 * 生成get/set方法模板
 */
public class FieldBean {
	private String columnName;
	private Class vhClass;
	private String fieldName;
	private String fieldNameUp;
	private String fieldContent;
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the vhClass
	 */
	public Class getVhClass() {
		return vhClass;
	}
	/**
	 * @param vhClass the vhClass to set
	 */
	public void setVhClass(Class vhClass) {
		this.vhClass = vhClass;
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the fieldContent
	 */
	public String getFieldContent() {
		return fieldContent;
	}
	/**
	 * @param fieldContent the fieldContent to set
	 */
	public void setFieldContent(String fieldContent) {
		this.fieldContent = fieldContent;
	}
	/**
	 * @return the fieldNameUp
	 */
	public String getFieldNameUp() {
		return fieldNameUp;
	}
	/**
	 * @param fieldNameUp the fieldNameUp to set
	 */
	public void setFieldNameUp(String fieldNameUp) {
		this.fieldNameUp = fieldNameUp;
	}
}
