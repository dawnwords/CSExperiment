package com.microsoft.schemas._2003._10.serialization.arrays;

import sutd.edu.sg.ArrayOfCrowdWorker;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9 complex type的 Java 类。
 * <p/>
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p/>
 * <pre>
 * &lt;complexType name="ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Value" type="{sg.edu.sutd}ArrayOfCrowdWorker"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CSWorkerList", propOrder = {
        "CSWorker"
})
public class CSWorkerList {

    @XmlElement(name = "KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9")
    protected List<CSWorker> CSWorker;

    /**
     * Gets the value of the keyValueOfstringArrayOfCrowdWorker8Qgdyvm9 property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyValueOfstringArrayOfCrowdWorker8Qgdyvm9 property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link CSWorkerList .KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9 }
     */
    public List<CSWorker> getCSWorker() {
        if (CSWorker == null) {
            CSWorker = new ArrayList<>();
        }
        return this.CSWorker;
    }

    public CSWorkerList add(CSWorker worker) {
        if (CSWorker == null) {
            CSWorker = new ArrayList<>();
        }
        CSWorker.add(worker);
        return this;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * <p/>
     * <p>以下模式片段指定包含在此类中的预期内容。
     * <p/>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Value" type="{sg.edu.sutd}ArrayOfCrowdWorker"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "key",
            "value"
    })
    public static class CSWorker {

        @XmlElement(name = "Key", required = true, nillable = true)
        protected String key;
        @XmlElement(name = "Value", required = true, nillable = true)
        protected ArrayOfCrowdWorker value;

        /**
         * 获取key属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getKey() {
            return key;
        }

        /**
         * 设置key属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public CSWorker setKey(String value) {
            this.key = value;
            return this;
        }

        /**
         * 获取value属性的值。
         *
         * @return possible object is
         * {@link ArrayOfCrowdWorker }
         */
        public ArrayOfCrowdWorker getValue() {
            return value;
        }

        /**
         * 设置value属性的值。
         *
         * @param value allowed object is
         *              {@link ArrayOfCrowdWorker }
         */
        public CSWorker setValue(ArrayOfCrowdWorker value) {
            this.value = value;
            return this;
        }

    }

}
