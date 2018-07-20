package model;

import service.DateAdapterService;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

public final class Result {

    private Long id = null;
    private Integer code = null;
    private Integer state = null;
    private Integer substate = null;
    private Integer as_final = null;
    private Integer trans = null;
    private Integer service = null;
    private Integer sum = null;
    private Integer commission = null;
    private Integer sum_prov = null;
    private Date server_time = null;
    //not found this field in the documentation
    private Date transdate = null;
    private Attribute[] attribute = null;

    public Result(){}

    public Date getTransdate() {
        return transdate;
    }

    @XmlAttribute(name = "transdate")
    @XmlJavaTypeAdapter(DateAdapterService.class)
    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public Attribute[] getAttribute() {
        return attribute;
    }

    @XmlElement
    public void setAttribute(Attribute[] attribute) {
        this.attribute = attribute;
    }

    public Integer getCode() {
        return code;
    }

    @XmlAttribute
    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getSum_prov() {
        return sum_prov;
    }

    @XmlAttribute
    public void setSum_prov(Integer sum_prov) {
        this.sum_prov = sum_prov;
    }

    public Date getServer_time() {
        return server_time;
    }

    @XmlAttribute(name = "server_time")
    @XmlJavaTypeAdapter(DateAdapterService.class)
    public void setServer_time(Date server_time) {
        this.server_time = server_time;
    }

    public Integer getCommission() {
        return commission;
    }

    @XmlAttribute
    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getSum() {
        return sum;
    }

    @XmlAttribute
    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getService() {
        return service;
    }

    @XmlAttribute
    public void setService(Integer service) {
        this.service = service;
    }

    public Integer getTrans() {
        return trans;
    }

    @XmlAttribute
    public void setTrans(Integer trans) {
        this.trans = trans;
    }

    public Integer getAs_final() {
        return as_final;
    }

    @XmlAttribute(name = "final")
    public void setAs_final(Integer as_final) {
        this.as_final = as_final;
    }

    public Integer getSubstate() {
        return substate;
    }

    @XmlAttribute
    public void setSubstate(Integer substate) {
        this.substate = substate;
    }

    public Integer getState() {
        return state;
    }

    @XmlAttribute
    public void setState(Integer state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }
}
