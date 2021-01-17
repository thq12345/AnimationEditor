package cs3500.animator.view;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 * Upgraded version JTextField. Support having grey hint text in the textfield when user did not
 * enter anything in that field. (Or if the user did not clicked on that textfield).
 */
public class JTextFieldUpgradedWithFocus implements FocusListener {

  private String hintcontent;
  private JTextField textField;

  /**
   * Constructor for upgraded JTextField.
   *
   * @param textField   JTextField input.
   * @param hintcontent hint text to display when user did not input anything.
   */
  JTextFieldUpgradedWithFocus(JTextField textField, String hintcontent) {
    this.textField = textField;
    this.hintcontent = hintcontent;
    textField.setText(hintcontent);
    textField.setForeground(Color.GRAY);
  }

  /**
   * Invoked when a component gains the keyboard focus.
   *
   * @param e the event to be processed
   */
  @Override
  public void focusGained(FocusEvent e) {
    String content = textField.getText();
    if (content.equals(hintcontent)) {
      textField.setText("");
      textField.setForeground(Color.BLACK);
    }
  }

  /**
   * Invoked when a component loses the keyboard focus.
   *
   * @param e the event to be processed
   */
  @Override
  public void focusLost(FocusEvent e) {
    String content = textField.getText();
    if (content.equals("")) {
      textField.setForeground(Color.GRAY);
      textField.setText(hintcontent);
    }
  }
}
