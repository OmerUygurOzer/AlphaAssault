import data.SerializationWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/5/2016.
 */
public class ObjectCreator {

    private static final String EXTENSION = ".aaobj";

    private static final String WORKING_PATH = "C:\\Users\\Omer\\Desktop\\Game Projects\\AlphaAssault\\mapeditor\\mapfiles\\";
    private static final String TYPES_IN = "types\\";
    private static final String OBJECTS_OUT = "objects\\";

    private static final String ALL = "-all";
    private static final String MAP_FEATURE = "-mapfeature";
    private static final String UNIT = "-unit";
    private static final String SPAWNER = "-spawner";

    private static final String SUCCESS = "Success.";
    private static final String ERROR = "Error.";

    private static final int BASE = 4;
    private static final int MAP_FEATURE_BASE = 25;

    private static class FileRead{
        public int size;
        public byte[] bytes;
    }

    public static void main (String[] arg) {

        String result = "ObjectCreator: ";


        for(int i = 0; i < arg.length; i++){
            if(arg[i].charAt(0) == '-'){
                if(arg[i].equals(MAP_FEATURE)){
                    result += MAP_FEATURE + " ";
                    if(arg.length>2 || arg.length<2){
                        result +=ERROR;
                        System.out.println(result);
                        return;
                    }
                    if(arg[i+1].charAt(0)=='-'){
                        result +=ERROR;
                        System.out.println(result);
                        return;
                    }
                   createMapFeature(arg[i+1]);



                }

            }
        }



        result += SUCCESS;
        System.out.println(result);
    }

    public static void createMapFeature(String dir) {
        System.out.println("Creating map feature...");
        String path = WORKING_PATH;
        path += TYPES_IN;
        String pathXML = path+ dir + ".xml";
        String pathPNG = path+ dir +".png";
        String pathOBJ = WORKING_PATH + OBJECTS_OUT + dir + EXTENSION;

        int byteCount = BASE + MAP_FEATURE_BASE;
        FileRead imageFile = readFromFile(pathPNG);
        byteCount += imageFile.size;

        int width = 0;
        int height = 0;
        int variety_w = 0;
        int variety_h = 0;
        int radius = 0;
        boolean destroyable = false;
        boolean blocksMovement = false;
        boolean blocksBullets = false;
        boolean blocksDamage = false;
        boolean blocksAerial = false;
        int imageSize = imageFile.size;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;



        File dirXML = new File(pathXML);
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(dirXML);
            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("base");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    variety_w = Integer.parseInt(eElement.getElementsByTagName("variety_w").item(0).getTextContent());
                    variety_h = Integer.parseInt(eElement.getElementsByTagName("variety_h").item(0).getTextContent());
                    width = Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent());
                    height = Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent());
                    radius = Integer.parseInt(eElement.getElementsByTagName("radius").item(0).getTextContent());
                    destroyable = eElement.getElementsByTagName("destroyable").item(0).getTextContent().equals("true");
                    blocksMovement =  eElement.getElementsByTagName("blocksMovement").item(0).getTextContent().equals("true");
                    blocksBullets =  eElement.getElementsByTagName("blocksBullets").item(0).getTextContent().equals("true");
                    blocksDamage =  eElement.getElementsByTagName("blocksDamage").item(0).getTextContent().equals("true");
                    blocksAerial =  eElement.getElementsByTagName("blocksAerial").item(0).getTextContent().equals("true");
                }


            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int indexPointer = 0;
        byte[] bytes = new byte[byteCount];
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,destroyable);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,blocksMovement);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,blocksBullets);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,blocksDamage);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,blocksAerial);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,variety_w);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,variety_h);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,width);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,height);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,radius);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,imageSize);
        indexPointer = SerializationWriter.writeBytes(bytes,indexPointer,imageFile.bytes);
        System.out.println(indexPointer + " total bytes processed.");

        File outputFile = new File(pathOBJ);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Object created at: " + pathOBJ);
    }
    public static void createUnit(String dir){

    }



    public static FileRead readFromFile(String dir){


        FileRead fileRead = new FileRead();
        File file = new File(dir);
        byte[] bytes = new byte[(int)file.length()];
        fileRead.size = bytes.length;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileRead.bytes = bytes;
        return fileRead;
    }




}
