 
package com.altran.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.altran.rental.ui.RentalProvider;
import com.altran.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyView implements RentalUIConstants{
	
	private TreeViewer tv;
	@Inject
	public RentalAgencyView() {
		
	}
	
	@Inject
	private ESelectionService selectionService;  
	
	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency a, IEclipseContext context, EMenuService menuService) {
		tv = new TreeViewer(parent);
		
		Collection<RentalAgency> agencies = new ArrayList<>();
		agencies.add(a);
		
		RentalProvider p = ContextInjectionFactory.make(RentalProvider.class, context);
		tv.setContentProvider(p);
		tv.setLabelProvider(p);
		
		tv.setInput(agencies);
		tv.expandAll();
		
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				selectionService.setSelection(sel.size()==1 ? sel.getFirstElement() : sel.toArray());
				
			}
		});
		
		menuService.registerContextMenu(tv.getControl(), "com.altran.rental.eap.popupmenu.displaymessage");

	}
	
	@Inject
	public void changeCustomerColor(@Preference(PREF_CUSTOMER_COLOR) String rgbKey1, @Preference(PREF_RENTAL_COLOR) String rgbKey2, @Preference(PREF_RENTAL_OBJECT_COLOR) String rgbKey3 ){
		if ( tv != null && ! tv.getControl().isDisposed())
			tv.refresh();
	}
	
	@Inject
	public void changeCustomerColor(@Preference(PREF_PALETTE) String pal ){
		if ( tv != null && ! tv.getControl().isDisposed())
			tv.refresh();
	}
	
	
	@Focus
	public void onFocus() {
		
	}
	
	
}