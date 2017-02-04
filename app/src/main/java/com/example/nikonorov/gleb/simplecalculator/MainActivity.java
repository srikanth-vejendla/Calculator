package com.example.nikonorov.gleb.simplecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.nikonorov.gleb.simplecalculator.R.id.outputLabel;

public class MainActivity extends Activity {
    private float currentSum = 0, operandB = 0;
    private boolean wasDotPressed = false, hasProcessedInput = false, hasPressedOperation = false;
    private char selectedOperation;
    private final char SELECTED_MUL = 'm',
        SELECTED_DIV = 'd',
        SELECTED_ADD = 'a',
        SELECTED_SUB = 's',
        SELECTED_NONE = 'z';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //this method will handle all clicks in application
    public void processButtonClick(View v)
    {
        TextView text = (TextView) findViewById(R.id.outputLabel);
        String buttonText = ((Button) v).getText().toString();

        if(buttonText.equals("="))
        {
            hasPressedOperation = false;
            processComputation(text);
        }
        else
        {
            if(buttonText.equals("C"))
            {
                //process clear
                if(operandB != 0)
                    operandB = 0;
                else
                    currentSum = 0;

                text.setText("0");

                hasPressedOperation = false;
            }
            else if(buttonText.equals("+/-"))
            {
                if(hasProcessedInput)
                    currentSum = -currentSum;
                else
                    operandB = -operandB;

                hasPressedOperation = false;

                float negatedResult = -Float.parseFloat(text.getText().toString());
                if(negatedResult % 1 == 0)
                    text.setText(Integer.toString((int) negatedResult));
                else
                    text.setText(Float.toString(negatedResult));

            }
            else if(buttonText.equals("DIV"))
            {
                if(!hasProcessedInput && !hasPressedOperation)
                {
                    currentSum = operandB;
                    operandB = 0;
                }

                selectedOperation = SELECTED_DIV;
                hasPressedOperation = true;
            }
            else if(buttonText.equals("MUL"))
            {
                if(!hasProcessedInput && ! hasPressedOperation)
                {
                    currentSum = operandB;
                    operandB = 0;
                }

                selectedOperation = SELECTED_MUL;
                hasPressedOperation = true;
            }
            else if(buttonText.equals("SUB"))
            {
                if(!hasProcessedInput && !hasPressedOperation)
                {
                    currentSum = operandB;
                    operandB = 0;
                }

                selectedOperation = SELECTED_SUB;
                hasPressedOperation = true;
            }
            else if(buttonText.equals("ADD"))
            {
                if(!hasProcessedInput && !hasPressedOperation)
                {
                    currentSum = operandB;
                    operandB = 0;
                }

                selectedOperation = SELECTED_ADD;
                hasPressedOperation = true;
            }

            else if(buttonText.equals("."))
            {
                if(!wasDotPressed)
                {
                    if((hasProcessedInput && (currentSum % 1 == 0)) || (!hasProcessedInput && (operandB % 1 == 0)))
                        text.setText(text.getText().toString() + ".");

                    wasDotPressed = true;
                    hasPressedOperation = false;
                }
            }

            else if(buttonText.equals("%"))
            {
                //make percent
                hasPressedOperation = false;

                if(hasProcessedInput)
                    currentSum /= 100;
                else
                    operandB /= 100;

                pasteOutput(text, false);
            }
            else
            {
                hasPressedOperation = false;

                if(!wasDotPressed)
                {
                    //add in number
                    operandB *= 10;
                    operandB += Integer.parseInt(buttonText);
                }
                else
                {
                    float dummy;
                    if(hasProcessedInput)
                        dummy = currentSum;
                    else
                        dummy = operandB;

                    int offset = 1;
                    float addend = Float.parseFloat(buttonText);
                    while(dummy % 1 != 0)
                    {
                        dummy *= 10;
                        offset++;
                    }

                    while(offset > 0)
                    {
                        addend /= 10;
                        offset--;
                    }

                    if(hasProcessedInput)
                        currentSum += addend;
                    else
                        operandB += addend;
                }

                //must convert int to string for text view or err is thrown
                String outputText;
                if(hasProcessedInput)
                {
                    if (currentSum % 1 == 0)
                        outputText = Integer.toString((int) currentSum);
                    else
                        outputText = Float.toString(currentSum);
                }
                else
                {
                    if (operandB % 1 == 0)
                        outputText = Integer.toString((int) operandB);
                    else
                        outputText = Float.toString(operandB);
                }

                text.setText(outputText);

                hasProcessedInput = false;
                wasDotPressed = false;
            }
        }
    }

    private void processComputation(TextView outputText)
    {
        if(selectedOperation == SELECTED_SUB)
            currentSum -= operandB;
        else if(selectedOperation == SELECTED_ADD)
            currentSum += operandB;
        else if(selectedOperation == SELECTED_MUL)
            currentSum *= operandB;
        else if(selectedOperation == SELECTED_DIV)
            currentSum /= operandB;

        pasteOutput(outputText, true);

        selectedOperation = SELECTED_NONE;
        operandB = 0;

        hasProcessedInput = true;
    }

    private void pasteOutput(TextView outputLabel, boolean usingCurrentSum)
    {
        String text = "";
        float displayNumber;
        if(usingCurrentSum || hasProcessedInput)
            displayNumber = currentSum;
        else
            displayNumber = operandB;

        if (displayNumber % 1 == 0)
            text = Integer.toString((int) displayNumber);
        else
            text = Float.toString(displayNumber);

        outputLabel.setText(text);
    }
}
