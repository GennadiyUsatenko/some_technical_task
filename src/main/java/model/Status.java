package model;


import javax.xml.bind.annotation.XmlAttribute;

public final class Status {

    private Long id = null;

    public Status(){}

    public Status(Long id){
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }
}
