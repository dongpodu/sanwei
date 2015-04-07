package com.well.sanwei.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class AppPropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static final Log log = LogFactory.getLog(AppPropertyConfigurer.class);
	
	@Override
	protected Properties mergeProperties() throws IOException {
		Properties superProps = super.mergeProperties();
		superProps.put("env", ResourceMap.getEvnDir());
		log.info(">>>>>当前环境 env:" + superProps.getProperty("env"));
		return superProps;
	}

}
