 
package com.altran.rental.ui.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPropertyView {
	
	public static final String VIEW_ID="com.altran.renatl.e4.ui.views.rentalView";
	
	private Label rentedObjectLabel, customerNameLabel;

	private Label fromDate;

	private Label toDate;
	
	@Inject
	private RentalAgency myAgency;
	
	@Inject @Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r ){
		if ( r != null)
			setRental(r);
	}
	
	public void setRental(Rental r){
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerNameLabel.setText(r.getCustomer().getDisplayName());
		fromDate.setText( r.getStartDate().toString());
		toDate.setText( r.getEndDate().toString());
	}
	
	
	public RentalPropertyView() {
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.BORDER);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Information");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		rentedObjectLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		Label rentedToLabel = new Label(infoGroup, SWT.BORDER);
		rentedToLabel.setText("Loué à");
		
		customerNameLabel = new Label(infoGroup, SWT.BORDER);
		
		Group dateGroup = new Group(parent, SWT.NONE);
		dateGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dateGroup.setText("Dates de location");
		dateGroup.setLayout(new GridLayout(2, false));
		
		Label lblDate = new Label(dateGroup, SWT.BORDER);
		lblDate.setText("du:");
		
		fromDate = new Label(dateGroup, SWT.BORDER);
		
		Label lblAu = new Label(dateGroup, SWT.BORDER);
		lblAu.setText("au:");
		
		toDate = new Label(dateGroup, SWT.BORDER);
		
		setRental(myAgency.getRentals().get(0));
	}
	
	
	
	@Focus
	public void onFocus() {
		
	}
}