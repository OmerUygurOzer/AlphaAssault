package core.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Omer on 12/28/2015.
 */
public class GraphicsUtils {
    public static TextureRegion[][] splitTexture(Texture _texture, int _x, int _y, int _width, int _height, int _xNumber, int _yNumber){
        TextureRegion[][] textureRegions = new TextureRegion[_yNumber][_xNumber];

        for(int y = 0; y < _yNumber;y++){
            for(int x = 0;x<_xNumber;x++){
                TextureRegion textureRegion = new TextureRegion(_texture,_x+x*_width,_y+y*_height,_width,_height);
                textureRegions[y][x] = textureRegion;
            }
        }

        return textureRegions;
    }

    public static TextureRegion[] linearizeTextureRegion(TextureRegion[][] textureRegions){
        int widthIndex = textureRegions.length;
        int heightIndex = textureRegions[0].length;

        TextureRegion[] linear = new TextureRegion[widthIndex * heightIndex];

        for(int x = 0 ; x< widthIndex ; x++){
            for (int y = 0 ; y < heightIndex ; y++){
                linear[y + x*heightIndex] = textureRegions[x][y];
            }
        }


        return linear;
    }
}
