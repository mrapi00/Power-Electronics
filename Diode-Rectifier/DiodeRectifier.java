/******************************************************************************
 *  Author: Mahmudul Rapi
 *  Title: Diode Rectifier Circuit Analysis
 *  Compilation:  javac StdIn.java
 *  Execution:    java StdIn   (interactive test of basic functionality)
 *  Dependencies: StdIn.java, StdOut.java, DisplayImage.java, Diagram Folder
 *
 *  Diode rectifier circuit converts an AC input voltage into a DC output voltage.
 *  With filtering, we can obtain a clean DC output voltage. Asks user to fill
 *  in parameter of diode rectifier which includes if circuit is full-wave
 *  rectifier or half-wave rectifier, sin wave or square wave voltage source, 
 *  frequency of the wave, peak voltage, parasitic inductance (if applies), 
 *  and output resistance. Program outputs the output voltage and current
 *  of the circuit. 
 *
 ******************************************************************************/

import java.io.IOException;

public class DiodeRectifier{
    // 1 for half wave, 2 for full wave
    private static int isFullWave; 
    // 1 for sin wave, 2 for square wave
    private static int waveform; 
    // user input peak voltage
    private static double Vpk;
    // user input angular frequency (rad/s)
    private static double w;
    // is there a parasitic Inductance (for commutation)
    private static int parasitic;
    // parasitic inductance
    private static double L_c;
    // output resistance
    private static double R;
    // output voltage
    private static double Vo;
    //output current
    private static double Io;

    // compute and set output voltage with parasitic inductance
    private static double paraComputeVout() {
        double Vout = 0;

        if (waveform == 1) {
            Vout = Vpk / (Math.PI + w * L_c / (2 * R));
        }
        else {
            double T = 2 * Math.PI / w;
            Vout = Vpk / (2 * (1 + L_c / (R * T)));
        }

        if (isFullWave == 2) Vout *= 2; // double for full wave
        Vo = Vout;
        Io = Vo / R; // ohm's law
        return Vout;
    }

    // compute and set output voltage with no parasitic inductance
    private static double nonParaComputeVout() {
        double Vout = 0;

        if (waveform == 1) {
            Vout = Vpk / Math.PI;
        }
        else {
            Vout = Vpk / 2;
        }

        if (isFullWave == 2) Vout *= 2; // double for full wave
        Vo = Vout;
        Io = Vo / R; // ohm's law
        return Vout;
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

        StdOut.println("Image of Circuit (based on specification) should pop up now!\n");
        StdOut.println("What is the peak voltage (amplitude of the wave) in volts");
        Vpk = StdIn.readDouble();
        StdOut.println("To confirm, peak voltage of " + Vpk + " volts? 1 for Yes, 2 for No");
            input = StdIn.readInt();
            while (input != 1 && input != 2){
                StdOut.println("Please enter 1 (Yes) or 2 (No) ");
                input = StdIn.readInt();
                }

            while (input == 2){
                StdOut.println("What is the peak voltage (amplitude of the wave) in volts");
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

        if (parasitic == 1)
            nonParaComputeVout();
        else  
            paraComputeVout();
        
        
        StdOut.printf("Output DC Voltage: %2.4f volts\n", Vo);
        StdOut.printf("Output DC current: %2.4f volts\n", Io);
    }
     
}