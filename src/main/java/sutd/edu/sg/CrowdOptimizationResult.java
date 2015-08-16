
package sutd.edu.sg;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList;


/**
 * <p>CrowdOptimizationResult complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡcrowdServiceSelection���Ե�ֵ��
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
     * ����crowdServiceSelection���Ե�ֵ��
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
     * ��ȡtotalReliability���Ե�ֵ��
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
     * ����totalReliability���Ե�ֵ��
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
