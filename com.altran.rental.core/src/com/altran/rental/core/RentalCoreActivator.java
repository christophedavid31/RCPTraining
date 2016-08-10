package com.altran.rental.core;

import javax.inject.Inject;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class RentalCoreActivator implements BundleActivator {

	private static BundleContext context;
		
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Inject
	public void start(BundleContext bundleContext) throws Exception {
		RentalCoreActivator.context = bundleContext;
		
		// Recup code p108
		Bundle e4Bundle = Platform.getBundle("org.eclipse.e4.ui.workbench");
		if (e4Bundle != null)
		{
			BundleContext e4BundleContext = e4Bundle.getBundleContext();
			IEclipseContext osgiCtx = EclipseContextFactory.getServiceContext(e4BundleContext);
			osgiCtx.set("myOSGiKey","Hello OSGi");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		RentalCoreActivator.context = null;
	}

}
