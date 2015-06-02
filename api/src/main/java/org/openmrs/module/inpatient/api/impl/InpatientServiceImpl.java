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
package org.openmrs.module.inpatient.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.inpatient.Inpatient;
import org.openmrs.module.inpatient.api.InpatientService;
import org.openmrs.module.inpatient.api.db.InpatientDAO;

import java.util.List;

/**
 * It is a default implementation of {@link InpatientService}.
 */
public class InpatientServiceImpl extends BaseOpenmrsService implements InpatientService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private InpatientDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(InpatientDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public InpatientDAO getDao() {
	    return dao;
    }


    @Override
    public List<Inpatient> getAllInpatient() {
        return dao.getAllInpatient();
    }

    @Override
    public Inpatient getInpatient(Integer inpatientId) {
        return dao.getInpatient(inpatientId);
    }

    @Override
    public Inpatient getInpatientbyIdentifier(String inpatientId) {
        return dao.getInpatientbyIdentifier(inpatientId);
    }

    @Override
    public Inpatient saveInpatient(Inpatient inpatient) {
        return dao.saveInpatient(inpatient);
    }

    @Override
    public void purgeInpatient(Inpatient inpatient) {
        dao.purgeInpatient(inpatient);
    }
}