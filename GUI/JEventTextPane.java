package GUI;

import CalendarClasses.Alert;
import CalendarClasses.Event;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.ArrayList;

/**
 * A text pane to represent an event, displaying its details
 */
public class JEventTextPane extends JTextPane {
    private Event event;

    /**
     * Creates a new pane and fills it with the details of event e
     * @param e the event to display in this pane
     */
    public JEventTextPane(Event e) {
        super();

        this.event = e;
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        this.setCharacterAttributes(attributeSet, true);
        this.setText(event.toString());
        this.setMaximumSize(new Dimension(390, 100));
        this.setMinimumSize(new Dimension(390, 100));
        this.setAlignmentY(Component.TOP_ALIGNMENT);
        this.setEditable(false);
    }

    /**
     * Sets the event to display
     * @param e the event to display
     */
    public void setEvent(Event e) {
        this.event = e;
    }

    /**
     * Returns the displayed event.
     * @return the displayed event.
     */
    public Event getEvent() {
        return this.event;
    }
}
