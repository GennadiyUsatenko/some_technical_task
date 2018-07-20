package model;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public final class Verify {

    private Integer service = null;
    private String account = null;
    private Attribute[] attribute = null;

    public Verify(){}

    public Verify(Integer service, String account, Attribute[] attribute){
        this.setService(service);
        this.setAccount(account);
        this.setAttribute(attribute);
    }

    public Integer getService() {
        return service;
    }

    @XmlAttribute
    public void setService(Integer service) {
        this.service = service;
    }

    public String getAccount() {
        return account;
    }

    @XmlAttribute
    public void setAccount(String account) {
        this.account = account;
    }

    public Attribute[] getAttribute() {
        return attribute;
    }

    @XmlElement
    public void setAttribute(Attribute[] attribute) {
        this.attribute = attribute;
    }
}
