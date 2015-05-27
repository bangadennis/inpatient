package org.openmrs.module.inpatient.api.db;

import org.openmrs.module.inpatient.Ward;

import java.util.List;

/**
 * Created by banga on 5/26/15.
 */
public interface WardDAO {

    List<Ward> getAllWards();

    Ward getWard(Integer wardId);

    Ward saveWard(Ward ward);

    void purgeWard(Ward ward);
}
