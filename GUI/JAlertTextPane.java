package GUI;

import CalendarClasses.Alert;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

/**
 * An extension of a JTextPane to only show an alert
 */
public class JAlertTextPane extends JTextPane {
    private Alert alert;

    protected JAlertTextPane(Alert a) {
        super();

        this.alert = a;
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        this.setCharacterAttributes(attributeSet, true);
        this.setText(alert.toString());
        this.setMaximumSize(new Dimension(390, 100));
        this.setMinimumSize(new Dimension(390, 100));
        this.setAlignmentY(Component.TOP_ALIGNMENT);
        this.setEditable(false);
    }

    protected void setAlert(Alert e) {
        this.alert = e;
    }

    protected Alert getAlert() {
        return this.alert;
    }
}
