/*******************************************************************************
 * Copyright (c) 2013 Jarosław Przygódzki
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package net.jarekprzygodzki.egc.editors;

import groovy.lang.Binding;
import groovy.ui.Console;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JApplet;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.jarekprzygodzki.egc.GroovyConsolePlugin;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class GroovyConsoleEditor extends EditorPart {

	public static final String ID = "net.jarekprzygodzki.egc.editors.GroovyConsoleEditorId";

	private Console console;

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if (!(input instanceof GroovyConsoleEditorInput)) {
			throw new PartInitException("Wrong input");
		}
		initLnF();
		setSite(site);
		setInput(input);
		setPartName("Groovy Console");
		Bundle bundle = GroovyConsolePlugin.getDefault().getBundle();
		BundleContext bundleContext = bundle.getBundleContext();
		Binding binding = new Binding();
		binding.setVariable("bundleContext", bundleContext);
		console = new Console(binding);

	}

	private static void initLnF() throws PartInitException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			throw new PartInitException("Error Setting Look and Feel", e);
		} catch (InstantiationException e) {
			throw new PartInitException("Error Setting Look and Feel", e);
		} catch (IllegalAccessException e) {
			throw new PartInitException("Error Setting Look and Feel", e);
		} catch (UnsupportedLookAndFeelException e) {
			throw new PartInitException("Error Setting Look and Feel", e);
		}
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.EMBEDDED
				| SWT.NO_BACKGROUND);
		java.awt.Frame frame = SWT_AWT.new_Frame(composite);
		Panel panel = new Panel(new BorderLayout()) {
			public void update(java.awt.Graphics g) {
				/* Do not erase the background */
				paint(g);
			}
		};
		frame.add(panel);
		JApplet applet = new JApplet();
		applet.setSize(800, 600);
		panel.add(applet);
		applet.init();
		applet.start();
		console.run(applet);
	}

	@Override
	public void setFocus() {

	}

}
