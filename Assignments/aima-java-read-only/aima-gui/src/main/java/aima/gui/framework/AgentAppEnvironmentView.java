package aima.gui.framework;

import javax.swing.JComponent;

import aima.core.agent.Environment;
import aima.core.agent.EnvironmentView;

/**
 * Base class for all graphical environment view implementations.
 * An environment view visualizes agents in their environment.
 * Typically, 2D graphics will be used for visualization. Environment
 * changes are communicated to the viewer by means of an observer pattern.
 * 
 * @author R. Lunde
 */
public abstract class AgentAppEnvironmentView
extends JComponent implements EnvironmentView {
	/** The environment providing the data to be visualized. */
	protected Environment env;
	/** Message display is delegated to a separate logger. */
	protected MessageLogger logger;
	
	/** Sets the data source for the viewer. */
	public void setEnvironment(Environment env) {
		if (this.env != null)
			this.env.removeEnvironmentView(this);
		this.env = env;
		env.addEnvironmentView(this);
		repaint();
	}
	
	/** Selects a logger for message display. */
	public void setMessageLogger(MessageLogger logger) {
		this.logger = logger;
	}
	
	/** Forwards a given message to the selected message logger. */
	public void notify(String msg) {
		if (logger != null)
			logger.log(msg);
	}
}
