package com.altran.rental.ui.palettes;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class UglyPalette implements IColorProvider {

	public UglyPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
	}

	@Override
	public Color getBackground(Object element) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
	}

}
