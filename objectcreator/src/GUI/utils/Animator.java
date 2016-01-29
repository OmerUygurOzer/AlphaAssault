package GUI.utils;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Omer on 1/19/2016.
 */
public class Animator extends Canvas implements Runnable {

    private int canvasWidth;
    private int canvasHeight;

    private BufferStrategy bufferStrategy;
    private BufferedImage screen;
    private int[] pixels;
    private int[] framePixels;
    private boolean running = false;

    private List<BufferedImage> frames = new LinkedList<BufferedImage>();
    private int totalFrames = 0;
    private int currentFrame = 0;
    private float spf;

    private Object frameLock = new Object();

    public Animator(int x,int y,int width,int height){
        setBounds(x,y,width,height);
        this.canvasWidth = width;
        this.canvasHeight = height;

        bufferStrategy = getBufferStrategy();


        screen = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();

        running = true;
        Thread thread = new Thread(this);
        thread.start();


    }

    public void addFrame(BufferedImage frame){
        synchronized (frameLock) {
            this.frames.add(ImageUtils.resizeImage(frame,canvasWidth,canvasHeight));
            totalFrames = frames.size();
        }
    }

    public void addFrames(BufferedImage[] frames){
        synchronized (frameLock){
            for(int i = 0;i < frames.length;i++){
                this.frames.add(frames[i]);
                totalFrames += frames.length;
            }
        }
    }

    public void removeFrame(int index){
        synchronized (frameLock){
            currentFrame = 0;
            this.frames.remove(index);
            totalFrames--;
            System.out.println(frames.size());
        }
    }

    public void setSPF(float spf){
        synchronized (frameLock) {
            this.spf = spf;
        }
    }


    public void render(){
        synchronized (frameLock){
                if(frames.size() > 0) {
                    if(bufferStrategy==null){
                        createBufferStrategy(3);
                        bufferStrategy = getBufferStrategy();
                    }
                framePixels = ((DataBufferInt) frames.get(currentFrame).getRaster().getDataBuffer()).getData();
                for (int i = 0; i < pixels.length; i++)
                    pixels[i] = framePixels[i];

                Graphics graphics = bufferStrategy.getDrawGraphics();
                graphics.setColor(Color.MAGENTA);
                graphics.fillRect(0,0,canvasWidth,canvasHeight);
                graphics.drawImage(screen,0,0,canvasWidth,canvasHeight,null);
                graphics.dispose();
                bufferStrategy.show();

                currentFrame++;
                if (currentFrame == totalFrames)
                    currentFrame = 0;
            }else{
                    if(bufferStrategy==null){
                        createBufferStrategy(3);
                        bufferStrategy = getBufferStrategy();
                    }
                    for (int i = 0; i < pixels.length; i++)
                        pixels[i] = 0xF237F2;

                    Graphics graphics = bufferStrategy.getDrawGraphics();
                    graphics.setColor(Color.MAGENTA);
                    graphics.fillRect(0,0,canvasWidth,canvasHeight);
                    graphics.drawImage(screen,0,0,canvasWidth,canvasHeight,null);
                    graphics.dispose();
                    bufferStrategy.show();
                }
        }

    }

    @Override
    public void run() {
        long begin = System.nanoTime();
        while(running){
            if(System.nanoTime() - begin > spf*1000000000){
                begin = System.nanoTime();
                render();
            }
        }
    }

    public List<BufferedImage> getFrames() {
        return frames;
    }
}
