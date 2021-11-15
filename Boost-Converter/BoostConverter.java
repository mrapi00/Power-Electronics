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

public class BoostConverter{
    // input DC voltage source
    private static double Vin;
    // user input dutyCycle of MOSFET
    private static double dutyCycle;
    private static double f;
    // inductance of inductor
    private static double L;
    // capacitance of capacitor
    private static double C;
    // output resistance
    private static double R;
    // output voltage
    private static double Vo;
    //output current
    private static double Io;

    // compute and set output voltage with parasitic inductance
    private static double computeVout() {
        double Vout = 0;

        Vout = Vin * 1/ (1- dutyCycle);
        Vo = Vout;
        Io = Vo / R; // ohm's law
        return Vout;
    }

    private static double computeRippleRatio() {
        double rippleRatio;

        rippleRatio = R * Math.pow((1 - dutyCycle), 2) * dutyCycle / (2 * L * f);
        
        return rippleRatio;
    }

    public static void main(String[] args) throws IOException{
        
        StdOut.println("Analysis of the Diode Rectifier Power Electronic Circuit! \n");
        StdOut.println("Half-wave rectifier (1) or Full-wave recifier (2)? Enter either 1 or 2");
        int input = StdIn.readInt();
        while (input != 1 && input != 2){
            StdOut.println("Please enter 1 or 2");
            input = StdIn.readInt();
        }
        isFullWave = input;

        StdOut.println("Is the voltage waveform a sin wave or square? 1 for sin, 2 for square");
        input = StdIn.readInt();
        while (input != 1 && input != 2){
            StdOut.println("Please enter 1 (sin) or 2 (square)");
            input = StdIn.readInt();
        }
        waveform = input;
             
        StdOut.println("Is there any parasitic inductance? 1 for Yes, 2 for No");
        input = StdIn.readInt();
        while (input != 1 && input != 2){
            StdOut.println("Please enter 1 (Yes) or 2 (No)");
            input = StdIn.readInt();
        }
        parasitic = input;

        
        String im;
        if (isFullWave == 1)
            if (parasitic == 1){
                if (waveform == 1)  im= "Diagram/Half-wave-Rectifier-Sin_Wave.png";
                else  im = "Diagram/Half-wave-Rectifier-Square-Wave.png";
            }
            else {
                if (waveform == 1) im= "Diagram/Half-wave-Rectifier-Sin_Wave_No_Parasitic.png"; 
                else  im= "Diagram/Half-wave-Rectifier-Square-Wave_No_Parasitic.png";
            }
        else {
            if (parasitic == 1){
                if (waveform == 1)  im= "Diagram/full-wave-sin-parasitic.png";
                else  im= "Diagram/full-wave-square-para.png";
            }
            else {
                if (waveform == 1) im= "Diagram/full-wave-sin-nonpara.png";
                else  im = "Diagram/full-wave-square-nonpara.png";
            }
        }
        StdOut.println("Diagram of expect circuit should display! (Look at task bar for any blinking application) \n");
        DisplayImage image = new DisplayImage(im);
        

        if (parasitic == 1){
            StdOut.println("What is the inductance of the parasitic inductor (in H)?");
            L_c = StdIn.readDouble();
            StdOut.println("To confirm, parasitic inductance of " + L_c + "H or " + L_c * 1000 + " mH? 1 for Yes, 2 for No");
            input = StdIn.readInt();
            while (input != 1 && input != 2){
                StdOut.println("Please enter 1 (Yes) or 2 (No) ");
                input = StdIn.readInt();
                }

            while (input == 2){
                StdOut.println("What is the inductance of the parasitic inductor (in H)?");
                L_c = StdIn.readDouble();
                StdOut.println("To confirm, parasitic inductance of " + L_c + "H or " + L_c / 1000 + " mH? 1 for Yes, 2 for No");
                input = StdIn.readInt();
                while (input != 1 && input != 2){
                StdOut.println("Please enter 1 (Yes) or 2 (No) ");
                input = StdIn.readInt();
                }
            }
        }

        StdOut.println("What is the peak voltage (amplitude of the wave) in volts?");
        Vpk = StdIn.readDouble();
        StdOut.println("To confirm, peak voltage of " + Vpk + " volts? 1 for Yes, 2 for No");
            input = StdIn.readInt();
            while (input != 1 && input != 2){
                StdOut.println("Please enter 1 (Yes) or 2 (No) ");
                input = StdIn.readInt();
                }

            while (input == 2){
                StdOut.println("What is the peak voltage (amplitude of the wave) in volts?");
                Vpk = StdIn.readDouble();
                StdOut.println("To confirm, peak voltage of " + Vpk + " volts? 1 for Yes, 2 for No");
                input = StdIn.readInt();
                while (input != 1 && input != 2){
                StdOut.println("Please enter 1 (Yes) or 2 (No) ");
                input = StdIn.readInt();
                }
            }

        StdOut.println("What is frequency of the wave in Hz?");
        double f = StdIn.readDouble();
        w = f * 2 * Math.PI;
        StdOut.printf("To confirm, frequency of %3.4f Hz (which is %2.3f rad/s or %2.7fs period)? 1 for Yes, 2 for No\n", f, w, 1 / f);
        input = StdIn.readInt();
        while (input != 1 && input != 2){
            StdOut.println("Please enter 1 (Yes) or 2 (No) ");
            input = StdIn.readInt();
            }

        while (input == 2){
            StdOut.println("What is frequency of the wave in Hz?");
            f = StdIn.readDouble();
            w = f * 2 * Math.PI;
            StdOut.printf("To confirm, frequency of %3.4f Hz (which is %2.4f rad/s or %2.4fs period)? 1 for Yes, 2 for No\n", f, w, 1 / f);
            input = StdIn.readInt();
            while (input != 1 && input != 2){
            StdOut.println("Please enter 1 (Yes) or 2 (No) ");
            input = StdIn.readInt();
            }
        }

        StdOut.println("What is the output resistance (in ohms)?");
        R = StdIn.readDouble();
        StdOut.println("To confirm, output resistance of " + R + " ohm? 1 for Yes, 2 for No");
            input = StdIn.readInt();
            while (input != 1 && input != 2){
                StdOut.println("Please enter 1 (Yes) or 2 (No) ");
                input = StdIn.readInt();
                }

            while (input == 2){
                StdOut.println("What is the output resistance in ohms?");
                R = StdIn.readDouble();
                StdOut.println("To confirm, output resistance of " + R + " ohm? 1 for Yes, 2 for No");
                input = StdIn.readInt();
                while (input != 1 && input != 2){
                StdOut.println("Please enter 1 (Yes) or 2 (No) ");
                input = StdIn.readInt();
            }
        }

        if (parasitic == 2)
            nonParaComputeVout();
        else  
            paraComputeVout();
        
        StdOut.printf("Output DC Voltage: %2.4f volts\n", Vo);
        StdOut.printf("Output DC current: %2.4f volts\n", Io);
        if (parasitic == 1){
            double Vstore = Vo;
            StdOut.println("Without parasitic inductance, quantities would be:");
            nonParaComputeVout();
            StdOut.printf("\tOutput DC Voltage: %2.4f volts\n", Vo);
            StdOut.printf("\tOutput DC current: %2.4f volts\n", Io);
            StdOut.printf("With commutation, DC output voltage is %2.3f%s of voltage without commutation", Vstore / Vo * 100, "%");
        }
    }
     
}