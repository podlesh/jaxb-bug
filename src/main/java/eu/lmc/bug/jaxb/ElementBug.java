package eu.lmc.bug.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;

/**
 * Element containning two nillable subelements.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bug")
@XmlType
public class ElementBug {

    @XmlElementRef(name = "A", type = JAXBElement.class)
    private JAXBElement<ElementA> a;
    @XmlElementRef(name = "B", type = JAXBElement.class)
    private JAXBElement<ElementB> b;

    public JAXBElement<ElementA> getA() {
        return a;
    }

    public void setA(JAXBElement<ElementA> a) {
        this.a = a;
    }

    public JAXBElement<ElementB> getB() {
        return b;
    }

    public void setB(JAXBElement<ElementB> b) {
        this.b = b;
    }
}
