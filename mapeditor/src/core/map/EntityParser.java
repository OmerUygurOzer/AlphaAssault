package core.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import core.Resources;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Omer on 1/3/2016.
 */
public class EntityParser {

    private static java.util.Map<Integer,Entity> entityCache;

    public static void cache(){
            entityCache = new HashMap<Integer, Entity>();


            String path = Gdx.files.getLocalStoragePath();
            path += "types";

            File[] allXML = new File(path).listFiles();

            for(int i = 0; i<allXML.length ; i++){
                Entity parsedEntity = new Entity();
                int id;
                int size;
                int variety;


                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = null;
                try {
                    dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(allXML[i]);
                    doc.getDocumentElement().normalize();



                    NodeList nList = doc.getElementsByTagName("base");

                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;

                          id = Integer.parseInt(eElement.getAttribute("id"));
                          size = Integer.parseInt(eElement.getElementsByTagName("size").item(0).getTextContent());
                           variety = Integer.parseInt(eElement.getElementsByTagName("variety").item(0).getTextContent());

                            parsedEntity.id = id;
                            parsedEntity.variety = variety;
                            System.out.println(id);
                            Sprite image = new Sprite(Resources.getTextureRegions(id)[0][0]);
                            image.setSize(size,size);
                            parsedEntity.image = image;
                            entityCache.put(id,parsedEntity);
                        }


                    }


                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }




    }

    public static Entity parse(int _id){
        Entity entity = new Entity();
        entity.image = new Sprite();
        entity.image.set(entityCache.get(_id).image);
        entity.variety = entityCache.get(_id).variety;
        entity.id = _id;

        return entity;
    }
}
