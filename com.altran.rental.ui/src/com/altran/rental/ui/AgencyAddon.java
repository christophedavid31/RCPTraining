 
package com.altran.rental.ui;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;

public class AgencyAddon {

	@Inject
	public void applicationStarsted(IEclipseContext context) {		
		RentalAgency a = RentalAgencyGenerator.createSampleAgency();
		context.set(RentalAgency.class, a);
	}

}
