package net.jarekprzygodzki.egc;

import groovy.lang.Binding;
import groovy.ui.Console;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class ConsoleHelper {

	private final Console console;

	private boolean done;

	private ConsoleHelper(Console console) {
		this.console = console;
	}

	private void waitFor() {
		JFrame f = (JFrame) console.getFrame();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				synchronized (ConsoleHelper.this) {
					done = true;
					ConsoleHelper.this.notifyAll();
				}
			}

		});
		synchronized (ConsoleHelper.this) {
			while (!done) {
				try {
					ConsoleHelper.this.wait();
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static void waitFor(Console console) {
		new ConsoleHelper(console).waitFor();
	}

	public static Console showConsole(BundleContext bundleContext) {
		Binding binding = new Binding();
		binding.setVariable("bundleContext", bundleContext); //$NON-NLS-1$
		Console console = new Console(binding);
		console.run();
		return console;
	}

	public static Console showConsole() {
		Bundle bundle = GroovyConsolePlugin.getDefault().getBundle();
		BundleContext bundleContext = bundle.getBundleContext();
		return ConsoleHelper.showConsole(bundleContext);
	}
}
