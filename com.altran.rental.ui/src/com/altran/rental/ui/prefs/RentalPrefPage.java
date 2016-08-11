package com.altran.rental.ui.prefs;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;

import com.altran.rental.ui.RentalUIConstants;

public class RentalPrefPage extends FieldEditorPreferencePage implements RentalUIConstants {

	public RentalPrefPage() {
		super(GRID);
	}

	@Override
	protected void createFieldEditors() {
		
		addField( new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Customer : ", getFieldEditorParent()));
		addField( new ColorFieldEditor(PREF_RENTAL_COLOR, "Rental : ", getFieldEditorParent()));
		addField( new ColorFieldEditor(PREF_RENTAL_OBJECT_COLOR, "Objects : ", getFieldEditorParent()));

	}

}
