package com.dstrube.identifiable;

/**
from
http://jcs.mobile-utopia.com/jcs/848_Bounds.java
 */
 
/**
 * A user exception thrown when a parameter is not within
 * the legal bounds for the object that a method is trying
 * to access.
 *
 * @see <A href="../../../../guide/idl/jidlExceptions.html">documentation on
 * Java IDL exceptions</A>
 */

public final class Bounds extends /*org.omg.CORBA.*/UserException {

    /**
     * Constructs an <code>Bounds</code> with no specified detail message. 
     */
    public Bounds() {
		super();
    }

    /**
     * Constructs an <code>Bounds</code> with the specified detail message. 
     *
     * @param   reason   the detail message.
     */
    public Bounds(String reason) {
		super(reason);
    }
}