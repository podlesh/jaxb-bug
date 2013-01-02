package eu.lmc.bug.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 */
@XmlType
public class ElementA {

    private String attr1;

    @XmlAttribute(name = "attr1")
    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }
}
