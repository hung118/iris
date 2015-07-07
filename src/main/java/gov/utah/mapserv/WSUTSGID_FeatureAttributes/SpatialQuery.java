/**
 * SpatialQuery.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.utah.mapserv.WSUTSGID_FeatureAttributes;

@SuppressWarnings({"serial", "rawtypes", "unused"})
public class SpatialQuery  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String SGIDLayerName;

    private java.lang.String[] attributeList;

    private double utmX;

    private double utmY;

    public SpatialQuery() {
    }

    public SpatialQuery(
           java.lang.String userName,
           java.lang.String SGIDLayerName,
           java.lang.String[] attributeList,
           double utmX,
           double utmY) {
           this.userName = userName;
           this.SGIDLayerName = SGIDLayerName;
           this.attributeList = attributeList;
           this.utmX = utmX;
           this.utmY = utmY;
    }


    /**
     * Gets the userName value for this SpatialQuery.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this SpatialQuery.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the SGIDLayerName value for this SpatialQuery.
     * 
     * @return SGIDLayerName
     */
    public java.lang.String getSGIDLayerName() {
        return SGIDLayerName;
    }


    /**
     * Sets the SGIDLayerName value for this SpatialQuery.
     * 
     * @param SGIDLayerName
     */
    public void setSGIDLayerName(java.lang.String SGIDLayerName) {
        this.SGIDLayerName = SGIDLayerName;
    }


    /**
     * Gets the attributeList value for this SpatialQuery.
     * 
     * @return attributeList
     */
    public java.lang.String[] getAttributeList() {
        return attributeList;
    }


    /**
     * Sets the attributeList value for this SpatialQuery.
     * 
     * @param attributeList
     */
    public void setAttributeList(java.lang.String[] attributeList) {
        this.attributeList = attributeList;
    }


    /**
     * Gets the utmX value for this SpatialQuery.
     * 
     * @return utmX
     */
    public double getUtmX() {
        return utmX;
    }


    /**
     * Sets the utmX value for this SpatialQuery.
     * 
     * @param utmX
     */
    public void setUtmX(double utmX) {
        this.utmX = utmX;
    }


    /**
     * Gets the utmY value for this SpatialQuery.
     * 
     * @return utmY
     */
    public double getUtmY() {
        return utmY;
    }


    /**
     * Sets the utmY value for this SpatialQuery.
     * 
     * @param utmY
     */
    public void setUtmY(double utmY) {
        this.utmY = utmY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SpatialQuery)) return false;
        SpatialQuery other = (SpatialQuery) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.SGIDLayerName==null && other.getSGIDLayerName()==null) || 
             (this.SGIDLayerName!=null &&
              this.SGIDLayerName.equals(other.getSGIDLayerName()))) &&
            ((this.attributeList==null && other.getAttributeList()==null) || 
             (this.attributeList!=null &&
              java.util.Arrays.equals(this.attributeList, other.getAttributeList()))) &&
            this.utmX == other.getUtmX() &&
            this.utmY == other.getUtmY();
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
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getSGIDLayerName() != null) {
            _hashCode += getSGIDLayerName().hashCode();
        }
        if (getAttributeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttributeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttributeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Double(getUtmX()).hashCode();
        _hashCode += new Double(getUtmY()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SpatialQuery.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", ">SpatialQuery"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "userName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SGIDLayerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "SGIDLayerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "attributeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("utmX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "utmX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("utmY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes", "utmY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
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
