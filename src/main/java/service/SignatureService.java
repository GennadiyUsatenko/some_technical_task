package service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public final class SignatureService {

    private static Map<String, SignatureStore> mapStore = null;
    private static String currentStep = null;

    public SignatureService(){
        if(mapStore == null){
            mapStore = new HashMap<>();
            mapStore.put("verify", new SignatureStore());
            mapStore.put("payment", new SignatureStore());
            mapStore.put("status", new SignatureStore());
        }
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public SignatureStore getCurrent(){
        return mapStore.get(currentStep);
    }

    public void setCurrent(String signature){
        mapStore.get(currentStep).init(signature);
    }

    public SignatureStore getByStep(String step){
        return mapStore.get(step);
    }

    public final class SignatureStore{

        private String requestSignature = null;
        private String responseSignature = null;

        public void init(String signature){
            if(requestSignature == null){
                requestSignature = signature;
            }else {
                responseSignature = signature;
            }
        }

        public String getResponseSignature() {
            return responseSignature;
        }

        public String getRequestSignature() {
            return requestSignature;
        }

    }

}
