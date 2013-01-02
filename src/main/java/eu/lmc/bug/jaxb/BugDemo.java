package eu.lmc.bug.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * Test JAXB unmarshaller bug.
 */
public class BugDemo {

    private final String filename;
    private final Boolean nilA;
    private final Boolean nilB;

    public BugDemo(String filename, Boolean nilA, Boolean nilB) {
        this.filename = filename;
        this.nilA = nilA;
        this.nilB = nilB;
    }

    public void run() throws JAXBException {
        System.out.println("loading: " + filename);
        InputStream inputStream = getClass().getResourceAsStream(filename);
        if (inputStream == null)
            throw new IllegalStateException("resource " + filename + " missing");
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final Object o = unmarshaller.unmarshal(inputStream);
        System.out.printf("resource `%s` unmarshalled to %s%n", filename, o.getClass());
        final ElementBug bug = (ElementBug) o;
        check("A", bug.getA(), nilA);
        check("B", bug.getB(), nilB);
    }

    private String formatNil(Boolean nil) {
        return nil == null ? "missing" : (nil ? "nil" : "element");
    }

    private void check(String name, JAXBElement<?> element, Boolean expectedNil) {
        final Boolean realNil = element == null ? null : element.isNil();
        System.out.printf("  %s is %-7s, expected %-7s", name,
                formatNil(realNil),
                formatNil(expectedNil)
        );
        if (realNil != expectedNil) {
            System.out.println(" ERROR!");
        } else {
            System.out.println(" (OK)");
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    static JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance(ElementBug.class, ElementA.class, ElementB.class);
        } catch (JAXBException e) {
            throw new IllegalStateException("jaxb initialization failed", e);
        }
    }


    public static void main(String[] args) throws JAXBException {
        new BugDemo("ok1.xml", true, true).run();
        new BugDemo("ok2.xml", false, false).run();
        new BugDemo("ok3.xml", false, true).run();
        new BugDemo("incorrect1.xml", true, false).run();
    }

}
