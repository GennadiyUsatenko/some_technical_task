package service;

import model.Request;
import model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.JAXBContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public final class HttpsConnectorService {

    private static final String LEO_URL = "https://test.lgaming.net/external/extended";
    @Autowired
    SignatureService signatureService;

    public Response sendPost(Request request){
        HttpsURLConnection conn = null;
        try{
            URL url = new URL(LEO_URL);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("PayLogic-Signature", signatureService.getCurrent().getRequestSignature());
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setDoOutput(true);

            //need to add xml body to request
            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            jaxbContext.createMarshaller().marshal(request, conn.getOutputStream());
            //need to read signature from response header
            signatureService.setCurrent(conn.getHeaderField("PayLogic-Signature"));
            //need to read xml from response
            jaxbContext = JAXBContext.newInstance(Response.class);
            Response response = (Response)jaxbContext.createUnmarshaller().unmarshal(conn.getInputStream());
            conn.disconnect();

            return response;
        }catch (Exception e){
            conn.disconnect();
            e.printStackTrace();
            return null;
        }
    }

    public Response sendTestPost(Request request){
        HttpsURLConnection conn = null;
        try{
            URL url = new URL(LEO_URL);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("PayLogic-Signature", signatureService.getCurrent().getRequestSignature());
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setDoOutput(true);
            //need to add xml body to request
            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            jaxbContext.createMarshaller().marshal(request, conn.getOutputStream());
            //need to read signature from response header
            signatureService.setCurrent(conn.getHeaderField("PayLogic-Signature"));
            //need to read xml from response

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();
            return null;
        }catch (Exception e){
            conn.disconnect();
            e.printStackTrace();
            return null;
        }
    }
}
