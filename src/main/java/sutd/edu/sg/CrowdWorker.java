package sutd.edu.sg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CrowdWorker complex type�� Java �ࡣ
 * <p/>
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p/>
 * <pre>
 * &lt;complexType name="CrowdWorker">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="index" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="reliability" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="responseTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="selected" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrowdWorker", propOrder = {
        "cost",
        "index",
        "reliability",
        "responseTime",
        "selected"
})
public class CrowdWorker {

    protected Double cost;
    protected Integer index;
    protected Double reliability;
    protected Long responseTime;
    protected Boolean selected;

    /**
     * ��ȡcost���Ե�ֵ��
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getCost() {
        return cost;
    }

    /**
     * ����cost���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public CrowdWorker setCost(Double value) {
        this.cost = value;
        return this;
    }

    /**
     * ��ȡindex���Ե�ֵ��
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * ����index���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public CrowdWorker setIndex(Integer value) {
        this.index = value;
        return this;
    }

    /**
     * ��ȡreliability���Ե�ֵ��
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getReliability() {
        return reliability;
    }

    /**
     * ����reliability���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public CrowdWorker setReliability(Double value) {
        this.reliability = value;
        return this;
    }

    /**
     * ��ȡresponseTime���Ե�ֵ��
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getResponseTime() {
        return responseTime;
    }

    /**
     * ����responseTime���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public CrowdWorker setResponseTime(Long value) {
        this.responseTime = value;
        return this;
    }

    /**
     * ��ȡselected���Ե�ֵ��
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isSelected() {
        return selected;
    }

    /**
     * ����selected���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setSelected(Boolean value) {
        this.selected = value;
    }

}
