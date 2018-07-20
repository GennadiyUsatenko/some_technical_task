package model;


import service.DateAdapterService;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

public final class Payment {

    private Long id = null;
    private Integer sum = null;
    private Integer check = null;
    private Integer service = null;
    private String account = null;
    private Date date = null;
    private Attribute[] attribute = null;

    public Payment(){}

    public Payment(Long id, Integer sum, Integer check, Integer service, String account, Date date){
        this.setId(id);
        this.setSum(sum);
        this.setCheck(check);
        this.setService(service);
        this.setAccount(account);
        this.setDate(date);
    }

    public Payment(Long id, Integer sum, Integer check, Integer service, String account, Date date, Attribute[] attribute){
        this.setId(id);
        this.setSum(sum);
        this.setCheck(check);
        this.setService(service);
        this.setAccount(account);
        this.setDate(date);
        this.setAttribute(attribute);
    }

    public Attribute[] getAttribute() {
        return attribute;
    }

    @XmlElement
    public void setAttribute(Attribute[] attribute) {
        this.attribute = attribute;
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSum() {
        return sum;
    }

    @XmlAttribute
    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getCheck() {
        return check;
    }

    @XmlAttribute
    public void setCheck(Integer check) {
        this.check = check;
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

    public Date getDate() {
        return date;
    }

    @XmlAttribute
    @XmlJavaTypeAdapter(DateAdapterService.class)
    public void setDate(Date date) {
        this.date = date;
    }
}
