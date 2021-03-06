package org.wso2.carbon.oc.webapp.extractor.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.oc.agent.artifact.extractor.OCArtifactProvider;
import org.wso2.carbon.oc.webapp.extractor.OCWebAppDataHolder;
import org.wso2.carbon.utils.ConfigurationContextService;

/*
 * Copyright 2015 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @scr.component name = "org.wso2.carbon.oc.webapp.extractor.operationcenterextractorcomponent" immediate=true
 * @scr.reference name="config.context.service"
 * interface="org.wso2.carbon.utils.ConfigurationContextService"
 * cardinality="1..1" policy="dynamic"
 * bind="setConfigurationContextService"
 * unbind="unsetConfigurationContextService"
 */

public class OCWebAppExtractorComponent {

	Log logger = LogFactory.getLog(OCWebAppExtractorComponent.class);

	protected void activate(ComponentContext componentContext) {
		OCArtifactProvider ocWebAppArtifactProvider =  new OCWebAppArtifactProvider();

		componentContext.getBundleContext().registerService(OCArtifactProvider.class,ocWebAppArtifactProvider,null);
	}

	protected void deactivate(ComponentContext componentContext) {

	}

	protected void setConfigurationContextService(
			ConfigurationContextService configurationContextService) {
		OCWebAppDataHolder.getInstance().setConfigurationContextService(configurationContextService);
	}

	protected void unsetConfigurationContextService(
			ConfigurationContextService configurationContextService) {
		OCWebAppDataHolder.getInstance().setConfigurationContextService(null);
	}

}
