package com.carter.demo.classload.pay;

import lombok.Data;

import java.io.Serializable;



/**
 * @author Carter.li
 */
@Data
public class PayConfig  implements Serializable{

	private static final long serialVersionUID = -1533000266479019238L;

	private int payType;

	private String payMode;

	private String name;

	private String remark;

	private String carrier;

	private int payStyle;

	private int startTime;

	private int endTime;

	private int dayLimit;
	private int monthLimit;
	private int dayTotalLimit;
	private int minPlayCount;
	private String gameLimit;
	private String proLimit;
	private String need;
	private String cmdMode;
	private String location;
	private int switched;
	
	private String define;


}
