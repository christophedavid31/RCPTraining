package com.altran.rental.ui.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.altran.rental.ui.RentalUIConstants;
import com.opcoach.e4.preferences.ScopedPreferenceStore;

public class RentalAbstractPreferenceInitializer extends AbstractPreferenceInitializer implements RentalUIConstants {

	public RentalAbstractPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store;
		
		store = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
		
		store.setDefault(PREF_PALETTE, "com.altran.rental.ui.palette1");
		
		store.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(255,0,0)));
		store.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(new RGB(0,0,255)));
		store.setDefault(PREF_RENTAL_OBJECT_COLOR, StringConverter.asString(new RGB(0,255,0)));
	}

}
