package sutd.edu.sg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CrowdWorker complex type的 Java 类。
 * <p/>
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取cost属性的值。
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getCost() {
        return cost;
    }

    /**
     * 设置cost属性的值。
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public CrowdWorker setCost(Double value) {
        this.cost = value;
        return this;
    }

    /**
     * 获取index属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 设置index属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public CrowdWorker setIndex(Integer value) {
        this.index = value;
        return this;
    }

    /**
     * 获取reliability属性的值。
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getReliability() {
        return reliability;
    }

    /**
     * 设置reliability属性的值。
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public CrowdWorker setReliability(Double value) {
        this.reliability = value;
        return this;
    }

    /**
     * 获取responseTime属性的值。
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getResponseTime() {
        return responseTime;
    }

    /**
     * 设置responseTime属性的值。
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public CrowdWorker setResponseTime(Long value) {
        this.responseTime = value;
        return this;
    }

    /**
     * 获取selected属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isSelected() {
        return selected;
    }

    /**
     * 设置selected属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setSelected(Boolean value) {
        this.selected = value;
    }

}
