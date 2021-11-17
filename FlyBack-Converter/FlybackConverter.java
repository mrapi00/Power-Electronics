/******************************************************************************
 *  Author: Mahmudul Rapi
 *  Title: Flyback Converter Circuit Analysis
 *  Compilation:  javac FlybackConverter.java
 *  Execution:    java FlybackConverter   (interactive test of basic functionality)
 *  Dependencies: StdIn.java, StdOut.java, DisplayImage.java, FlybackConverterDiagram.png
 *
 *  Flyback converter circuit can step up or down the DC input voltage to DC output voltage
 *  but with an inverted polarity.
 *  User will enter circuit parameters, and the program will compute the
 *  output voltage and current, along with the ripple across energy storage elements,
 *  switch limits, and the ripple ratio of the converter.
 *
 ******************************************************************************/

import java.io.IOException;

public class FlybackConverter{
    // input DC voltage source
    private final double Vin;
    // user input dutyCycle of MOSFET
    private final double dutyCycle;
    // output resistance
    private final double R;
    // output voltage
    private final double Vo;
    //output current
    private final double Io;
    // input current
    private final double Iin;

    // constructor which initalizes all values based on given data
    private FlybackConverter(double Vin, double dutyCycle, double R) {
        this.Vin = Vin;
        this.dutyCycle = dutyCycle;
        this.R = R;
    
        this.Vo = dutyCycle / (dutyCycle - 1) * Vin;
        this.Io = Vo / R; // ohm's law
        this.Iin = Vo * Io / Vin;
    }

    // returns input current
    private double inputCurrent() {
        return Iin;
    }

    // returns output voltage
    private double outputVoltage() {
        return Vo;
    }

    // returns output current
    private double outputCurrent() {
        return Io;
    }

    public static void main(String[] args) throws IOException{
        StdOut.println("Analysis of the Flyback Converter Power Electronic Circuit! \n");
        DisplayImage image = new DisplayImage("FlybackConverterDiagram.png");
        StdOut.println("What is DC voltage source Vs (in volts)?");
        double inputVin = StdIn.readDouble();

        StdOut.println("What is the resistance of the output resistor/load (in ohms)?");
        double inputR = StdIn.readDouble();
        StdOut.println("What is the duty cycle of the MOSFET (0 < D < 1)?");
        double inputD = StdIn.readDouble();

        FlybackConverter fbc = new FlybackConverter(inputVin, inputD, inputR);
        double Vout = fbc.outputVoltage();
        double Iout = fbc.outputCurrent();

        StdOut.println("\nEXPECTED VALUES:");
        StdOut.printf("The DC output voltage is %2.4f volts\n", Vout);
        StdOut.printf("The DC output current is %2.4f amps\n\n", Iout);
        StdOut.println("Additional Design details:");
        
        double Iin = fbc.inputCurrent();
        StdOut.printf("The MOSFET and diode must be able to block %2.4f volts.\n", inputVin + (Vout * -1));
        StdOut.printf("The MOSFET and diode must be able to carry %2.4f amps.\n", -Iout + Iin);
    }
}