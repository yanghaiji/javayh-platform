/**
 *   Copyright [2020] [Yang Hai Ji of copyright owner]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
