public class DiodeRectifier{
    // 1 for half wave, 2 for full wave
    private static int isFullWave; 
    // 1 for sin wave, 2 for square wave
    private static int waveform; 
    private static double Vpk;
    private static double w;
    // is there a parasitic Inductance
    private static int parasitic;
    //
    private static double L_c;
    private static double R;
    private static double Vo;
    private static double Io;

    private static double paraComputeVout() {
        double Vout = 0;

        if (waveform == 1) {
            Vout = Vs / pi * (1 - w * L_c )
        }
        else {
            Vout = Vpk / 2;
        }

        if (isFullWave == 2) Vout *= 2; // double for full wave
        Vo = Vout;
        Io = Vo / R; // ohm's law
        return Vout;
    }
    
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

    public static void main(String[] args) {
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
            StdOut.println("Please enter 1 (Yes) or 2 (No)");
            input = StdIn.readInt();
        }
        waveform = input;

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

        StdOut.println("Is there any parasitic inductance? 1 for Yes, 2 for No");
        input = StdIn.readInt();
        while (input != 1 && input != 2){
            StdOut.println("Please enter 1 (Yes) or 2 (No)");
            input = StdIn.readInt();
        }
        parasitic = input;

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
        
    }
     
}