
package sutd.edu.sg;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList;
import com.microsoft.schemas._2003._10.serialization.arrays.CSResultNumList;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="globalTimeConstraint" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="globalCostConstraint" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="workers" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9" minOccurs="0"/>
 *         &lt;element name="resultNums" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfKeyValueOfstringint" minOccurs="0"/>
 *         &lt;element name="numOfGeneration" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xml",
    "globalTimeConstraint",
    "globalCostConstraint",
    "workers",
    "resultNums",
    "numOfGeneration"
})
@XmlRootElement(name = "GlobalOptimize")
public class GlobalOptimize {

    @XmlElementRef(name = "xml", namespace = "sg.edu.sutd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xml;
    protected Long globalTimeConstraint;
    protected Double globalCostConstraint;
    @XmlElementRef(name = "workers", namespace = "sg.edu.sutd", type = JAXBElement.class, required = false)
    protected JAXBElement<CSWorkerList> workers;
    @XmlElementRef(name = "resultNums", namespace = "sg.edu.sutd", type = JAXBElement.class, required = false)
    protected JAXBElement<CSResultNumList> resultNums;
    protected Integer numOfGeneration;

    /**
     * 获取xml属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXml() {
        return xml;
    }

    /**
     * 设置xml属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXml(JAXBElement<String> value) {
        this.xml = value;
    }

    /**
     * 获取globalTimeConstraint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGlobalTimeConstraint() {
        return globalTimeConstraint;
    }

    /**
     * 设置globalTimeConstraint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGlobalTimeConstraint(Long value) {
        this.globalTimeConstraint = value;
    }

    /**
     * 获取globalCostConstraint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGlobalCostConstraint() {
        return globalCostConstraint;
    }

    /**
     * 设置globalCostConstraint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGlobalCostConstraint(Double value) {
        this.globalCostConstraint = value;
    }

    /**
     * 获取workers属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CSWorkerList }{@code >}
     *     
     */
    public JAXBElement<CSWorkerList> getWorkers() {
        return workers;
    }

    /**
     * 设置workers属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CSWorkerList }{@code >}
     *     
     */
    public void setWorkers(JAXBElement<CSWorkerList> value) {
        this.workers = value;
    }

    /**
     * 获取resultNums属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CSResultNumList }{@code >}
     *     
     */
    public JAXBElement<CSResultNumList> getResultNums() {
        return resultNums;
    }

    /**
     * 设置resultNums属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CSResultNumList }{@code >}
     *     
     */
    public void setResultNums(JAXBElement<CSResultNumList> value) {
        this.resultNums = value;
    }

    /**
     * 获取numOfGeneration属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumOfGeneration() {
        return numOfGeneration;
    }

    /**
     * 设置numOfGeneration属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumOfGeneration(Integer value) {
        this.numOfGeneration = value;
    }

}
