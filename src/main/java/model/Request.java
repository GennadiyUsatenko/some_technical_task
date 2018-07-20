package model;

import interfaces.XMLConverter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "request")
public final class Request implements XMLConverter {

    private Integer point = null;
    private Verify verify = null;
    private Payment[] payment = null;
    private Status[] status = null;

    public Request(){}

    public Request(Integer point, Verify verify){
        this.setPoint(point);
        this.setVerify(verify);
    }

    public Request(Integer point, Payment[] payment){
        this.setPoint(point);
        this.setPayment(payment);
    }

    public Request(Integer point, Status[] status){
        this.setPoint(point);
        this.setStatus(status);
    }

    public static Request testVerify(){
        Attribute a1 = new Attribute("email", "info@rol.ru");
        Attribute a2 = new Attribute("fio", "ivanov");
        Verify verify = new Verify(4390, "12345", new Attribute[]{a1,a2});
        Request request = new Request(327, verify);

        return request;
    }

    public static Request testPayment(){
        Attribute a1 = new Attribute("email", "info@rol.ru");
        Attribute a2 = new Attribute("fio", "ivanov");
        Payment p1 = new Payment(14546l, 1000, 17235, 4390, "12345", new Date());
        Payment p2 = new Payment(14547l, 1000, 17235, 4390, "12345", new Date(), new Attribute[]{a1,a2});
        Request request = new Request(327, new Payment[]{p1,p2});

        return request;
    }

    public static Request testStatus(){
        Status s1 = new Status(123l);
        Status s2 = new Status(125l);
        Request request = new Request(327, new Status[]{s1,s2});

        return request;
    }

    public Payment[] getPayment() {
        return payment;
    }

    @XmlElement
    public void setPayment(Payment[] payment) {
        this.payment = payment;
    }

    public Status[] getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(Status[] status) {
        this.status = status;
    }

    public Integer getPoint() {
        return point;
    }

    @XmlAttribute
    public void setPoint(Integer point) {
        this.point = point;
    }

    public Verify getVerify() {
        return verify;
    }

    @XmlElement
    public void setVerify(Verify verify) {
        this.verify = verify;
    }
}
