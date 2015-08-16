
package sutd.edu.sg;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList;


/**
 * <p>CrowdOptimizationResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CrowdOptimizationResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crowdServiceSelection" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9" minOccurs="0"/>
 *         &lt;element name="totalReliability" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrowdOptimizationResult", propOrder = {
    "crowdServiceSelection",
    "totalReliability"
})
public class CrowdOptimizationResult {

    @XmlElementRef(name = "crowdServiceSelection", namespace = "sg.edu.sutd", type = JAXBElement.class, required = false)
    protected JAXBElement<CSWorkerList> crowdServiceSelection;
    protected Double totalReliability;

    /**
     * 获取crowdServiceSelection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CSWorkerList }{@code >}
     *     
     */
    public JAXBElement<CSWorkerList> getCrowdServiceSelection() {
        return crowdServiceSelection;
    }

    /**
     * 设置crowdServiceSelection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CSWorkerList }{@code >}
     *     
     */
    public void setCrowdServiceSelection(JAXBElement<CSWorkerList> value) {
        this.crowdServiceSelection = value;
    }

    /**
     * 获取totalReliability属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalReliability() {
        return totalReliability;
    }

    /**
     * 设置totalReliability属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalReliability(Double value) {
        this.totalReliability = value;
    }

}
