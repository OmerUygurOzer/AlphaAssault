package objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 1/27/2016.
 */
public abstract class ObjectBase {

    protected Map<String,Object> attributes;

    protected ObjectBase(){
        attributes = new HashMap<String, Object>();
    }

    public void addAttribute(String id, Object attribute){
        attributes.put(id,attribute);
    }

    public Object getAttribute(String id){
        return attributes.get(id);
    }

    public abstract void toFile(String path);
}
