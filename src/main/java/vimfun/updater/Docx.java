package vimfun.updater;

import javax.xml.bind.JAXBElement;
import java.util.Iterator;
import java.util.List;

import org.docx4j.wml.ContentAccessor;

/**
 * @author vimfun
 * @date 19/8/2022 6:53 PM
 */
public class Docx {
    public static void removeElemsOfType(Object obj, Class toSearch) {
        if (obj instanceof ContentAccessor) {
            List cs = ((ContentAccessor) obj).getContent();
            Iterator ci = cs.iterator();
            while (ci.hasNext()) {
                Object c = ci.next();
                if (c instanceof JAXBElement)
                    c = ((JAXBElement) c).getValue();

                if (c.getClass().equals(toSearch)){
                    ci.remove();
                } else {
                    removeElemsOfType(c, toSearch);
                }
            }
        }
    }
}
