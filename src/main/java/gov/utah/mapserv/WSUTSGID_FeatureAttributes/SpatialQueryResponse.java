/**
 * SpatialQueryResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.utah.mapserv.WSUTSGID_FeatureAttributes;

@SuppressWarnings({"serial", "rawtypes", "unused"})
public class SpatialQueryResponse  implements java.io.Serializable {
    private gov.utah.mapserv.WSUTSGID_FeatureAttributes.Attribute[] spatialQueryResult;

    public SpatialQueryResponse() {
    }

    public SpatialQueryResponse(
           gov.utah.mapserv.WSUTSGID_FeatureAttributes.Attribute[] spatialQueryResult) {
           this.spatialQueryResult = spatialQueryResult;
    }


    /**
     * Gets the spatialQueryResult value for this SpatialQueryResponse.
     * 
     * @return spatialQueryResult
     */
    public gov.utah.mapserv.WSUTSGID_FeatureAttributes.Attribute[] getSpatialQueryResult() {
        return spatialQueryResult;
    }


    /**
     * Sets the spatialQueryResult value for this SpatialQueryResponse.
     * 
     * @param spatialQueryResult
     */
    public void setSpatialQueryResult(gov.utah.mapserv.WSUTSGID_FeatureAttributes.Attribute[] spatialQueryResult) {
        this.spatialQueryResult = spatialQueryResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SpatialQueryResponse)) return false;
        SpatialQueryResponse other = (SpatialQueryResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.spatialQueryResult==null && other.getSpatialQueryResult()==null) || 
             (this.spatialQueryResult!=null &&
              java.util.Arrays.equals(this.spatialQueryResult, other.getSpatialQueryResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getSpatialQueryResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSpatialQueryResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSpatialQueryResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SpatialQueryResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", ">SpatialQueryResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spatialQueryResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "SpatialQueryResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "Attribute"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "Attribute"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
