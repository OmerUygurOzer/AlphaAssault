package utilities;

/**
 * Created by Omer on 3/8/2016.
 */
public interface Packable {
    void pack();
    void unpack(Packable packable);

    void write(String name);
    void read(String name);
}
