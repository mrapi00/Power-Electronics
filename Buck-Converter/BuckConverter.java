/******************************************************************************
 *  Author: Mahmudul Rapi
 *  Title: Buck Converter Circuit Analysis
 *  Compilation:  javac BuckConverter.java
 *  Execution:    java BuckConverter   (interactive test of basic functionality)
 *  Dependencies: StdIn.java, StdOut.java, DisplayImage.java, BuckConverterDiagram.png
 *
 *  Buck converter circuit steps down DC input voltage to DC output voltage.
 *
 ******************************************************************************/

import java.io.IOException;

public class BuckConverter{
    // input DC voltage source
    private final double Vin;
    // user input dutyCycle of MOSFET
    private final double dutyCycle;
    // switching frequency
    private final double f;
    // inductance of inductor
    private final double L;
    // capacitance of capacitor
    private final double C;
    // output resistance
    private final double R;
    // output voltage
    private double Vo;
    //output current
    private double Io;

    private BuckConverter(double Vin, double dutyCycle, double L, double C, double R, double f) {
        this.Vin = Vin;
        this.dutyCycle = dutyCycle;
        this.L = L;
        this.C = C;
        this.R = R;
        this.f = f;
    }
    // compute and set output voltage with parasitic inductance
    private double computeVout() {
        double Vout = 0;

        Vout = Vin * dutyCycle;
        Vo = Vout;
        Io = Vo / R; // ohm's law
        return Vout;
    }

    private double computeRippleRatio() {
        double rippleRatio;

        rippleRatio = R * (1 - dutyCycle) / (2 * L * f);
        
        return rippleRatio;
    }

    private double inductorCurrentRipple() {
        double Iptp;
        Iptp = Vo * (1 - dutyCycle) / (L * f);
        return Iptp;
    }

    private double capacitorVoltageRipple() {
        double Vptp;
        double Ip2p = inductorCurrentRipple();
        double deltaT = 1 / (2 * f);

        Vptp = 1 / C * (Ip2p * deltaT);
        return Vptp;
    }

    public static void main(String[] args) throws IOException{
        StdOut.println("Analysis of the Buck Converter Power Electronic Circuit! \n");
        StdOut.println("What is DC voltage source (in volts)?");
        double inputVin = StdIn.readDouble();
        StdOut.println("What is capacitance (in F)?");
        double inputC = StdIn.readDouble();
        StdOut.println("What is the inductance (in H)?");
        double inputL = StdIn.readDouble();
        StdOut.println("What is the resistance of the output resistor/load (in ohms)?");
        double inputR = StdIn.readDouble();
        StdOut.println("What is the duty cycle of the MOSFET (0 < D < 1?");
        double inputD = StdIn.readDouble();
        StdOut.println("What is the switching frequency of the MOSFET in Hz");
        double inputf = StdIn.readDouble();

        BuckConverter bc = new BuckConverter(inputVin, inputD, inputL, inputC, inputR, inputf);
        double Vout = bc.computeVout();
        double capVolt = bc.capacitorVoltageRipple();
        double indCurr = bc.inductorCurrentRipple();
        double rippleRatio = bc.computeRippleRatio();
    }
     
}