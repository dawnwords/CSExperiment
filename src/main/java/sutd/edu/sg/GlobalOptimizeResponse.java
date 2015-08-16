
package sutd.edu.sg;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GlobalOptimizeResult" type="{sg.edu.sutd}CrowdOptimizationResult" minOccurs="0"/>
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
    "globalOptimizeResult"
})
@XmlRootElement(name = "GlobalOptimizeResponse")
public class GlobalOptimizeResponse {

    @XmlElementRef(name = "GlobalOptimizeResult", namespace = "sg.edu.sutd", type = JAXBElement.class, required = false)
    protected JAXBElement<CrowdOptimizationResult> globalOptimizeResult;

    /**
     * ��ȡglobalOptimizeResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CrowdOptimizationResult }{@code >}
     *     
     */
    public JAXBElement<CrowdOptimizationResult> getGlobalOptimizeResult() {
        return globalOptimizeResult;
    }

    /**
     * ����globalOptimizeResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CrowdOptimizationResult }{@code >}
     *     
     */
    public void setGlobalOptimizeResult(JAXBElement<CrowdOptimizationResult> value) {
        this.globalOptimizeResult = value;
    }

}
