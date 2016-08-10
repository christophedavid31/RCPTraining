 
package com.altran.rental.ui.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.altran.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalPropertyView {
	
	public static final String VIEW_ID="com.altran.renatl.e4.ui.views.rentalView";
	
	private Label rentedObjectLabel, customerNameLabel;

	private Label fromDate;

	private Label toDate;
	
	public void setRental(Rental r){
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerNameLabel.setText(r.getCustomer().getDisplayName());
		fromDate.setText( r.getStartDate().toString());
		toDate.setText( r.getEndDate().toString());
	}
	
	
	@Inject
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
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(1));
	}
	
	
	
	@Focus
	public void onFocus() {
		
	}
}