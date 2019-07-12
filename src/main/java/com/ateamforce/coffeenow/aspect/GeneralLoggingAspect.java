package com.ateamforce.coffeenow.aspect;

/**
 *
 * @author Sakel
 */
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class GeneralLoggingAspect {

    private static final Logger LOGGER = Logger.getLogger(System.class);
    
    @Pointcut("execution(* print*(..)) && args(str)")
    public void log(String str) {
        LOGGER.info(str);
    }

}
