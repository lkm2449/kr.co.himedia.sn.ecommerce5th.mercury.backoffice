/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.mercury
 * Description	:
 * Environment	: JRE 1.8 or more
 * File			: BackofficeCmpn.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240320155005][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.common.component;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.himedia.ecommerce.util.Datetime;
import kr.co.himedia.ecommerce.util.servlet.Request;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-03-20
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Component("kr.co.himedia.ecommerce.backoffice.common.componen.BackofficeCmpn")
public class BackofficeCmpn {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BackofficeCmpn.class);
	
	@Autowired
	private Properties staticProperties;
	

	public boolean isSession(HttpServletRequest request) {
		
		boolean isExist = true;
		
		String fullURL = Request.getFullURL(request);
		
		try {
			String seq_mng			= Request.getSession(request, "SEQ_MNG", "", false);
			String ip			= Request.getRemoteAddr(request, staticProperties.getProperty("common.server.web", "[UNDEFINED]"));
			
			String prefixSession = staticProperties.getProperty("seller.logging.session", "[UNDEFINED]");
			
			if (seq_mng == null || seq_mng.equals("")) {
				
				logger.error("# REGION ***************************" + prefixSession + "***************************");
				logger.error("# DENY DESC: SESSION DOES NOT EXIST");
				logger.error("# SEQ_MNG  : " + seq_mng);
				logger.error("# IP       : " + ip);
				logger.error("# URL      : " + fullURL);
				logger.error("# TIME     : " + Datetime.getNow("yyyy-MM-dd HH:mm:ss"));
				logger.error("# END REGION ***********************" + prefixSession + "***************************\n");
				
				isExist = false;
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".isSession()] " + e.getMessage(), e);
		}
		
		return isExist;
	}

	
}
