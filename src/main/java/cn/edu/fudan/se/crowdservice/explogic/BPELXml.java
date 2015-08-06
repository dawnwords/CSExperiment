package cn.edu.fudan.se.crowdservice.explogic;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class BPELXml {
    public static final String CS1_NAME = "service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService";
    public static final String CS2_NAME = "service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService";

    public static final String CS1_CS2 =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<process name=\"Intermediary\"\n" +
                    "xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"\n" +
                    "targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"\n" +
                    "xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"\n" +
                    "xmlns:bpel=\"http://ultraBpel/\">  \n" +
                    "<sequence>\n" +
                    "\t<receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"BuySecondhandItem\" variable=\"var\"  createInstance=\"yes\"/>\n" +
                    "\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"10;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
                    "\t<flow>\n" +
                    "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
                    "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\t\t\n" +
                    "\t</flow>\n" +
                    "\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
                    "\t<if ext:ifProb=\"0.5;0.5\">\n" +
                    "\t\t<condition>ContinueYes</condition>\n" +
                    "\t\t<sequence>\n" +
                    "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
                    "\t\t<if ext:ifProb=\"0.5;0.5\">\n" +
                    "\t\t\t<condition>PriceOK</condition>\n" +
                    "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
                    "\t\t\t<else>\n" +
                    "\t\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"0;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
                    "\t\t\t</else>\n" +
                    "\t\t</if>\n" +
                    "\t\t</sequence>\n" +
                    "\t\t<else>\n" +
                    "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"0;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
                    "\t\t</else>\n" +
                    "\t</if>\t\n" +
                    "\t<reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"BuySecondhandItem\" variable=\"result\"/>\n" +
                    "</sequence>\n" +
                    "</process>";

    public static final String CS1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<process name=\"Intermediary\"\n" +
            "xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"\n" +
            "targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"\n" +
            "xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"\n" +
            "xmlns:bpel=\"http://ultraBpel/\">  \n" +
            "<sequence>\n" +
            "    <receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"BuySecondhandItem\" variable=\"var\"  createInstance=\"yes\"/>\n" +
            "       <invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "    <reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"BuySecondhandItem\" variable=\"result\"/>\n" +
            "</sequence>\n" +
            "</process>";

    public static final String CS2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<process name=\"Intermediary\"\n" +
            "xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"\n" +
            "targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"\n" +
            "xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"\n" +
            "xmlns:bpel=\"http://ultraBpel/\">  \n" +
            "<sequence>\n" +
            "    <receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"BuySecondhandItem\" variable=\"var\"  createInstance=\"yes\"/>\n" +
            "        <invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "    <reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"BuySecondhandItem\" variable=\"result\"/>\n" +
            "</sequence>\n" +
            "</process>";
}
