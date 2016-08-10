package com.altran.rental.ui;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider,IColorProvider, RentalUIConstants {

	
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
		if (parentElement instanceof RentalAgency) {
			RentalAgency a  = (RentalAgency) parentElement;
			return new Node[] {
					new Node(Node.CUSTOMERS,a),
					new Node(Node.LOCATIONS,a),
					new Node(Node.RENTAL_OBJECTS,a)
			};
		}
		else if (parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}
			return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof RentalAgency  || element instanceof Node;
	}
	
	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency)
			return ((RentalAgency) element).getName();
		else if (element instanceof Customer)
			return ((Customer) element).getDisplayName();
		else if (element instanceof RentalObject)
			return ((RentalObject) element).getName();
		else
			return super.getText(element);
	}

	class Node{
		public static final String RENTAL_OBJECTS = "Rental Objects";
		public static final String LOCATIONS = "Locations";
		public static final String CUSTOMERS = "Customers";
		private String label;
		private RentalAgency agency;
		
		public Node(String label, RentalAgency agency){
			super();
			this.label = label;
			this.agency = agency;
		}
		
		public Object[] getChildren(){
			if (label == CUSTOMERS)
				return agency.getCustomers().toArray();
			else if (label==RENTAL_OBJECTS)
				return agency.getObjectsToRent().toArray();
			else if (label==LOCATIONS)
				return agency.getRentals().toArray();
			else
				return null;
		}
		
		@Override
		public String toString() {
			return label;
		}
	}

	@Override
	public Color getForeground(Object element) {
		if ( element instanceof Customer)
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return null;
	}

	@Inject @Named(RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry registry;
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof Customer)
			return registry.get(IMG_CUSTOMER);
		else if (element instanceof RentalObject)
			return registry.get(IMG_RENTAL_OBJECT);
		else if (element instanceof Rental)
			return registry.get(IMG_RENTAL);
		
		return super.getImage(element);
	}
}
