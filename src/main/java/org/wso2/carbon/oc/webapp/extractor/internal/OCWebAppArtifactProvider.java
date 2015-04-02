package org.wso2.carbon.oc.webapp.extractor.internal;

import org.apache.axis2.context.ConfigurationContext;
import org.wso2.carbon.oc.agent.artifact.extractor.OCArtifactProvider;
import org.wso2.carbon.oc.webapp.extractor.OCWebAppDataHolder;
import org.wso2.carbon.oc.webapp.extractor.OCWebAppExtractorConstants;
import org.wso2.carbon.oc.webapp.extractor.beans.OCArtifact;
import org.wso2.carbon.webapp.mgt.WebApplication;
import org.wso2.carbon.webapp.mgt.WebApplicationsHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OCWebAppArtifactProvider implements OCArtifactProvider {

	@Override
	public List getArtifacts() {

		if(OCWebAppDataHolder.getInstance().getConfigurationContextService()==null){
			return new ArrayList();
		}

		ConfigurationContext serverConfigurationContext =
				OCWebAppDataHolder.getInstance().getConfigurationContextService()
				                  .getServerConfigContext();
		WebApplicationsHolder webApplicationsHolder =
				(WebApplicationsHolder) ((HashMap) serverConfigurationContext
						.getProperty(OCWebAppExtractorConstants.HOLDER_LIST_PROPERTY))
						.get(OCWebAppExtractorConstants.WEB_APPS);
		Map<String, WebApplication> startedWebAppMap = webApplicationsHolder.getStartedWebapps();
		Map<String, WebApplication> faultyWebAppMap = webApplicationsHolder.getFaultyWebapps();
		Map<String, WebApplication> stoppedWebAppMap = webApplicationsHolder.getStoppedWebapps();

		List<OCArtifact> webApps = new ArrayList<OCArtifact>();

		for (WebApplication webApplication : startedWebAppMap.values()) {
			OCArtifact artifact = new OCArtifact(webApplication.getLastModifiedTime(),
			                                     webApplication.getVersion());
			artifact.setProperty(OCWebAppExtractorConstants.DISPLAY_NAME,
			                     webApplication.getDisplayName());
			artifact.setProperty(OCWebAppExtractorConstants.WEB_APP_TYPE,
			                     OCWebAppExtractorConstants.WEB_APP_FILTER.toString());
			artifact.setProperty(OCWebAppExtractorConstants.CONTEXT,
			                     webApplication.getContextName());
			artifact.setProperty(OCWebAppExtractorConstants.HOST_NAME,
			                     webApplication.getHostName());
			artifact.setProperty(OCWebAppExtractorConstants.STATE, webApplication.getState());
			webApps.add(artifact);
		}

		for (WebApplication webApplication : faultyWebAppMap.values()) {
			OCArtifact artifact = new OCArtifact(webApplication.getLastModifiedTime(),
			                                     webApplication.getVersion());
			artifact.setProperty(OCWebAppExtractorConstants.DISPLAY_NAME,
			                     webApplication.getDisplayName());
			artifact.setProperty(OCWebAppExtractorConstants.WEB_APP_TYPE,
			                     OCWebAppExtractorConstants.WEB_APP_FILTER.toString());
			artifact.setProperty(OCWebAppExtractorConstants.CONTEXT,
			                     webApplication.getContextName());
			artifact.setProperty(OCWebAppExtractorConstants.HOST_NAME,
			                     webApplication.getHostName());
			artifact.setProperty(OCWebAppExtractorConstants.STATE, webApplication.getState());
			webApps.add(artifact);
		}

		for (WebApplication webApplication : stoppedWebAppMap.values()) {
			OCArtifact artifact = new OCArtifact(webApplication.getLastModifiedTime(),
			                                     webApplication.getVersion());
			artifact.setProperty(OCWebAppExtractorConstants.DISPLAY_NAME,
			                     webApplication.getDisplayName());
			artifact.setProperty(OCWebAppExtractorConstants.WEB_APP_TYPE,
			                     OCWebAppExtractorConstants.WEB_APP_FILTER.toString());
			artifact.setProperty(OCWebAppExtractorConstants.CONTEXT,
			                     webApplication.getContextName());
			artifact.setProperty(OCWebAppExtractorConstants.HOST_NAME,
			                     webApplication.getHostName());
			artifact.setProperty(OCWebAppExtractorConstants.STATE, webApplication.getState());
			webApps.add(artifact);
		}

		return webApps;
	}

	@Override
	public String getArtifactType() {
		return OCWebAppExtractorConstants.ARTIFACT_TYPE;
	}
}