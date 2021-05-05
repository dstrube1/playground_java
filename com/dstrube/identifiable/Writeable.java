package com.dstrube.identifiable;

/**
from
http://jcs.mobile-utopia.com/jcs/501_Writeable.java

todo?:
http://jcs.mobile-utopia.com/jcs/342_WriteContents.java
http://jcs.mobile-utopia.com/jcs/2395_OutputStream.java
http://jcs.mobile-utopia.com/jcs/9413_OutputStream.java
http://jcs.mobile-utopia.com/jcs/1101_Principal.java
http://jcs.mobile-utopia.com/jcs/28766_Any.java
http://jcs.mobile-utopia.com/jcs/3582_EncapsulationUtility.java
*/

//import org.omg.CORBA_2_3.portable.OutputStream ;

/** This interface represents an entity that can be written to an OutputStream.
 * @author Ken Cavanaugh
 */
public interface Writeable 
{
    /** Write this object directly to the output stream.
     */
    void write(OutputStream arg0);
}