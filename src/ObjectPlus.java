import java.io.*;
import java.util.*;

public class ObjectPlus implements Serializable {

    public static Map<Class, List> extents = new HashMap<>();

    public ObjectPlus() {
        List extent = extents.get(this.getClass());
        if (extent == null) {
            extent = new ArrayList();
            extents.put(this.getClass(), extent);
        }
        extent.add(this);
    }

    public List<Object> getExtent() {
        List list = extents.get(this.getClass());
        return Collections.unmodifiableList(list);
    }

    public static List getExtent(Class clazz) {
        List list = extents.get(clazz);
        if (list == null) {
            return new ArrayList();
        }
        return Collections.unmodifiableList(list);
    }

    public static void saveExtent(ObjectOutputStream oos) throws IOException {
        oos.writeObject(extents);
    }

    public static void loadExtent(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        extents = (Map<Class, List>) ois.readObject();
    }

}
