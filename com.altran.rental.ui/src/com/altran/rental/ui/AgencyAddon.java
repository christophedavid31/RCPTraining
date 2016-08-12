 
package com.altran.rental.ui;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.altran.rental.ui.palettes.Palette;
import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;

public class AgencyAddon implements RentalUIConstants{

	private Map<String, Palette> palettes;

	@PostConstruct
	public void applicationStarsted(IEclipseContext context, IExtensionRegistry reg) {		
		RentalAgency a = RentalAgencyGenerator.createSampleAgency();
		context.set(RentalAgency.class, a);
		context.set(RENTAL_UI_IMG_REGISTRY, getLocalImageRegistry());
		ScopedPreferenceStore store = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
		context.set(RENTAL_UI_PREF_STORE, store);
		palettes = readPalettes(reg, context);
		context.set(PALETTE_MANAGER, palettes);

		String idPalette = store.getString(PREF_PALETTE);
		Palette pal = palettes.get(idPalette);
		
		context.set(Palette.class, pal );
		
	}
	
	@Inject Logger log;
	
	
	private Map<String, Palette> readPalettes(IExtensionRegistry reg, IEclipseContext c){
		
		Map<String, Palette> palettes = new HashMap<String, Palette>();
		for (IConfigurationElement elt : reg.getConfigurationElementsFor("com.altran.rental.ui.palette"))
		{
			try
			{
				Palette p = new Palette();
				p.setId(elt.getAttribute("id"));
				p.setName(elt.getAttribute("name"));
			
				Bundle b = Platform.getBundle(elt.getNamespaceIdentifier());
				Class<?> clazz = b.loadClass( elt.getAttribute("paletteClass"));
				IColorProvider pal = (IColorProvider) ContextInjectionFactory.make(clazz, c);
				p.setProvider(pal);
				palettes.put(p.getId(), p);
			} catch (ClassNotFoundException ex)
			{
				log.error("Unable to create the class" );
			}
			
		}
		
		return palettes;
	}
	
	
	ImageRegistry getLocalImageRegistry()
	{
		// Get the bundle using the universal method to get it from the current class
		Bundle b = FrameworkUtil.getBundle(getClass());  
		
		// Create a local image registry
		ImageRegistry reg = new ImageRegistry();

		// Then fill the values...
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));

		return reg;
	}

	@Inject
	private void showAdapterExtensions(IExtensionRegistry reg){
		for (IConfigurationElement elt : reg.getConfigurationElementsFor("org.eclipse.core.runtime.adapters"))
		{
			String pluginName = elt.getNamespaceIdentifier();
			String adapterName = elt.getAttribute("class");
			System.out.printf("Plugin : %-50s Adapter : %s\n", pluginName, adapterName);
		}
		
	}
	
	@Inject
	public void changePalette(@Preference(PREF_PALETTE) String pal, IEclipseContext ctx ){
		if (palettes != null)
			ctx.set(Palette.class, palettes.get(pal));
	}

}
