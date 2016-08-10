 
package com.altran.rental.ui.views;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;

import com.altran.rental.ui.RentalProvider;
import com.opcoach.training.rental.RentalAgency;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TreeViewer;

public class RentalAgencyView {
	@Inject
	public RentalAgencyView() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency a) {
		TreeViewer tv = new TreeViewer(parent);
		
		Collection<RentalAgency> agencies = new ArrayList<>();
		agencies.add(a);
		
		RentalProvider p = new RentalProvider();
		tv.setContentProvider(p);
		tv.setLabelProvider(p);
		
		tv.setInput(agencies);
		tv.expandAll();
	}
	
	
	
	@Focus
	public void onFocus() {
		
	}
	
	
}