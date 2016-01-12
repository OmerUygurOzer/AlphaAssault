package core.objects;

import core.fileIO.SerializationWriter;

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
        short header = SerializationWriter.readShort(_bytes,indexPointer);           indexPointer+=2;
        int typeSize = SerializationWriter.readInt(_bytes,indexPointer);             indexPointer+=4;
        String type = SerializationWriter.readString(_bytes,indexPointer,typeSize);  indexPointer+=typeSize;
        mfr.destroyable  = SerializationWriter.readBoolean(_bytes,indexPointer);     indexPointer++;
        mfr.blocksMovement = SerializationWriter.readBoolean(_bytes,indexPointer);   indexPointer++;
        mfr.blocksBullets = SerializationWriter.readBoolean(_bytes,indexPointer);    indexPointer++;
        mfr.blocksDamage = SerializationWriter.readBoolean(_bytes,indexPointer);     indexPointer++;
        mfr.blocksAerial = SerializationWriter.readBoolean(_bytes,indexPointer);     indexPointer++;
        mfr.variety_w = SerializationWriter.readInt(_bytes,indexPointer);            indexPointer+=4;
        mfr.variety_h = SerializationWriter.readInt(_bytes,indexPointer);            indexPointer+=4;
        mfr.width     = SerializationWriter.readInt(_bytes,indexPointer);            indexPointer+=4;
        mfr.height    = SerializationWriter.readInt(_bytes,indexPointer);            indexPointer+=4;
        mfr.radius    = SerializationWriter.readInt(_bytes,indexPointer);            indexPointer+=4;
        int imageSize = SerializationWriter.readInt(_bytes,indexPointer);            indexPointer+=4;
        imageSize = _bytes.length - (indexPointer+2);
        mfr.image     = SerializationWriter.readBytes(_bytes,indexPointer,imageSize); indexPointer+=imageSize;
        short footer =  SerializationWriter.readShort(_bytes,indexPointer);
        return mfr;
    }
}
