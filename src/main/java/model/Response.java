package model;

import interfaces.XMLConverter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public final class Response implements XMLConverter {

    private String server = null;
    private Result[] result = null;

    public Response(){}

    public String getServer() {
        return server;
    }

    @XmlAttribute
    public void setServer(String server) {
        this.server = server;
    }

    public Result[] getResult() {
        return result;
    }

    @XmlElement(name = "result")
    public void setResult(Result[] result) {
        this.result = result;
    }

}
