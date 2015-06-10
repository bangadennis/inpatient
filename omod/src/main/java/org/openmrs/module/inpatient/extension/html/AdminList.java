/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.inpatient.extension.html;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class defines the links that will appear on the administration page under the
 * "inpatient.title" heading. 
 */
public class AdminList extends AdministrationSectionExt {
	
	/**
	 * @see AdministrationSectionExt#getMediaType()
	 */
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}
	
	/**
	 * @see AdministrationSectionExt#getTitle()
	 */
	public String getTitle() {
		return "inpatient.title";
	}
	
	/**
	 * @see AdministrationSectionExt#getLinks()
	 */
	public Map<String, String> getLinks() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("/module/inpatient/manage.form", "inpatient.manage");
		map.put("/module/inpatient/findPatient.form", "inpatient.searchPatient");
		map.put("/module/inpatient/addward.form", "inpatient.addWard");
		map.put("/module/inpatient/listwards.form", "inpatient.listWards");
		map.put("/module/inpatient/listInpatient.form", "inpatient.listInpatient");
		map.put("/module/inpatient/listadmission.form", "inpatient.listadmission");
		map.put("/module/inpatient/listdischarge.form", "inpatient.listdischarge");
		return map;
	}
	
}
