 
package com.altran.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.altran.rental.ui.RentalProvider;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyView {
	@Inject
	public RentalAgencyView() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency a, IEclipseContext context) {
		TreeViewer tv = new TreeViewer(parent);
		
		Collection<RentalAgency> agencies = new ArrayList<>();
		agencies.add(a);
		
		
		RentalProvider p = ContextInjectionFactory.make(RentalProvider.class, context);
		tv.setContentProvider(p);
		tv.setLabelProvider(p);
		
		tv.setInput(agencies);
		tv.expandAll();
	}
	
	
	
	@Focus
	public void onFocus() {
		
	}
	
	
}