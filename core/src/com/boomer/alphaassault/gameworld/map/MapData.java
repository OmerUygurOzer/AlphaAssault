package com.boomer.alphaassault.gameworld.map;

import com.boomer.alphaassault.networkutils.SerializationWriter;

import java.io.Serializable;

/**
 * Created by Omer on 12/28/2015.
 */
public class MapData {

    private int byteSize;

    private int[][] tiles;
    private int[][] mapfeatures;

    private int width;
    private int heigth;




    public MapData(int _width,int _height,int [][] _tiles,int[][] _mapFeatures){
        tiles = _tiles;
        mapfeatures = _mapFeatures;

        width = _width;
        heigth = _height;

        byteSize = tiles.length * tiles[0].length * 8;
        byteSize += 8;

    }

    public MapData(){

    }

    public byte[] serialize() {
        int pointerIndex = 0;

        byte[] bytes = new byte[byteSize];

        pointerIndex = SerializationWriter.writeBytes(bytes,pointerIndex,width);
        pointerIndex = SerializationWriter.writeBytes(bytes,pointerIndex,heigth);

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                pointerIndex = SerializationWriter.writeBytes(bytes,pointerIndex,tiles[y][x]);
                pointerIndex = SerializationWriter.writeBytes(bytes,pointerIndex,mapfeatures[y][x]);
            }
        }

        return bytes;
    }

    public void deSerialize(byte[] bytes){



        int pointerIndex = 0;
        width = SerializationWriter.readInt(bytes,pointerIndex);
        pointerIndex+=4;
        heigth = SerializationWriter.readInt(bytes,pointerIndex);
        pointerIndex+=4;

        tiles = new int[width][heigth];
        mapfeatures = new int[width][heigth];

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < heigth; x++) {
                tiles[y][x] = SerializationWriter.readInt(bytes,pointerIndex);
                pointerIndex += 4;
                mapfeatures[y][x] = SerializationWriter.readInt(bytes,pointerIndex);
                pointerIndex += 4;
            }
        }

    }

    public void parseMap(Map _map){
        _map.generateMap(width,heigth,tiles,mapfeatures);
    }

}
