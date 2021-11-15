/******************************************************************************
 *  Author: Mahmudul Rapi
 *  Title: Boost Conerter Circuit Analysis
 *  Compilation:  javac BoostConverter.java
 *  Execution:    java BoostConverter   (interactive test of basic functionality)
 *  Dependencies: StdIn.java, StdOut.java, DisplayImage.java, BoostConverterDiagram.png
 *
 *  Buck converter circuit steps down DC input voltage to DC output voltage.
 *
 ******************************************************************************/

import java.io.IOException;

public class BoostConverter{
    // input DC voltage source
    private final double Vin;
    // user input dutyCycle of MOSFET
    private final double dutyCycle;
    // switching requency of MOSFET
    private final double f;
    // inductance of inductor
    private final double L;
    // capacitance of capacitor
    private final double C;
    // output resistance
    private final double R;
    // output voltage
    private final double Vo;
    // output current
    private final double Io;
    // input current
    private final double Iin;

    private BoostConverter(double Vin, double dutyCycle, double L, double C, double R, double f) {
        this.Vin = Vin;
        this.dutyCycle = dutyCycle;
        this.L = L;
        this.C = C;
        this.R = R;
        this.f = f;

        this.Vo = Vin * 1 / (1 - dutyCycle);
        this.Io = Vo / R;
        this.Iin = Vo * Io / Vin;
    }

    private double inputCurrent() {
        return Iin;
    }

    private double outputVoltage() {
        return Vo;
    }

    private double outputCurrent() {
        return Io;
    }

    private double computeRippleRatio() {
        double rippleRatio;

        rippleRatio = R * Math.pow((1 - dutyCycle), 2) * dutyCycle / (2 * L * f);
        
        return rippleRatio;
    }

    private double inductorCurrentRipple() {
        double Iptp;
        Iptp = Vin * dutyCycle / (L * f);
        return Iptp;
    }

    private double capacitorVoltageRipple() {
        double Vptp;

        Vptp = dutyCycle * Iin * (1 - dutyCycle) / (f * C);
        return Vptp;
    }

    public static void main(String[] args) throws IOException{
        StdOut.println("Analysis of the Buck Converter Power Electronic Circuit! \n");
        DisplayImage image = new DisplayImage("BoostConverterDiagram.png");
        StdOut.println("What is DC voltage source Vs (in volts)?");
        double inputVin = StdIn.readDouble();
        StdOut.println("What is capacitance (in F)?");
        double inputC = StdIn.readDouble();
        StdOut.println("What is the inductance (in H)?");
        double inputL = StdIn.readDouble();
        StdOut.println("What is the resistance of the output resistor/load (in ohms)?");
        double inputR = StdIn.readDouble();
        StdOut.println("What is the duty cycle of the MOSFET (0 < D < 1)?");
        double inputD = StdIn.readDouble();
        StdOut.println("What is the switching frequency of the MOSFET in Hz?");
        double inputf = StdIn.readDouble();

        BoostConverter bc = new BoostConverter(inputVin, inputD, inputL, inputC, inputR, inputf);
        double Vout = bc.outputVoltage();
        double Iout = bc.outputCurrent();

        double capVolt = bc.capacitorVoltageRipple();
        double indCurr = bc.inductorCurrentRipple();
        double rippleRatio = bc.computeRippleRatio();
        StdOut.println("\nEXPECTED VALUES:");
        StdOut.printf("The DC output voltage is %2.4f volts\n", Vout);
        StdOut.printf("The DC output current is %2.4f amps\n\n", Iout);

        StdOut.println("Additional Design details:");
        StdOut.printf("The peak-to-peak inductor current ripple is %2.7f amps\n", indCurr);
        StdOut.printf("The peak-to-peak capacitor voltage ripple is %2.7f volts\n", capVolt);
        StdOut.printf("The ripple ratio of the buck converter is %2.5f whichs means ", rippleRatio);
        String message;
        double epsilon = 0.0001;
        if (rippleRatio < 1 - epsilon)
            message = "the converter is running in continuous conduction mode!";
        else if (rippleRatio > 1 + epsilon) message = "the converter is running in discontinuous conduction mode!";
        else message = "the converter is running in boundary conduction mode!";
        StdOut.println(message);

        double Iin = bc.inputCurrent();
        StdOut.printf("The MOSFET and diode must be able to block %2.4f volts.\n", Vout);
        StdOut.printf("The MOSFET and diode must be able to carry %2.4f amps.\n", Iin);

    }
     
}