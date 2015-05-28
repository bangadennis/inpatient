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
package org.openmrs.module.inpatient.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.inpatient.Inpatient;
import org.openmrs.module.inpatient.api.db.InpatientDAO;

import java.util.List;

/**
 * It is a default implementation of  {@link InpatientDAO}.
 */
public class HibernateInpatientDAO implements InpatientDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }

	@Override
	public List<Inpatient> getAllInpatient() {
		return sessionFactory.getCurrentSession().createCriteria(Inpatient.class).list();
	}

	@Override
	public Inpatient getInpatient(Integer inpatientId) {
		return (Inpatient) sessionFactory.getCurrentSession().get(Inpatient.class, inpatientId);
	}

	@Override
	public Inpatient saveInpatient(Inpatient inpatient) {
		sessionFactory.getCurrentSession().save(inpatient);
		return inpatient;
	}

	@Override
	public void purgeInpatient(Inpatient inpatient) {
		sessionFactory.getCurrentSession().delete(inpatient);

	}
}