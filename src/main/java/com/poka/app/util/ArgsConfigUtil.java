package com.poka.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author lb 功能启用工具类
 *
 */
public class ArgsConfigUtil {

	private Properties p;

	public ArgsConfigUtil() {
		this("argsConfig.properties");
	}

	public ArgsConfigUtil(String path) {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
		this.p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取人行下发预约取款启用标识
	 * @return
	 */
	public String getAppointmentPeopFlag() {
		return p.getProperty("appointmentPeopFlag");
	}
	
	/**
	 * 获取商行同步预约取款启用标识
	 * @return
	 */
	public String getAppointmentCommFlag() {
		return p.getProperty("appointmentCommFlag");
	}

	/**
	 * 获取下发可疑冠字号同步更新功能启用标识
	 * @return
	 */
	public String getMonRuleFlag() {
		return p.getProperty("monRuleFlag");
	}

	/**
	 * 获取商行机具信息同步启用标识
	 */
	public String getPerInfoFlag(){
		return p.getProperty("perInfoFlag");
	}
	
	/**
	 * 获取商行网点信息同步启用标识
	 */
	public String getBranchInfoFlag(){
		return p.getProperty("branchInfoFlag");
	}
	
	/**
	 * 获取商行银行日结和网点日结信息同步启用标识
	 */
	public String getBankAndNetRepFlag(){
		return p.getProperty("bankAndNetRepFlag");
	}
	
	/**
	 * 获取商行预约交款信息同步启用标识
	 */
	public String getPaymentFlag(){
		return p.getProperty("paymentFlag");
	}
	
	/**
	 * 获取商行查询申请信息同步启用标识
	 */
	public String getQryApplyFlag(){
		return p.getProperty("qryApplyFlag");
	}
	
	/**
	 * 获取商行横向调拨信息同步启用标识
	 */
	public String getBagInfoFlag(){
		return p.getProperty("bagInfoFlag");
	}
}
