/*******************************************************************************
 * Copyright (c) 2012 Jarosław Przygódzki
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package net.jarekprzygodzki.egc.actions;

import groovy.ui.Console;
import net.jarekprzygodzki.egc.ConsoleHelper;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class ShowGroovyConsoleAction implements IWorkbenchWindowActionDelegate {

	private Console console;

	@Override
	public void run(IAction action) {
		console = ConsoleHelper.showConsole();
	}

	@Override
	public void dispose() {
		console.exit();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}
	
	@Override
	public void init(IWorkbenchWindow window) {
	}
}