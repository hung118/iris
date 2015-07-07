/**
 * UTSGID_GeoLocatorLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.utah.mapserv.WSUTSGID_Geolocator;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class UTSGID_GeoLocatorLocator extends org.apache.axis.client.Service implements gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocator {

    public UTSGID_GeoLocatorLocator() {
    }


    public UTSGID_GeoLocatorLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UTSGID_GeoLocatorLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UTSGID_GeoLocatorSoap
    private java.lang.String UTSGID_GeoLocatorSoap_address = "http://mapserv.utah.gov/WSUTSGID_Geolocator/Default.asmx";

    public java.lang.String getUTSGID_GeoLocatorSoapAddress() {
        return UTSGID_GeoLocatorSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UTSGID_GeoLocatorSoapWSDDServiceName = "UTSGID_GeoLocatorSoap";

    public java.lang.String getUTSGID_GeoLocatorSoapWSDDServiceName() {
        return UTSGID_GeoLocatorSoapWSDDServiceName;
    }

    public void setUTSGID_GeoLocatorSoapWSDDServiceName(java.lang.String name) {
        UTSGID_GeoLocatorSoapWSDDServiceName = name;
    }

    public gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap getUTSGID_GeoLocatorSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UTSGID_GeoLocatorSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUTSGID_GeoLocatorSoap(endpoint);
    }

    public gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap getUTSGID_GeoLocatorSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoapStub _stub = new gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoapStub(portAddress, this);
            _stub.setPortName(getUTSGID_GeoLocatorSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUTSGID_GeoLocatorSoapEndpointAddress(java.lang.String address) {
        UTSGID_GeoLocatorSoap_address = address;
    }


    // Use to get a proxy class for UTSGID_GeoLocatorSoap12
    private java.lang.String UTSGID_GeoLocatorSoap12_address = "http://mapserv.utah.gov/WSUTSGID_Geolocator/Default.asmx";

    public java.lang.String getUTSGID_GeoLocatorSoap12Address() {
        return UTSGID_GeoLocatorSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UTSGID_GeoLocatorSoap12WSDDServiceName = "UTSGID_GeoLocatorSoap12";

    public java.lang.String getUTSGID_GeoLocatorSoap12WSDDServiceName() {
        return UTSGID_GeoLocatorSoap12WSDDServiceName;
    }

    public void setUTSGID_GeoLocatorSoap12WSDDServiceName(java.lang.String name) {
        UTSGID_GeoLocatorSoap12WSDDServiceName = name;
    }

    public gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap getUTSGID_GeoLocatorSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UTSGID_GeoLocatorSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUTSGID_GeoLocatorSoap12(endpoint);
    }

    public gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap getUTSGID_GeoLocatorSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap12Stub _stub = new gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap12Stub(portAddress, this);
            _stub.setPortName(getUTSGID_GeoLocatorSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUTSGID_GeoLocatorSoap12EndpointAddress(java.lang.String address) {
        UTSGID_GeoLocatorSoap12_address = address;
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
            if (gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoapStub _stub = new gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoapStub(new java.net.URL(UTSGID_GeoLocatorSoap_address), this);
                _stub.setPortName(getUTSGID_GeoLocatorSoapWSDDServiceName());
                return _stub;
            }
            if (gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap12Stub _stub = new gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap12Stub(new java.net.URL(UTSGID_GeoLocatorSoap12_address), this);
                _stub.setPortName(getUTSGID_GeoLocatorSoap12WSDDServiceName());
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
        if ("UTSGID_GeoLocatorSoap".equals(inputPortName)) {
            return getUTSGID_GeoLocatorSoap();
        }
        else if ("UTSGID_GeoLocatorSoap12".equals(inputPortName)) {
            return getUTSGID_GeoLocatorSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "UTSGID_GeoLocator");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "UTSGID_GeoLocatorSoap"));
            ports.add(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "UTSGID_GeoLocatorSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UTSGID_GeoLocatorSoap".equals(portName)) {
            setUTSGID_GeoLocatorSoapEndpointAddress(address);
        }
        else 
if ("UTSGID_GeoLocatorSoap12".equals(portName)) {
            setUTSGID_GeoLocatorSoap12EndpointAddress(address);
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
