package interfaces;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public interface XMLConverter {

    default String toXMLString(){
        try{
            StringWriter sw = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(this, sw);
            return sw.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    default void printPrettyXML(){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, System.out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    default void printRequestStep(String signature, String step){
        System.out.println("***********************************************************************");
        System.out.println(step);
        System.out.println("***********************************************************************");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("XML-Request:");
        System.out.println("-----------------------------------------------------------------------");
        printPrettyXML();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Signature-Request:");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(signature);
        System.out.println("-----------------------------------------------------------------------");
    }

    default void printResponseStep(String signature){
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("XML-Response:");
        System.out.println("-----------------------------------------------------------------------");
        printPrettyXML();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Signature-Response:");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(signature);
        System.out.println("-----------------------------------------------------------------------");
    }

}
