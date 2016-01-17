package GUI;

import javax.swing.*;

/**
 * Created by Omer on 1/17/2016.
 */
public class BasePalette extends JInternalFrame {
    private static final String TITLE = "Base Palette";

    public BasePalette() {
        super(TITLE,    true, // resizable
                        true, // closable
                        true, // maximizable
                        true);// iconifiable
    }
}
