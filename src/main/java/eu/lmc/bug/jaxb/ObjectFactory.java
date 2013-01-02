package eu.lmc.bug.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.namespace.QName;

public class ObjectFactory {

    @XmlElementDecl(namespace = "", name = "A", scope = ElementBug.class)
    public JAXBElement<ElementA> createElementA(ElementA value) {
        return new JAXBElement<ElementA>(new QName("", "A"), ElementA.class, ElementBug.class, value);
    }

    @XmlElementDecl(namespace = "", name = "B", scope = ElementBug.class)
    public JAXBElement<ElementB> createElementB(ElementB value) {
        return new JAXBElement<ElementB>(new QName("", "B"), ElementB.class, ElementBug.class, value);
    }

}
