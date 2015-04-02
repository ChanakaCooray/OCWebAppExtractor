package org.wso2.carbon.oc.webapp.extractor;

import org.wso2.carbon.utils.ConfigurationContextService;

public class OCWebAppDataHolder {

	private ConfigurationContextService configurationContextService;

	private static OCWebAppDataHolder instance = new OCWebAppDataHolder();

	private OCWebAppDataHolder(){

	}

	public static OCWebAppDataHolder getInstance(){
		return instance;
	}

	public ConfigurationContextService getConfigurationContextService() {
		return configurationContextService;
	}

	public void setConfigurationContextService(
			ConfigurationContextService configurationContextService) {
		this.configurationContextService = configurationContextService;
	}
}


