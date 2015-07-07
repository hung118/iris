/**
 * UTSGID_GeoLocatorSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.utah.mapserv.WSUTSGID_Geolocator;

public interface UTSGID_GeoLocatorSoap extends java.rmi.Remote {
    public gov.utah.mapserv.WSUTSGID_Geolocator.GeocodeResult geocodeAddress(java.lang.String userName, java.lang.String streetAddress, java.lang.String zipCodeOrCity) throws java.rmi.RemoteException;
}
