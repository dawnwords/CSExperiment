
package com.microsoft.schemas._2003._10.serialization.arrays;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.schemas._2003._10.serialization.arrays package. 
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

    private final static QName _ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9");
    private final static QName _ArrayOfKeyValueOfstringint_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfKeyValueOfstringint");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.schemas._2003._10.serialization.arrays
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CSResultNumList }
     * 
     */
    public CSResultNumList createArrayOfKeyValueOfstringint() {
        return new CSResultNumList();
    }

    /**
     * Create an instance of {@link CSWorkerList }
     * 
     */
    public CSWorkerList createArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9() {
        return new CSWorkerList();
    }

    /**
     * Create an instance of {@link CSResultNumList.CSResultNum }
     * 
     */
    public CSResultNumList.CSResultNum createArrayOfKeyValueOfstringintKeyValueOfstringint() {
        return new CSResultNumList.CSResultNum();
    }

    /**
     * Create an instance of {@link CSWorkerList .KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9 }
     * 
     */
    public CSWorkerList.CSWorker createArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9() {
        return new CSWorkerList.CSWorker();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CSWorkerList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9")
    public JAXBElement<CSWorkerList> createArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(CSWorkerList value) {
        return new JAXBElement<CSWorkerList>(_ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9_QNAME, CSWorkerList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CSResultNumList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfKeyValueOfstringint")
    public JAXBElement<CSResultNumList> createArrayOfKeyValueOfstringint(CSResultNumList value) {
        return new JAXBElement<CSResultNumList>(_ArrayOfKeyValueOfstringint_QNAME, CSResultNumList.class, null, value);
    }

}
