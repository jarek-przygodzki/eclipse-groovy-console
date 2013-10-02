/*******************************************************************************
 * Copyright (c) 2013 Jarosław Przygódzki
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package net.jarekprzygodzki.egc.editors.handler;

import net.jarekprzygodzki.egc.editors.GroovyConsoleEditor;
import net.jarekprzygodzki.egc.editors.GroovyConsoleEditorInput;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public class CallEditor extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage activePage = window.getActivePage();
		try {
			activePage.openEditor(new GroovyConsoleEditorInput(), GroovyConsoleEditor.ID);
		} catch (PartInitException e) {
			throw new ExecutionException(e.toString(), e);
		}
		return null;
	}

}
