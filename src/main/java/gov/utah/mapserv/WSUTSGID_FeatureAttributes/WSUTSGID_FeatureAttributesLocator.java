/**
 * WSUTSGID_FeatureAttributesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.utah.mapserv.WSUTSGID_FeatureAttributes;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class WSUTSGID_FeatureAttributesLocator extends org.apache.axis.client.Service implements gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributes {

    public WSUTSGID_FeatureAttributesLocator() {
    }


    public WSUTSGID_FeatureAttributesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSUTSGID_FeatureAttributesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSUTSGID_FeatureAttributesSoap12
    private java.lang.String WSUTSGID_FeatureAttributesSoap12_address = "http://mapserv.utah.gov/WSUTSGID_FeatureAttributes/default.asmx";

    public java.lang.String getWSUTSGID_FeatureAttributesSoap12Address() {
        return WSUTSGID_FeatureAttributesSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSUTSGID_FeatureAttributesSoap12WSDDServiceName = "WSUTSGID_FeatureAttributesSoap12";

    public java.lang.String getWSUTSGID_FeatureAttributesSoap12WSDDServiceName() {
        return WSUTSGID_FeatureAttributesSoap12WSDDServiceName;
    }

    public void setWSUTSGID_FeatureAttributesSoap12WSDDServiceName(java.lang.String name) {
        WSUTSGID_FeatureAttributesSoap12WSDDServiceName = name;
    }

    public gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap getWSUTSGID_FeatureAttributesSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSUTSGID_FeatureAttributesSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSUTSGID_FeatureAttributesSoap12(endpoint);
    }

    public gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap getWSUTSGID_FeatureAttributesSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap12Stub _stub = new gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap12Stub(portAddress, this);
            _stub.setPortName(getWSUTSGID_FeatureAttributesSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSUTSGID_FeatureAttributesSoap12EndpointAddress(java.lang.String address) {
        WSUTSGID_FeatureAttributesSoap12_address = address;
    }


    // Use to get a proxy class for WSUTSGID_FeatureAttributesSoap
    private java.lang.String WSUTSGID_FeatureAttributesSoap_address = "http://mapserv.utah.gov/WSUTSGID_FeatureAttributes/default.asmx";

    public java.lang.String getWSUTSGID_FeatureAttributesSoapAddress() {
        return WSUTSGID_FeatureAttributesSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSUTSGID_FeatureAttributesSoapWSDDServiceName = "WSUTSGID_FeatureAttributesSoap";

    public java.lang.String getWSUTSGID_FeatureAttributesSoapWSDDServiceName() {
        return WSUTSGID_FeatureAttributesSoapWSDDServiceName;
    }

    public void setWSUTSGID_FeatureAttributesSoapWSDDServiceName(java.lang.String name) {
        WSUTSGID_FeatureAttributesSoapWSDDServiceName = name;
    }

    public gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap getWSUTSGID_FeatureAttributesSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSUTSGID_FeatureAttributesSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSUTSGID_FeatureAttributesSoap(endpoint);
    }

    public gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap getWSUTSGID_FeatureAttributesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoapStub _stub = new gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoapStub(portAddress, this);
            _stub.setPortName(getWSUTSGID_FeatureAttributesSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSUTSGID_FeatureAttributesSoapEndpointAddress(java.lang.String address) {
        WSUTSGID_FeatureAttributesSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap12Stub _stub = new gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap12Stub(new java.net.URL(WSUTSGID_FeatureAttributesSoap12_address), this);
                _stub.setPortName(getWSUTSGID_FeatureAttributesSoap12WSDDServiceName());
                return _stub;
            }
            if (gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoapStub _stub = new gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoapStub(new java.net.URL(WSUTSGID_FeatureAttributesSoap_address), this);
                _stub.setPortName(getWSUTSGID_FeatureAttributesSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSUTSGID_FeatureAttributesSoap12".equals(inputPortName)) {
            return getWSUTSGID_FeatureAttributesSoap12();
        }
        else if ("WSUTSGID_FeatureAttributesSoap".equals(inputPortName)) {
            return getWSUTSGID_FeatureAttributesSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "WSUTSGID_FeatureAttributes");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "WSUTSGID_FeatureAttributesSoap12"));
            ports.add(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "WSUTSGID_FeatureAttributesSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSUTSGID_FeatureAttributesSoap12".equals(portName)) {
            setWSUTSGID_FeatureAttributesSoap12EndpointAddress(address);
        }
        else 
if ("WSUTSGID_FeatureAttributesSoap".equals(portName)) {
            setWSUTSGID_FeatureAttributesSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
