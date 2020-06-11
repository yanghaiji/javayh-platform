package com.javayh.api.sys.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
/**
 * <p>  *  Bean
 * </p>  * @author：Dylan
 * @version：V1.0
 * @since：2020-04-16
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SysLogistics implements java.io.Serializable{

	/**
	 *  
	 */
	@ApiModelProperty(hidden = true)
	private Integer id; 
	/**
	 *  收件人
	 */
	@ApiModelProperty(value = "收件人")
	private String addresseeName; 
	/**
	 *  收件人地址
	 */
	@ApiModelProperty(value = "收件人地址")
	private String addressee; 
	/**
	 *  
	 */
	@ApiModelProperty(hidden = true)
	private Integer addresseePhone; 
	/**
	 *  寄件人
	 */
	@ApiModelProperty(value = "寄件人")
	private String senderName; 
	/**
	 *  寄件人地址
	 */
	@ApiModelProperty(value = "寄件人地址")
	private String senderAdd; 
	/**
	 *  0,已接单，1,已发货，2，已签收
	 */
	@ApiModelProperty(value = "0,已接单，1,已发货，2，已签收")
	private Integer emsStatus; 
	/**
	 *  0,为退货，1，退货中，2，已退货
	 */
	@ApiModelProperty(value = "0,为退货，1，退货中，2，已退货")
	private Integer retreatStatus; 
	/**
	 *  退货原因
	 */
	@ApiModelProperty(value = "退货原因")
	private String retreatInfo; 
	/**
	 *  创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createDate; 
	/**
	 *  
	 */
	@ApiModelProperty(hidden = true)
	private Date updateDate; 
	/**
	 *  操作人
	 */
	@ApiModelProperty(value = "操作人")
	private String createBy; 

}
