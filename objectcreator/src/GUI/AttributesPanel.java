package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 1/27/2016.
 */
public class AttributesPanel extends JPanel implements ActionListener{

    private static final int LIST_WIDTH = 300;
    private static final int LIST_HEIGHT = 20;

    private int itemCount = 0;

    private Map<String,Object> attributes;

    private JTextField attributeID;
    private JComboBox<String> attributeType;
    private JTextField attribute;
    private JButton add;

    private List<JLabel> attributesLabel;
    private List<JButton> attributesDelete;
    private List<Object> attributesObjects;

    public AttributesPanel(int x,int y,int width,int height){
        setLayout(null);
        setBounds(x,y,width,height);

        attributesLabel   = new ArrayList<JLabel>();
        attributesDelete  = new ArrayList<JButton>();
        attributesObjects = new ArrayList<Object>();

        attributeID = new JTextField();
        attributeType = new JComboBox<String>();
        attributeType.addItem("String");
        attributeType.addItem("Double");
        attributeType.addItem("Boolean");

        attribute = new JTextField();
        add = new JButton("Add");
        add.addActionListener(this);



        add(attributeID);
        add(attributeType);
        add(attribute);
        add(add);

        relist();
    }

    public void setAttributeMap(Map<String,Object> attributes){
        this.attributes = attributes;
    }

    public void addAttribute(){
        if(parseObject(((String)attributeType.getSelectedItem()),attribute.getText())!=null){
            System.out.println(parseObject(((String)attributeType.getSelectedItem()),attribute.getText()));
            attributes.put(attributeID.getText(),parseObject(((String)attributeType.getSelectedItem()),attribute.getText()));
        }else{
            JOptionPane.showMessageDialog(this, "Types don't match, check your spelling or correct the type selection");
            return;
        }


        itemCount++;
        relist();
    }

    private void removeAttribute(){

        itemCount = itemCount==0 ? itemCount = 0 : itemCount--;
        relist();
    }

    private void relist(){
        attributeID.setBounds(0,itemCount*LIST_HEIGHT,100,LIST_HEIGHT);
        attributeType.setBounds(100,itemCount*LIST_HEIGHT,60,LIST_HEIGHT);
        attribute.setBounds(160,itemCount*LIST_HEIGHT,100,LIST_HEIGHT);
        add.setBounds(260,itemCount*LIST_HEIGHT,60,LIST_HEIGHT);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addAttribute();
    }

    private static Object parseObject(String type,String attribute){
        if(type.equals("String")){
           Object object = new String(attribute);
           return object;
        }
        if(type.equals("Double")){
            try{
                Object object = Double.parseDouble(attribute);
                return object;
            }catch (Exception e){
                return null;
            }
        }
        if(type.equals("Boolean")){
            try{
                Object object = Boolean.parseBoolean(attribute);
                return object;
            }catch (Exception e){
                return null;
            }
        }


        return null;
    }
}
