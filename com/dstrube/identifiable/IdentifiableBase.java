package com.dstrube.identifiable;

/**
from
http://jcs.mobile-utopia.com/jcs/1169_IdentifiableBase.java 
*/

//import /*org.omg.CORBA_2_3.portable.*/OutputStream ;

//import /*com.sun.corba.se.spi.ior.*/Writeable ;
//import /*com.sun.corba.se.spi.ior.*/WriteContents ;
//import /*com.sun.corba.se.spi.orb.*/ORB ;

//import /*com.sun.corba.se.impl.ior.*/EncapsulationUtility ;

/** Provide support for properly reading and writing Identifiable objects
* that are also encapsulations (tagged profiles and components).
*/
public abstract class IdentifiableBase implements Identifiable,
    WriteContents
{
    /** Write the data for this object as a CDR encapsulation.
    * This is used for writing tagged components and profiles.
    * These data types must be written out as encapsulations,
    * which means that we need to first write the data out to
    * an encapsulation stream, then extract the data and write
    * it to os as an array of octets.
    * /
    final public void write( OutputStream os )
    {
		EncapsulationUtility.writeEncapsulation( (WriteContents)this, os ) ;
    }*/
}