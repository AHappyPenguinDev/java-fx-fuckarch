package JavaGUI;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.beans.fxml.FXML;
import javafx.beans.scene.control.Label;
import javafx.beans.scene.control.Slider;
import javafx.beans.scene.control.TextField;

public class TipCalculatorController {
  // formaters for currency and percentages
  private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
  private static final NumberFormat percent = NumberFormat.getPercentInstance();

  private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

  @FXML
  private TextField amountTextField;

  @FXML
  private TextField tipPercentageLabel;

  @FXML
  private TextField tipPercentageSlider;

  @FXML
  private TextField tipTextField;
  @FXML

  @FXML
  private TextField totalTextField;

  @FXML
  private void calculateButtonPressed(ActionEvent event) {
    try {
      BigDecimal amount = new BigDecimal(amountTextField.getText());
      BigDecimal tip = amount.multiply(tipPercentage);
      BigDecimal total = amount.add(tip);

      tipTextField.setText(currency.format(tip));
      totalTextField.setText(currency.format(total));
    } catch (NumberFormatException ex) {
      amountTextField.setText("Enter amount");
      amountTextField.selectAll();
      amountTextField.requestFocus();
    }
  }

  public void initialize() {
    // 0-4 rounds down, 5-9 rounds up
    currency.setRoundingMode(RoundingMode.HALF_UP);

    // listener for changes to tipPErcentageSlider's value
    tipPercentageSlider.valueProperty().addListener(
        new ChangeListener<Number>() {
          @Override
          public void changed(ObservableValue<? extends Number> ov,
              Number oldValue, Number newValue) {
            tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
            tipPercentageLabel.setText(percent.format(tipPercentage));
          }
        });
  }
}
