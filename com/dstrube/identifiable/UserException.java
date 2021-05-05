package com.dstrube.identifiable;

/**
from
http://jcs.mobile-utopia.com/jcs/1130_UserException.java
*/

public abstract class UserException extends java.lang.Exception implements /*org.omg.CORBA.portable.*/IDLEntity {

    /**
     * Constructs a <code>UserException</code> object.
     * This method is called only by subclasses.
     */
    protected UserException() {
		super();
    }

    /**
     * Constructs a <code>UserException</code> object with a
     * detail message. This method is called only by subclasses.
     *
     * @param reason a <code>String</code> object giving the reason for this
     *         exception
     */
    protected UserException(String reason) {
		super(reason);
    }
}