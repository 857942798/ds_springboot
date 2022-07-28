package com.ds.udep.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "echo", namespace = "http://ais.echo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Echo {

    @XmlAttribute(name = "value", required = true)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
