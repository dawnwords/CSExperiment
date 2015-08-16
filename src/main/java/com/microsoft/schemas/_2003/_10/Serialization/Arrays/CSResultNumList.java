package com.microsoft.schemas._2003._10.serialization.arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfKeyValueOfstringint complex type的 Java 类。
 * <p/>
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p/>
 * <pre>
 * &lt;complexType name="ArrayOfKeyValueOfstringint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyValueOfstringint" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "CSResultNumList", propOrder = {
        "CSResultNum"
})
public class CSResultNumList {

    @XmlElement(name = "KeyValueOfstringint")
    protected List<CSResultNum> CSResultNum;

    /**
     * Gets the value of the keyValueOfstringint property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyValueOfstringint property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyValueOfstringint().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link CSResultNum }
     */
    public List<CSResultNum> getCSResultNum() {
        if (CSResultNum == null) {
            CSResultNum = new ArrayList<>();
        }
        return this.CSResultNum;
    }

    public CSResultNumList add(CSResultNum resultNum) {
        if (CSResultNum == null) {
            CSResultNum = new ArrayList<>();
        }
        CSResultNum.add(resultNum);
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
     *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    public static class CSResultNum {

        @XmlElement(name = "Key", required = true, nillable = true)
        protected String key;
        @XmlElement(name = "Value")
        protected int value;

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
        public CSResultNum setKey(String value) {
            this.key = value;
            return this;
        }

        /**
         * 获取value属性的值。
         */
        public int getValue() {
            return value;
        }

        /**
         * 设置value属性的值。
         */
        public CSResultNum setValue(int value) {
            this.value = value;
            return this;
        }

    }

}
