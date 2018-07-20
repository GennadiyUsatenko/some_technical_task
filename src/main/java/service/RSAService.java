package service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

@Service
public final class RSAService {

    private static final String publicKeyName = "public.pem";
    private static final String privateKeyName = "private.pem";
    private static final String headOfPublicKey = "-----BEGIN PUBLIC KEY-----";
    private static final String footerOfPublicKey = "-----END PUBLIC KEY-----";
    private static final String headOfPrivateKey = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String footerOfPrivateKey = "-----END RSA PRIVATE KEY-----";
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    public RSAService(){
        try {
            setPrivateKey(privateKeyName);
            setPublicKey(publicKeyName);
        }catch (Exception e){ e.printStackTrace(); }
    }

    private void setPrivateKey(String fileName) throws IOException, GeneralSecurityException {
        String privateKeyPEM = getKeyFromFile(fileName);
        setPrivateKeyFromKeyString(privateKeyPEM);
    }

    private void setPublicKey(String fileName) throws IOException, GeneralSecurityException {
        String publicKeyPEM = getKeyFromFile(fileName);
        setPublicKeyFromKeyString(publicKeyPEM);
    }

    private void setPrivateKeyFromKeyString(String key) throws IOException, GeneralSecurityException {
        byte[] encoded = Base64.decodeBase64(key);

        //pkcs#1 format that needed to be in pkcs#8
        try (ASN1InputStream asn1InputStream = new ASN1InputStream(encoded)) {
            DERObject rsaPrivateKey = asn1InputStream.readObject();
            encoded = new PrivateKeyInfo(
                    new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption), rsaPrivateKey)
                    .getDEREncoded();
        }

        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        privateKey = kf.generatePrivate(keySpec);
    }

    private void setPublicKeyFromKeyString(String key) throws IOException, GeneralSecurityException {
        byte[] encoded = Base64.decodeBase64(key);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(encoded);
        publicKey = kf.generatePublic(keySpecX509);
    }

    private String getKeyFromFile(String fileName) throws IOException {
        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(!line.equals(headOfPublicKey) && !line.equals(footerOfPublicKey)
                        && !line.equals(headOfPrivateKey) && !line.equals(footerOfPrivateKey)){
                    result.append(line);
                }
            }

            scanner.close();

        } catch (IOException e) { throw new IOException(); }

        return result.toString();
    }

    /**
     * Подписывает строку
     * @param message - Строка для подписи
     * @return подпись в Base64

     */
    public String sign(String message) throws SignatureException {
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initSign(privateKey);
            sign.update(message.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(sign.sign()),"UTF-8");
        } catch (Exception ex) {
            throw new SignatureException(ex);
        }
    }

    /**
     * Проверяет подпись
     * @param message строка для проверки
     * @param signature подись в Base64
     * @return true если подпись верна
     * @throws java.security.SignatureException

     */
    public boolean verify(String message, String signature) throws SignatureException{
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initVerify(publicKey);
            sign.update(message.getBytes("UTF-8"));
            return sign.verify(Base64.decodeBase64(signature.getBytes("UTF-8")));
        } catch (Exception ex) {
            throw new SignatureException(ex);
        }
    }
}
