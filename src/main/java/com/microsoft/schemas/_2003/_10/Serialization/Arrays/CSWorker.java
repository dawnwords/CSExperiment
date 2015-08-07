/**
 * ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9.java
 * <p/>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas._2003._10.Serialization.Arrays;

import sutd.edu.sg.CrowdWorker;

import java.io.Serializable;

public class CSWorker implements Serializable {
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
            new org.apache.axis.description.TypeDesc(CSWorker.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", ">ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9>KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("sg.edu.sutd", "CrowdWorker"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("sg.edu.sutd", "CrowdWorker"));
        typeDesc.addFieldDesc(elemField);
    }

    private String key;
    private CrowdWorker[] value;
    private Object __equalsCalc = null;
    private boolean __hashCodeCalc = false;


    public CSWorker() {
    }


    public CSWorker(String key, CrowdWorker[] value) {
        this.key = key;
        this.value = value;
    }

    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    public static org.apache.axis.encoding.Serializer getSerializer(
            String mechType,
            java.lang.Class _javaType,
            javax.xml.namespace.QName _xmlType) {
        return
                new org.apache.axis.encoding.ser.BeanSerializer(
                        _javaType, _xmlType, typeDesc);
    }

    public static org.apache.axis.encoding.Deserializer getDeserializer(
            String mechType,
            java.lang.Class _javaType,
            javax.xml.namespace.QName _xmlType) {
        return
                new org.apache.axis.encoding.ser.BeanDeserializer(
                        _javaType, _xmlType, typeDesc);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CrowdWorker[] getValue() {
        return value;
    }

    public void setValue(CrowdWorker[] value) {
        this.value = value;
    }

    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CSWorker)) return false;
        CSWorker other = (CSWorker) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = ((this.key == null && other.getKey() == null) ||
                (this.key != null &&
                        this.key.equals(other.getKey()))) &&
                ((this.value == null && other.getValue() == null) ||
                        (this.value != null &&
                                java.util.Arrays.equals(this.value, other.getValue())));
        __equalsCalc = null;
        return _equals;
    }

    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getValue() != null) {
            for (int i = 0;
                 i < java.lang.reflect.Array.getLength(getValue());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getValue(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    @Override
    public String toString() {
        return "CSWorker{" +
                "key='" + key + '\'' +
                ", value= CrowdWorker[" + value.length +
                "]}";
    }
}
