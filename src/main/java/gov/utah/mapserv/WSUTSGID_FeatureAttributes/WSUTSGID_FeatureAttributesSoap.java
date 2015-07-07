/**
 * WSUTSGID_FeatureAttributesSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.utah.mapserv.WSUTSGID_FeatureAttributes;

public interface WSUTSGID_FeatureAttributesSoap extends java.rmi.Remote {

    /**
     * returns feature attributes based on a spatial query (point
     * in polygon).
     */
    public gov.utah.mapserv.WSUTSGID_FeatureAttributes.SpatialQueryResponse getFeatureAttributes(gov.utah.mapserv.WSUTSGID_FeatureAttributes.SpatialQuery parameters) throws java.rmi.RemoteException;

    /**
     * Returns feature attributes based on an attribute query
     */
    public gov.utah.mapserv.WSUTSGID_FeatureAttributes.AttributeQueryResponse getFeatureAttributes(gov.utah.mapserv.WSUTSGID_FeatureAttributes.AttributeQuery parameters) throws java.rmi.RemoteException;
}
