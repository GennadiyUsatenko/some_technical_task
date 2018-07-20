package main;


import model.Request;
import model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.HttpsConnectorService;
import service.RSAService;
import service.SignatureService;

@Component
public final class Main {

    private Request request = null;
    private Response response = null;
    @Autowired
    private RSAService rsaService;
    @Autowired
    private SignatureService signatureService;
    @Autowired
    private HttpsConnectorService httpsConnectorService;

    public void doTask(){
        try{
            // Step number 1 - VERIFY :
            signatureService.setCurrentStep("verify");
            //generate test request for this step
            request = Request.testVerify();
            doStep("Step number 1 - VERIFY :");

            // Step number 2 - PAYMENT :
            signatureService.setCurrentStep("payment");
            //generate test request for this step
            request = Request.testPayment();
            doStep("Step number 2 - PAYMENT :");

            // Step number 3 - STATUS :
            signatureService.setCurrentStep("status");
            //generate test request for this step
            request = Request.testStatus();
            doStep("Step number 3 - STATUS :");

        }catch (Exception e){ e.printStackTrace(); }
    }

    private void doStep(String stepName){
        try {
            //generate and set request signature
            signatureService.setCurrent( rsaService.sign( request.toXMLString() ) );
            //print current step to console
            request.printRequestStep(
                    signatureService.getCurrent().getRequestSignature(),
                    stepName
            );
            //send https request to leo server and generate response
            response = httpsConnectorService.sendPost(request);
            //print current step to console
            response.printResponseStep( signatureService.getCurrent().getResponseSignature() );
            //I don't know why this check verify doesn't work
            rsaService.verify( response.toXMLString(), signatureService.getCurrent().getResponseSignature() );

        }catch (Exception e){ e.printStackTrace(); }
    }

}
