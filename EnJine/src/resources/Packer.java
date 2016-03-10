package resources;

/**
 * Created by Omer on 3/9/2016.
 */
public interface Packer {
    void pack();
    void unpack();
    ResourceData getPackedData();
}
