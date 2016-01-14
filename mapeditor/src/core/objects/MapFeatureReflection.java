package core.objects;

import core.fileIO.ByteIO;

/**
 * Created by Omer on 1/11/2016.
 */
public class MapFeatureReflection {
    public int variety_w;
    public int variety_h;
    public boolean destroyable;
    public boolean blocksMovement;
    public boolean blocksBullets;
    public boolean blocksDamage;
    public boolean blocksAerial;
    public int radius;
    public int width;
    public int height;
    public byte[] image;


    public static MapFeatureReflection readFromByteArray(byte[] _bytes){
        MapFeatureReflection mfr = new MapFeatureReflection();
        int indexPointer = 0;
        short header = ByteIO.readShort(_bytes,indexPointer);           indexPointer+=2;
        int typeSize = ByteIO.readInt(_bytes,indexPointer);             indexPointer+=4;
        String type = ByteIO.readString(_bytes,indexPointer,typeSize);  indexPointer+=typeSize;
        mfr.destroyable  = ByteIO.readBoolean(_bytes,indexPointer);     indexPointer++;
        mfr.blocksMovement = ByteIO.readBoolean(_bytes,indexPointer);   indexPointer++;
        mfr.blocksBullets = ByteIO.readBoolean(_bytes,indexPointer);    indexPointer++;
        mfr.blocksDamage = ByteIO.readBoolean(_bytes,indexPointer);     indexPointer++;
        mfr.blocksAerial = ByteIO.readBoolean(_bytes,indexPointer);     indexPointer++;
        mfr.variety_w = ByteIO.readInt(_bytes,indexPointer);            indexPointer+=4;
        mfr.variety_h = ByteIO.readInt(_bytes,indexPointer);            indexPointer+=4;
        mfr.width     = ByteIO.readInt(_bytes,indexPointer);            indexPointer+=4;
        mfr.height    = ByteIO.readInt(_bytes,indexPointer);            indexPointer+=4;
        mfr.radius    = ByteIO.readInt(_bytes,indexPointer);            indexPointer+=4;
        int imageSize = ByteIO.readInt(_bytes,indexPointer);            indexPointer+=4;
        imageSize = _bytes.length - (indexPointer+2);
        mfr.image     = ByteIO.readBytes(_bytes,indexPointer,imageSize); indexPointer+=imageSize;
        short footer =  ByteIO.readShort(_bytes,indexPointer);
        return mfr;
    }
}
