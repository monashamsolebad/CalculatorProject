package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.math.RoundingMode;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class Controller implements Initializable {

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal(0.15);
    @FXML
    public TextField amountText;
    @FXML
    public Slider tipSlider;
    @FXML
    public TextField tipText;
    @FXML
    public TextField totalText;
    @FXML
    public Button calculateButton;
    @FXML
    public Label tipLabel;

    @FXML
    public void buttonClicked(ActionEvent event) {
        try {
            BigDecimal amount = new BigDecimal(amountText.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);
            tipText.setText(currency.format(tip));
            totalText.setText(currency.format(total));


        } catch (NumberFormatException ex) {
            amountText.setText("Enter amount");
            amountText.selectAll();
            amountText.requestFocus();

        }


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currency.setRoundingMode(RoundingMode.HALF_UP);
        tipSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
                    tipLabel.setText(percent.format(tipPercentage));

                }
        );
    }
}
