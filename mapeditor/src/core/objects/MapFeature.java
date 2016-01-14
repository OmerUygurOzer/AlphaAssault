package core.objects;

import core.fileIO.ByteIO;
import core.fileIO.FileIO;

import java.io.File;

/**
 * Created by Omer on 1/13/2016.
 */
public class MapFeature {
    public static class Reflection{
        public String name;
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

        public static MapFeature.Reflection readFromFile(File file){
            byte [] bytes = FileIO.readFromFile(file);
            MapFeature.Reflection mfr = new MapFeature.Reflection();
            mfr.name = file.getName().substring(0,file.getName().length()-4);
            int indexPointer  = 0;
            short header      = ByteIO.readShort(bytes,indexPointer);           indexPointer+=2;
            int typeSize      = ByteIO.readInt(bytes,indexPointer);             indexPointer+=4;
            String type       = ByteIO.readString(bytes,indexPointer,typeSize); indexPointer+=typeSize;
            mfr.destroyable   = ByteIO.readBoolean(bytes,indexPointer);         indexPointer++;
            mfr.blocksMovement= ByteIO.readBoolean(bytes,indexPointer);         indexPointer++;
            mfr.blocksBullets = ByteIO.readBoolean(bytes,indexPointer);         indexPointer++;
            mfr.blocksDamage  = ByteIO.readBoolean(bytes,indexPointer);         indexPointer++;
            mfr.blocksAerial  = ByteIO.readBoolean(bytes,indexPointer);         indexPointer++;
            mfr.variety_w     = ByteIO.readInt(bytes,indexPointer);             indexPointer+=4;
            mfr.variety_h     = ByteIO.readInt(bytes,indexPointer);             indexPointer+=4;
            mfr.width         = ByteIO.readInt(bytes,indexPointer);             indexPointer+=4;
            mfr.height        = ByteIO.readInt(bytes,indexPointer);             indexPointer+=4;
            mfr.radius        = ByteIO.readInt(bytes,indexPointer);             indexPointer+=4;
            int imageSize     = ByteIO.readInt(bytes,indexPointer);             indexPointer+=4;
            imageSize         = bytes.length - (indexPointer+2);
            mfr.image         = ByteIO.readBytes(bytes,indexPointer,imageSize); indexPointer+=imageSize;
            short footer      =  ByteIO.readShort(bytes,indexPointer);
            return mfr;
        }
    }
    public static class Instance{
        public String name;
        public int variety;
        public int x;
        public int y;

        public Instance(String name){
            this.name = name;
            variety = 0;
            x = 0;
            y = 0;
        }

        public Instance(String name,int variety){
            this(name);
            this.variety = variety;
        }

        public Instance(String name,int variety,int x,int y){
            this(name,variety);
            this.x = x;
            this.y = y;
        }
    }




}
