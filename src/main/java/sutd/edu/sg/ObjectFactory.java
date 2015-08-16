
package sutd.edu.sg;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList;
import com.microsoft.schemas._2003._10.serialization.arrays.CSResultNumList;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sutd.edu.sg package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ArrayOfCrowdWorker_QNAME = new QName("sg.edu.sutd", "ArrayOfCrowdWorker");
    private final static QName _CrowdOptimizationResult_QNAME = new QName("sg.edu.sutd", "CrowdOptimizationResult");
    private final static QName _CrowdWorker_QNAME = new QName("sg.edu.sutd", "CrowdWorker");
    private final static QName _GlobalOptimizeWorkers_QNAME = new QName("sg.edu.sutd", "workers");
    private final static QName _GlobalOptimizeResultNums_QNAME = new QName("sg.edu.sutd", "resultNums");
    private final static QName _GlobalOptimizeXml_QNAME = new QName("sg.edu.sutd", "xml");
    private final static QName _GetVersionResponseGetVersionResult_QNAME = new QName("sg.edu.sutd", "GetVersionResult");
    private final static QName _GlobalOptimizeResponseGlobalOptimizeResult_QNAME = new QName("sg.edu.sutd", "GlobalOptimizeResult");
    private final static QName _CrowdOptimizationResultCrowdServiceSelection_QNAME = new QName("sg.edu.sutd", "crowdServiceSelection");
    private final static QName _GetDataResponseGetDataResult_QNAME = new QName("sg.edu.sutd", "GetDataResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sutd.edu.sg
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GlobalOptimize }
     * 
     */
    public GlobalOptimize createGlobalOptimize() {
        return new GlobalOptimize();
    }

    /**
     * Create an instance of {@link GlobalOptimizeResponse }
     * 
     */
    public GlobalOptimizeResponse createGlobalOptimizeResponse() {
        return new GlobalOptimizeResponse();
    }

    /**
     * Create an instance of {@link CrowdOptimizationResult }
     * 
     */
    public CrowdOptimizationResult createCrowdOptimizationResult() {
        return new CrowdOptimizationResult();
    }

    /**
     * Create an instance of {@link GetVersion }
     * 
     */
    public GetVersion createGetVersion() {
        return new GetVersion();
    }

    /**
     * Create an instance of {@link GetData }
     * 
     */
    public GetData createGetData() {
        return new GetData();
    }

    /**
     * Create an instance of {@link CrowdWorker }
     * 
     */
    public CrowdWorker createCrowdWorker() {
        return new CrowdWorker();
    }

    /**
     * Create an instance of {@link GetDataResponse }
     * 
     */
    public GetDataResponse createGetDataResponse() {
        return new GetDataResponse();
    }

    /**
     * Create an instance of {@link ArrayOfCrowdWorker }
     * 
     */
    public ArrayOfCrowdWorker createArrayOfCrowdWorker() {
        return new ArrayOfCrowdWorker();
    }

    /**
     * Create an instance of {@link GetVersionResponse }
     * 
     */
    public GetVersionResponse createGetVersionResponse() {
        return new GetVersionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCrowdWorker }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "ArrayOfCrowdWorker")
    public JAXBElement<ArrayOfCrowdWorker> createArrayOfCrowdWorker(ArrayOfCrowdWorker value) {
        return new JAXBElement<ArrayOfCrowdWorker>(_ArrayOfCrowdWorker_QNAME, ArrayOfCrowdWorker.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrowdOptimizationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "CrowdOptimizationResult")
    public JAXBElement<CrowdOptimizationResult> createCrowdOptimizationResult(CrowdOptimizationResult value) {
        return new JAXBElement<CrowdOptimizationResult>(_CrowdOptimizationResult_QNAME, CrowdOptimizationResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrowdWorker }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "CrowdWorker")
    public JAXBElement<CrowdWorker> createCrowdWorker(CrowdWorker value) {
        return new JAXBElement<CrowdWorker>(_CrowdWorker_QNAME, CrowdWorker.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CSWorkerList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "workers", scope = GlobalOptimize.class)
    public JAXBElement<CSWorkerList> createGlobalOptimizeWorkers(CSWorkerList value) {
        return new JAXBElement<CSWorkerList>(_GlobalOptimizeWorkers_QNAME, CSWorkerList.class, GlobalOptimize.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CSResultNumList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "resultNums", scope = GlobalOptimize.class)
    public JAXBElement<CSResultNumList> createGlobalOptimizeResultNums(CSResultNumList value) {
        return new JAXBElement<CSResultNumList>(_GlobalOptimizeResultNums_QNAME, CSResultNumList.class, GlobalOptimize.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "xml", scope = GlobalOptimize.class)
    public JAXBElement<String> createGlobalOptimizeXml(String value) {
        return new JAXBElement<String>(_GlobalOptimizeXml_QNAME, String.class, GlobalOptimize.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "GetVersionResult", scope = GetVersionResponse.class)
    public JAXBElement<String> createGetVersionResponseGetVersionResult(String value) {
        return new JAXBElement<String>(_GetVersionResponseGetVersionResult_QNAME, String.class, GetVersionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrowdOptimizationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "GlobalOptimizeResult", scope = GlobalOptimizeResponse.class)
    public JAXBElement<CrowdOptimizationResult> createGlobalOptimizeResponseGlobalOptimizeResult(CrowdOptimizationResult value) {
        return new JAXBElement<CrowdOptimizationResult>(_GlobalOptimizeResponseGlobalOptimizeResult_QNAME, CrowdOptimizationResult.class, GlobalOptimizeResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CSWorkerList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "crowdServiceSelection", scope = CrowdOptimizationResult.class)
    public JAXBElement<CSWorkerList> createCrowdOptimizationResultCrowdServiceSelection(CSWorkerList value) {
        return new JAXBElement<CSWorkerList>(_CrowdOptimizationResultCrowdServiceSelection_QNAME, CSWorkerList.class, CrowdOptimizationResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "sg.edu.sutd", name = "GetDataResult", scope = GetDataResponse.class)
    public JAXBElement<String> createGetDataResponseGetDataResult(String value) {
        return new JAXBElement<String>(_GetDataResponseGetDataResult_QNAME, String.class, GetDataResponse.class, value);
    }

}
