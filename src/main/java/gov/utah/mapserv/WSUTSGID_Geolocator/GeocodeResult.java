/**
 * GeocodeResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.utah.mapserv.WSUTSGID_Geolocator;

@SuppressWarnings({"serial", "unused", "rawtypes"})
public class GeocodeResult  implements java.io.Serializable {
    private java.lang.String matchAddress;

    private java.lang.String geocoder;

    private double score;

    private double UTM_X;

    private double UTM_Y;

    private double LONG_X;

    private double LAT_Y;

    public GeocodeResult() {
    }

    public GeocodeResult(
           java.lang.String matchAddress,
           java.lang.String geocoder,
           double score,
           double UTM_X,
           double UTM_Y,
           double LONG_X,
           double LAT_Y) {
           this.matchAddress = matchAddress;
           this.geocoder = geocoder;
           this.score = score;
           this.UTM_X = UTM_X;
           this.UTM_Y = UTM_Y;
           this.LONG_X = LONG_X;
           this.LAT_Y = LAT_Y;
    }


    /**
     * Gets the matchAddress value for this GeocodeResult.
     * 
     * @return matchAddress
     */
    public java.lang.String getMatchAddress() {
        return matchAddress;
    }


    /**
     * Sets the matchAddress value for this GeocodeResult.
     * 
     * @param matchAddress
     */
    public void setMatchAddress(java.lang.String matchAddress) {
        this.matchAddress = matchAddress;
    }


    /**
     * Gets the geocoder value for this GeocodeResult.
     * 
     * @return geocoder
     */
    public java.lang.String getGeocoder() {
        return geocoder;
    }


    /**
     * Sets the geocoder value for this GeocodeResult.
     * 
     * @param geocoder
     */
    public void setGeocoder(java.lang.String geocoder) {
        this.geocoder = geocoder;
    }


    /**
     * Gets the score value for this GeocodeResult.
     * 
     * @return score
     */
    public double getScore() {
        return score;
    }


    /**
     * Sets the score value for this GeocodeResult.
     * 
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
    }


    /**
     * Gets the UTM_X value for this GeocodeResult.
     * 
     * @return UTM_X
     */
    public double getUTM_X() {
        return UTM_X;
    }


    /**
     * Sets the UTM_X value for this GeocodeResult.
     * 
     * @param UTM_X
     */
    public void setUTM_X(double UTM_X) {
        this.UTM_X = UTM_X;
    }


    /**
     * Gets the UTM_Y value for this GeocodeResult.
     * 
     * @return UTM_Y
     */
    public double getUTM_Y() {
        return UTM_Y;
    }


    /**
     * Sets the UTM_Y value for this GeocodeResult.
     * 
     * @param UTM_Y
     */
    public void setUTM_Y(double UTM_Y) {
        this.UTM_Y = UTM_Y;
    }


    /**
     * Gets the LONG_X value for this GeocodeResult.
     * 
     * @return LONG_X
     */
    public double getLONG_X() {
        return LONG_X;
    }


    /**
     * Sets the LONG_X value for this GeocodeResult.
     * 
     * @param LONG_X
     */
    public void setLONG_X(double LONG_X) {
        this.LONG_X = LONG_X;
    }


    /**
     * Gets the LAT_Y value for this GeocodeResult.
     * 
     * @return LAT_Y
     */
    public double getLAT_Y() {
        return LAT_Y;
    }


    /**
     * Sets the LAT_Y value for this GeocodeResult.
     * 
     * @param LAT_Y
     */
    public void setLAT_Y(double LAT_Y) {
        this.LAT_Y = LAT_Y;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GeocodeResult)) return false;
        GeocodeResult other = (GeocodeResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.matchAddress==null && other.getMatchAddress()==null) || 
             (this.matchAddress!=null &&
              this.matchAddress.equals(other.getMatchAddress()))) &&
            ((this.geocoder==null && other.getGeocoder()==null) || 
             (this.geocoder!=null &&
              this.geocoder.equals(other.getGeocoder()))) &&
            this.score == other.getScore() &&
            this.UTM_X == other.getUTM_X() &&
            this.UTM_Y == other.getUTM_Y() &&
            this.LONG_X == other.getLONG_X() &&
            this.LAT_Y == other.getLAT_Y();
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
        if (getMatchAddress() != null) {
            _hashCode += getMatchAddress().hashCode();
        }
        if (getGeocoder() != null) {
            _hashCode += getGeocoder().hashCode();
        }
        _hashCode += new Double(getScore()).hashCode();
        _hashCode += new Double(getUTM_X()).hashCode();
        _hashCode += new Double(getUTM_Y()).hashCode();
        _hashCode += new Double(getLONG_X()).hashCode();
        _hashCode += new Double(getLAT_Y()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GeocodeResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "GeocodeResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "MatchAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("geocoder");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "Geocoder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UTM_X");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "UTM_X"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UTM_Y");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "UTM_Y"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LONG_X");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "LONG_X"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LAT_Y");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mapserv.utah.gov/WSUTSGID_Geolocator/", "LAT_Y"));
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
