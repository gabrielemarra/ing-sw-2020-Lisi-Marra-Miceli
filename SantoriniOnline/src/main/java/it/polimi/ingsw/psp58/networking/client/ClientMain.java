package it.polimi.ingsw.psp58.networking.client;

import it.polimi.ingsw.psp58.view.UI.CLI.CLIView;
import it.polimi.ingsw.psp58.view.UI.GUI.GUI;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {
        boolean viewModeArg = false;
        boolean enablePing = true;

        int viewModeChoice = -1;

        ArrayList<String> argsToForward = new ArrayList<>();

        if (args != null) {
            for (String currentArgument : args) {
                switch (currentArgument) {
                    case "-pingOff":
                        argsToForward.add("-ping off");
                        enablePing = false;
                        System.out.println("CLIENT SETTINGS: <Ping System> OFF");
                        break;
                    case "-cli":
                        if (!viewModeArg) {
                            viewModeArg = true;
                            viewModeChoice = 1;
                            System.out.println("CLIENT SETTINGS: <CLI> ON");
                        } else {
//                            Double mode selected - choice during runtime
                            viewModeArg = false;
                            viewModeChoice = -1;
                        }
                        break;
                    case "-gui":
                        if (!viewModeArg) {
                            viewModeArg = true;
                            viewModeChoice = 2;
                            System.out.println("CLIENT SETTINGS: <GUI> ON");
                        } else {
//                            Double mode selected - choice during runtime
                            viewModeArg = false;
                            viewModeChoice = -1;
                        }
                        break;
                }
            }
        }

        if (!viewModeArg) {
            System.out.println("Please enter the interface you would like to play with");
            System.out.println("1. CLI");
            System.out.println("2. GUI (Suggested)");
            Scanner input = new Scanner(System.in);

            boolean continueInput = true;
            do {
                try {
                    viewModeChoice = input.nextInt();
                    if (viewModeChoice != 1 && viewModeChoice != 2) {
                        System.out.println("ERROR: please insert a valid integer.");
                    } else {
                        continueInput = false;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("ERROR: please insert a valid integer.");
                }

            }
            while (continueInput);
        }

        String[] argsString = new String[argsToForward.size()];
        argsToForward.toArray(argsString);

        switch (viewModeChoice) {
            case 1:
                //starts the cli
                CLIView cli = new CLIView(argsString);
                cli.start();
                break;
            case 2:
                Application.launch(GUI.class, argsString);
                break;
            default:
                System.out.println("ERROR: please insert a valid integer.");
                System.exit(0);
                break;
        }
    }
}
