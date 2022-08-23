package vimfun.updater;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javax.xml.bind.JAXBElement;

import org.docx4j.wml.ContentAccessor;

/**
 * @author vimfun
 * @date 19/8/2022 6:53 PM
 */
public class Docx {

    public static Object removeElemsOfType(Object obj, Class toSearch) {
        return removeElems(obj, c -> c.getClass().equals(toSearch));
    }

    public static Object removeElemsOfTypes(Object obj, Collection<Class> cs) {
        return removeElems(obj, x -> cs.stream()
                .anyMatch(c -> x.getClass().equals(c)));
    }

    public static Object removeElems(Object obj, Predicate predicate) {
        if (obj instanceof ContentAccessor) {
            List cs = ((ContentAccessor) obj).getContent();
            Iterator ci = cs.iterator();
            while (ci.hasNext()) {
                Object c = ci.next();
                if (c instanceof JAXBElement)
                    c = ((JAXBElement) c).getValue();

                if (predicate.test(c)) {
                    ci.remove();
                } else {
                    removeElems(c, predicate);
                }
            }
        }
        return obj;
    }
}
