package view.UI.CLI.utility;

import auxiliary.ANSIColors;
import view.UI.CLI.CLIView;

import java.io.PrintStream;

public class MessageUtility {

    //Called both by invalid username already taken (via server) or invalid username not alphanumeric
    public static void displayErrorMessage(String errorMessage) {
        System.out.println(ANSIColors.ANSI_RED + "⚠ ERROR: "  + errorMessage + ANSIColors.ANSI_RESET);

    }

    /**
     * prints (message) is valid in green
     *
     * @param message
     */
    public static void printValidMessage(String message) {
        System.out.println(ANSIColors.ANSI_GREEN + "✓ " + message + ANSIColors.ANSI_RESET);
    }

    public static void setBackground() {
        //System.out.println("\033[40m\033[30m");


    }
    public static void  displayTitle() {

        CLIView.clearScreen();
        PrintStream output = (PrintStream) System.out;
        output.println("\n\t\t\t\t\tWELCOME TO:\t\t");
        output.println("  __   ___  __  __ ______   ___   ____  __ __  __ __\n" +
                " (( \\ // \\\\ ||\\ || | || |  // \\\\  || \\\\ || ||\\ || ||\n" +
                "  \\\\  ||=|| ||\\\\||   ||   ((   )) ||_// || ||\\\\|| ||\n" +
                " \\_)) || || || \\||   ||    \\\\_//  || \\\\ || || \\|| ||\n" +
                "                                                    ");

        output.println(" _______  _        _       _________ _        _______ \n" +
                "(  ___  )( (    /|( \\      \\__   __/( (    /|(  ____ \\\n" +
                "| (   ) ||  \\  ( || (         ) (   |  \\  ( || (    \\/\n" +
                "| |   | ||   \\ | || |         | |   |   \\ | || (__    \n" +
                "| |   | || (\\ \\) || |         | |   | (\\ \\) ||  __)   \n" +
                "| |   | || | \\   || |         | |   | | \\   || (      \n" +
                "| (___) || )  \\  || (____/\\___) (___| )  \\  || (____/\\\n" +
                "(_______)|/    )_)(_______/\\_______/|/    )_)(_______/\n" +
                "                                                      ");


    }

    public static void bigTitle() {
        System.out.println(ANSIColors.CYAN_BRIGHT);
        System.out.println("                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "             ▒░░░░░░░░░░░░░░▒                                                                   ░▒▒▒▒▒░                                      ▒░░░░░░▒           \n" +
                "          ░░░░░   ░░░ ░░░░ ░░░░░                          ▒▒░░░░░░░░░░░▒░                   ░░░░  ░  ░░░░░░░░░░░░░░░░░░░░░                ░░░░ ▒ ░░ ░░░░░       \n" +
                "         ░░░░ ░ ░░▓▓▓▓▓▓   ░ ░░░░░░              ░▒░░░░░░░░░░     ░   ░░░░░░░░░░░░▒      ░░░░░   ░░░░▒░░░░░░░░▒░░▒    ░ ░░░░░         ▒░░░░░ ░░░  ░░░ ░░░░      \n" +
                "       ░░░░░ ▒░░▓▓▓ ▓ ▒ ▓▓░ ░ ░░░░░░░  ░░░░░░░░░░░░░ ░░░░░░░░░░  ░░░░         ░░ ░░░░░░░░░░░░░░░ ▓▓▓▓ ░░ ▓▓▓▓░░▒▒░ ▓▓▒░░░░░░░░░░░░░░░  ░░░░░▒▒ ▓▓▓▓  ░░░░░░     \n" +
                "       ░░░░░░  ▓▓▓▓  ░  ▓▓▓ ░░░░░░  ░░░░▒░  ░░░░░░ ░░░ ▓▓▓ ░░░▒▓▓▓▓▓▓▓▓▓▓▓▓▒░   ░░      ░░░ ░░░ ▓▓▓▓▓▓▓  ▓▓▓▓▓▓   ▓▓▓▓▒░░  ░  ░░░ ░░░░░░░░ ░ ░  ▓▓    ░░░░░░    \n" +
                "      ▒░░░░░░  ▓▓▓▓         ░ ░░░ ░░▓▓▓▓▓ ░░░░▓  ░░░ ▓▓ ▓▓▓▓ ░      ▒▓▓▓▓ ▒▒▒▒▒  ░░░░  ░░░░░ ▓▓▓▓▓▓▓▓    ▓▓▓▓▓▓▓     ▓ ░░░░░░░░░░░░ ▓▓▓▓  ░░░░         ░░░░░    \n" +
                "      ░░░░░░    ▓▓▓▓         ░░░ ░░ ▓▓▓▓▓▓ ░░ ▓▓▓▓  ▓   ▓▓▓▓▒ ░ ░   ▓▓▓▓▓         ▒    ▓ ▓▓     ▓▓▓▓▓    ▒▓▓▓▓▓▓   ▒▓▓  ░  ▓▓▓ ░░ ▓▓ ▓▓▓▓▓      ▓▓▓▓   ░░░░░▒   \n" +
                "      ░░░░░░░    ▓▓▓▓    ░░░░░░░ ░ ▓▓▓▒▓▓▓  ░  ▓▓▓▓▓    ▓▓▓▓▓       ▓▓▓▓▓   ░   ▓▓▓▓       ▓▓   ▓▓▓▓▓    ▓▓▓▓▓     ▓▓▓▓    ▓▓▓▓  ▓    ▓▓▓▓▒     ▓▓▓▓   ░░░░░░   \n" +
                "      ▒░░░░░░     ▓▓▓▓▓    ░░░░░   ▓▓▓ ▓▓▓▓    ▓▓▓▓▓     ▓▓▓▓▒      ▓▓▓▓▓     ▓▓▓▓▓▓▓▓▓▓   ▓▓▒  ▓▓▓▓▓    ▓▓▓▓      ▓▓▓▓    ▓▓▓▓▓▓     ▓▓▓▓▓     ▓▓▓▓   ░░░░░░   \n" +
                "       ░░░░░░░     ▓▓▓▓▓    ░░░   ▓▓▓░ ▓▓▓▓    ▒▓▓▓▓    ▒▓▓▓▓▓     ▒▓▓▓▓▒    ▓▓▓▓▓    ▓▓▓  ▓▓▓  ▓▓▓▓▓   ▓▓▓        ▓▓▓▓    ▓▓▓▓▓     ▓▓▓▓▓▓    ▓▓▓▓▓   ░░░░░░   \n" +
                "       ░░░░░░░░░░   ▓▓▓▓▓▓    ░   ▓▓▓▒ ▓▓▓▓▓    ▓▓▓▓    ▓▓▓▓▓▓     ▓▓▓▓▓    ▓▓▓▓▓      ▓▓  ▓▓▓░ ▓▓▓▓▓░▓▓▓          ▓▓▓▓    ▓▓▓▓▓     ▓▓▓▓▓▓    ▒▓▓▓▓   ░░░░░░   \n" +
                "      ░░░░░         ░▓▓▓▓▓▓▒      ▓▓▓   ▓▓▓▓    ▓▓▓▓     ▓▓▓▓▓     ▓▓▓▓▓   ▓▓▓▓▓▓      ▓▓  ▓▓▓▓ ▓▓▓▓▓▓▓▓▓▓         ▓▓▓▓    ▓▓▓▓▓     ▓▓▓▓▓▓    ▓▓▓▓▓   ░░░░░░   \n" +
                "     ░░░░    ▒▓       ▓▓▓▓▓▓▓    ▓▓▓▓   ▓▓▓▓▓   ▓▓▓▓▓    ▓▓▓▓▓     ▓▓▓▓▓  ░▓▓▓▓▓       ▓▓  ▓▓▓░ ▓▓▓▓▓   ▓▓▓▓       ▓▓▓▓    ▓▓▓▓▓     ▓▓▓▓▓▓    ▓▓▓▓▓   ░░░░░░   \n" +
                "    ░░░░   ▓▓▓▓        ▓▓▓▓▓▓▓   ▓▓▓▓   ▓▓▓▓▓   ▒▓▓▓▓    ▓▓▓▓▓░   ▒▓▓▓▓▓  ▓▓▓▓▓▓  ▓▓▓ ░▓  ▓▓▓▓  ▓▓▓▓▓   ░▓▓▓▓   ░  ▓▓▓▓░   ▓▓▓▓▓     ▓▓▓▓▓▓    ▓▓▓▓▓   ░░░░░░   \n" +
                "   ░░░░░   ▓▓▓▓   ░░    ▓▓▓▓▓▓▓  ▓▓▓▓   ░▓▓▓▓▓   ▓▓▓▓    ▓▓▓▓▓▓   ▓▓▓▓▓▓  ▓▓▓▓▓▓  ▓▓▓▓    ▓▓▓░ ▓▓▓▓▓▓     ▓▓▓▓     ▓▓▓▓▓   ▓▓▓▓▓     ▓▓▓▓▓▓    ▓▓▓▓▓   ░░░░░░   \n" +
                "   ░░░░░   ▒▓▓▓▓    ░   ▓▓▓▓▓▓▓  ▓▓▓▓    ▓▓▓▓▓   ▓▓▓▓    ▒▓▓▓▓▓▒  ▓▓▓▓▓▓  ▒▓▓▓▓▓    ▓    ▓▓▓   ▒▓▓▓▓▓     ▓▓▓▓▓    ▓▓▓▓▓    ▓▓▓▓     ▓▓▓▓▓▓    ▓▓▓▓▓   ░░░░░░   \n" +
                "  ░░░░░░░   ▓▓▓▓▓   ░░  ▓▓▓▓▓▓▓  ▓▓▓▒     ▓▓▓▓▓  ▒▓▓▓▒      ▓▓▓   ▓▓▓▓▓▓    ▓▓▓▓▓     ░ ▓▓▓    ▓▓▓▓▓▓     ▒▓▓▓▓▓   ▓▓▓▓▓    ▓▓▓▓     ▓▓▓▓▓▓    ▓▓▓▓▓▒ ░░ ░░░░░  \n" +
                "   ░░░░░░    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ▓▓▓▓░ ░ ░ ▒▓▓▓▓▓                  ▓▓▓▓▓▓      ▓▓▓▓  ░░ ▓▓▓      ▓▓▓▓▓▓     ▓▓▓▓▓▓   ▓▓▓ ▒  ░▓▓▓▓     ▓▓▓▓▓▓   ▒▓▓▓▓▓   ░ ░░░░▒  \n" +
                "   ░░░░░░░     ▓▓▓▓▓▓▓▓▓▓▓▓▓    ▒ ▓▓ ░░  ░   ▓▓▓▓  ░  ▒▒▒▒▒       ▓▓▓▓▓▓       ▓  ▓▓▓▓▓▓   ░  ░   ▒▒   ░ ░ ▒▓▓▓▓▓▒       ░  ▓▓▓▓  ░░ ▓▓▓▓▓▓▓▓   ▒▓   ░░ ░░░░░░  \n" +
                "   ░░░░░░░░░     ▓        ▒   ░░░   ░░ ░░ ░░  ▒▒  ░ ░░░░░░░░░░░ ░   ▓▓▓▓  ░              ░░ ▒░▒ ░░░░░░ ░░░░   ▓▓    ░   ▒ ░░     ░  ░  ▒      ░░░░░░░ ░░░░░░░░  \n" +
                "    ░░░░░░░░░░   ░     ░░░░░ ░▒▒     ░░░░░░ ░░░░░░ ░░░░░░░░░░░░░▒░░░     ░ ▒▒▒▒░  ░░░░░  ░░░░░░░░░░░░░░░░░░░░     ░░▒░░░░░░  ░░░ ░▒▒░ ░░░  ░░░▒▒░░░░░░░░░░░░░░  \n" +
                "     ░░░░░░░░░░░░░░░░▒░▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░  ░░░ ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░    ▒░░░░░░░░░░░░░░░░░░░░░░░░▒░░░░░░░░░░░░░░░░░▒   \n" +
                "      ▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░    \n" +
                "        ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒░▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░       \n" +
                "          ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒       ▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒░░▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒           \n" +
                "              ░▒░░░░░░░░░░░░░▒░              ░▒▒░              ░░░░░░░░░░░░░▒░    ░░▒░░                  ▒░░░░░░░░░░▒░             ░▒░░░░░░░░░▒                 \n" +
                "                                                                                                                                                                ");



    }

    public static void online() {
        System.out.println(" _______  _        _       _________ _        _______ \n" +
                "(  ___  )( (    /|( \\      \\__   __/( (    /|(  ____ \\\n" +
                "| (   ) ||  \\  ( || (         ) (   |  \\  ( || (    \\/\n" +
                "| |   | ||   \\ | || |         | |   |   \\ | || (__    \n" +
                "| |   | || (\\ \\) || |         | |   | (\\ \\) ||  __)   \n" +
                "| |   | || | \\   || |         | |   | | \\   || (      \n" +
                "| (___) || )  \\  || (____/\\___) (___| )  \\  || (____/\\\n" +
                "(_______)|/    )_)(_______/\\_______/|/    )_)(_______/\n" +
                "                                                      ");


    }

    public static void printDivider() {
        for (int i = 0; i < 11; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }



}
