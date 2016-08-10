package com.altran.rental.ui;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

	
	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if (inputElement instanceof Collection<?>) 
			return ((Collection<?>) inputElement).toArray();
		else
			return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RentalAgency)
			return ((RentalAgency) parentElement).getCustomers().toArray();
		else
			return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return true;
	}
	
	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency)
			return ((RentalAgency) element).getName();
		else if (element instanceof Customer)
			return ((Customer) element).getDisplayName();
		else
			return super.getText(element);
	}

	

}
