package sutd.edu.sg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfCrowdWorker complex type的 Java 类。
 * <p/>
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p/>
 * <pre>
 * &lt;complexType name="ArrayOfCrowdWorker">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CrowdWorker" type="{sg.edu.sutd}CrowdWorker" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCrowdWorker", propOrder = {
        "crowdWorker"
})
public class ArrayOfCrowdWorker {

    @XmlElement(name = "CrowdWorker", nillable = true)
    protected List<CrowdWorker> crowdWorker;

    /**
     * Gets the value of the crowdWorker property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crowdWorker property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrowdWorker().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link CrowdWorker }
     */
    public List<CrowdWorker> getCrowdWorker() {
        if (crowdWorker == null) {
            crowdWorker = new ArrayList<CrowdWorker>();
        }
        return this.crowdWorker;
    }

    public ArrayOfCrowdWorker setCrowdWorker(List<CrowdWorker> crowdWorker) {
        this.crowdWorker = crowdWorker;
        return this;
    }
}
