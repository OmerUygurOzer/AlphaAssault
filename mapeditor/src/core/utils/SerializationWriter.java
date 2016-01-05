package core.utils;

/**
 * Created by Omer on 12/24/2015.
 */
public class SerializationWriter {
    public static final byte[] HEADER = "AA".getBytes();
    public static final short VERSION = 0x0100;


    public static int writeBytes(byte[] bytes,int indexPointer,byte value){
        bytes[indexPointer++] = value;
        return indexPointer;
    }

    public static int writeBytes(byte[] bytes,int indexPointer,byte[] data){
       for(int i = 0;i<data.length;i++){
           bytes[indexPointer++] = data[i];
       }
        return indexPointer;
    }

    public static int writeBytes(byte[] bytes,int indexPointer,short value){
        bytes[indexPointer++] = (byte)((value >> 8) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 0) & 0xff);
        return indexPointer;
    }

    public static int writeBytes(byte[] bytes,int indexPointer,int value){
        bytes[indexPointer++] = (byte)((value >> 24) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 16) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 8) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 0) & 0xff);
        return indexPointer;
    }

    public static int writeBytes(byte[] bytes,int indexPointer,long value){
        bytes[indexPointer++] = (byte)((value >> 86) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 48) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 40) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 32) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 24) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 16) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 8) & 0xff);
        bytes[indexPointer++] = (byte)((value >> 0) & 0xff);
        return indexPointer;
    }

    public static int writeBytes(byte[] bytes,int indexPointer,float value){
       int data = Float.floatToIntBits(value);
        return writeBytes(bytes,indexPointer,data);
    }

    public static int writeBytes(byte[] bytes,int indexPointer,double value){
        long data = Double.doubleToLongBits(value);
        return writeBytes(bytes,indexPointer,data);
    }


    public static int writeBytes(byte[] bytes,int indexPointer,boolean value){
        bytes[indexPointer++] = (byte)(value ? 1 : 0);
        return indexPointer;
    }

    public static int writeBytes(byte[] bytes,int indexPointer,String value){
      indexPointer = writeBytes(bytes,indexPointer,(short)value.length());
        return writeBytes(bytes,indexPointer,value.getBytes());
    }
    public static long readLong(byte[] bytes,int indexPointer){
        return (long)((bytes[indexPointer] << 56) | (bytes[indexPointer+1] << 48) | (bytes[indexPointer+2] << 40) | (bytes[indexPointer+3] << 32) | (bytes[indexPointer+4] << 24) | (bytes[indexPointer+5] << 16) | (bytes[indexPointer+6] << 8) | (bytes[indexPointer+7] << 0));
    }

    public static int readInt(byte[] bytes,int indexPointer){
        return (int)((bytes[indexPointer] << 24) | (bytes[indexPointer+1] << 16) | (bytes[indexPointer+2] << 8) | (bytes[indexPointer+3] << 0));
    }

    public static short readShort(byte[] bytes,int indexPointer){
        return (short)((bytes[indexPointer+2] << 8) | (bytes[indexPointer+3] << 0));
    }

    public static float readFloat(byte[] bytes,int indexPointer){
        return Float.intBitsToFloat(readInt(bytes,indexPointer));
    }

    public static double readDouble(byte[] bytes,int indexPointer){
        return Double.longBitsToDouble(readLong(bytes,indexPointer));
    }

    public static char readChar(byte[] bytes,int indexPointer){
        return (char)((bytes[indexPointer+2] << 8) | (bytes[indexPointer+3] << 0));
    }

    public static boolean readBoolean(byte[] bytes,int indexPointer){
        assert(bytes[indexPointer] == 0 || bytes[indexPointer] == 1);
        return bytes[indexPointer]!=0;
    }

    public static byte readByte(byte[] bytes,int indexPointer){
        return bytes[indexPointer];
    }


}
