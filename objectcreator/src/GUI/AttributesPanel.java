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
        Object object = parseObject(((String)attributeType.getSelectedItem()),attribute.getText());
        if(object!=null){
            attributes.put(attributeID.getText(),object);
            attributesObjects.add(object);
        }else{
            JOptionPane.showMessageDialog(this, "Types don't match, check your spelling or correct the type selection");
            return;
        }
        String labelString = "ID:" + attributeID.getText() + " " + "Type:" + attributeType.getSelectedItem().toString() + " " + "Value:" + attribute.getText();
        JLabel label = new JLabel(padToLength(labelString,30));
        JButton button = new JButton("x"); button.addActionListener(this);
        label.setBounds(0,attributesObjects.indexOf(object)*LIST_HEIGHT,LIST_WIDTH,LIST_HEIGHT);
        button.setBounds(260,attributesObjects.indexOf(object)*LIST_HEIGHT,60,LIST_HEIGHT);
        attributesLabel.add(label);
        attributesDelete.add(button);
        add(label);
        add(button);


        itemCount++;
        relist();
    }

    private void removeAttribute(int index){
        attributes.remove(attributesObjects.get(index));
        attributesObjects.remove(index);
        remove(attributesLabel.get(index));
        remove(attributesDelete.get(index));
        attributesLabel.remove(index);
        attributesDelete.remove(index);

        itemCount--;
        relist();
    }

    private void relist(){
        for(int i = 0; i < attributesObjects.size(); i ++){
            attributesLabel.get(i).setBounds(0,i*LIST_HEIGHT,200,LIST_HEIGHT);
            attributesDelete.get(i).setBounds(260,i*LIST_HEIGHT,60,LIST_HEIGHT);
        }
        attributeID.setBounds(0,itemCount*LIST_HEIGHT,100,LIST_HEIGHT);
        attributeType.setBounds(100,itemCount*LIST_HEIGHT,60,LIST_HEIGHT);
        attribute.setBounds(160,itemCount*LIST_HEIGHT,100,LIST_HEIGHT);
        add.setBounds(260,itemCount*LIST_HEIGHT,60,LIST_HEIGHT);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if(object.equals(add)){
            addAttribute();
        }else {
            int index = attributesDelete.indexOf(object);
            removeAttribute(index);
        }
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
            if(attribute.equals("true")){
                Object object = Boolean.valueOf(true);
                return object;
            }
            if(attribute.equals("false")){
                Object object = Boolean.valueOf(false);
                return object;
            }
            return null;

        }


        return null;
    }

    private static String padToLength(String string, int length){
        String[] strings = string.split(" ");
        int totalPad = length - string.length();
        String[] pads = new String[strings.length -1];
        for(int i = 0;i < pads.length; i++){
            pads[i] = " ";
        }
        for(int i = 0;i < totalPad; i++){
            pads[i%pads.length]+=" ";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strings[0]);
        for(int i = 0 ; i < pads.length ; i++){
            stringBuilder.append(pads[i]);
            stringBuilder.append(strings[i+1]);
        }
        return stringBuilder.toString();
    }
}
