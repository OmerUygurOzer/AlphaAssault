package level.objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 1/30/2016.
 */
public abstract class ObjectBase {

    protected Map<String,Object> attributes = new HashMap<String, Object>();

    public void addAttribute(String id, Object attribute){
        attributes.put(id,attribute);
    }

    public Object getAttribute(String id){
        return attributes.get(id);
    }

    public Map<String,Object> getAttributes(){return attributes;}
}
