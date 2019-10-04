package com.capgemini.go.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.capgemini.go.presentationLayer.GoInteractiveUserInterface;;

/**
 * 
 * @author Kunal Roychoudhury
 * 
 *         To use the log, use syntax: GoLog.logger.debug(<string>)
 *         GoLog.logger.error(<string>) \n
 * 
 *         This logger can be accessed by any function in any layer.
 */
public class GoLog {
	public static final Logger logger = LogManager.getLogger(GoInteractiveUserInterface.class);

	private GoLog() {

	}
}
