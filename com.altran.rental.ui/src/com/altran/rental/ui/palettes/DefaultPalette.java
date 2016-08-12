package com.altran.rental.ui.palettes;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.altran.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class DefaultPalette implements IColorProvider, RentalUIConstants {
	
	@Inject
	@Named(RENTAL_UI_PREF_STORE) IPreferenceStore store;

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
					
		if ( element instanceof Customer)
		{
			return getAColor( store.getString(PREF_CUSTOMER_COLOR) );
		}
		else if ( element instanceof Rental)
		{
			return getAColor( store.getString(PREF_RENTAL_COLOR) );
		}
		else if ( element instanceof RentalObject)
		{
			return getAColor( store.getString(PREF_RENTAL_OBJECT_COLOR) );
		}
		else
			return null;
	}
	
	private Color getAColor(String rgbKey){
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		
		Color col = colorRegistry.get(rgbKey);
		
		if (col==null)
		{
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		return col;
	}

	@Override
	public Color getBackground(Object element) {
		return null;
	}

}
