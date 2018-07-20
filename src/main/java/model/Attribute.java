package model;


import javax.xml.bind.annotation.XmlAttribute;

public final class Attribute {

    private String name = null;
    private String value = null;

    public Attribute(){}

    public Attribute(String name, String value){
        this.setName(name);
        this.setValue(value);
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    @XmlAttribute
    public void setValue(String value) {
        this.value = value;
    }

}
