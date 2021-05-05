package com.dstrube.identifiable;

/**
from
http://jcs.mobile-utopia.com/jcs/560_IDLEntity.java
*/

/**
 * An interface with no members whose only purpose is to serve as a marker 
 * indicating  that an implementing class is a
 * Java value type from IDL that has a corresponding Helper class.
 * RMI IIOP serialization looks for such a marker to perform
 * marshalling/unmarshalling. 
 **/
public interface IDLEntity extends java.io.Serializable {

}