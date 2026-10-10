package atmmachine;

import atmmachine.model.BankAccount;
import atmmachine.service.AtmService;
import atmmachine.service.AtmServiceImpl;
import atmmachine.ui.AtmInterfaceScreen;
import atmmachine.exception.AtmException;
import java.util.Scanner;

public class Main {
    private static final String RESET = "\u001B[0m";
    private static final String CYAN_BG = "\u001B[46m\u001B[30m";
    private static final String RED_TEXT = "\u001B[31m";
    private static final String GREEN_TEXT = "\u001B[32m";
    private static final String YELLOW_TEXT = "\u001B[33m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AtmService atmService = new AtmServiceImpl();

        boolean shutdownTriggered = false;
        while (!shutdownTriggered) {
            AtmInterfaceScreen.renderWelcomeMenu();
            System.out.print(YELLOW_TEXT + "Execute System Level Action Target (1-3): " + RESET);
            String initialSelection = scanner.nextLine().trim();

            switch (initialSelection) {
                case "1": 
                    System.out.print(YELLOW_TEXT + "Enter Full Name of Account Registrant: " + RESET);
                    String name = scanner.nextLine().trim();
                    System.out.print(YELLOW_TEXT + "Create a 4-Digit Secure Verification Pass PIN: " + RESET);
                    String pin = scanner.nextLine().trim();
                    if (!pin.matches("\\d{4}")) {
                        System.out.println(RED_TEXT + "[ERROR] Layout validation failure. PIN must be 4 digits." + RESET);
                        break;
                    }
                    System.out.print(YELLOW_TEXT + "Enter Initial Capital Liquidity Opening Deposit: $" + RESET);
                    double initialDeposit = Double.parseDouble(scanner.nextLine());
                    atmService.registerAccount(name, pin, initialDeposit);
                    break;

                case "2": 
                    System.out.print(YELLOW_TEXT + "Provide Account Registry Target Identification ID: " + RESET);
                    String accNum = scanner.nextLine().trim();
                    System.out.print(YELLOW_TEXT + "Provide Secure PIN Verification Pattern Key: " + RESET);
                    String loginPin = scanner.nextLine().trim();

                    try {
                        BankAccount sessionAccount = atmService.login(accNum, loginPin);
                        boolean dashboardActive = true;
                        while (dashboardActive) {
                            AtmInterfaceScreen.renderDashboardLayout(sessionAccount);
                            System.out.print(YELLOW_TEXT + "Execute Directive Vector (1-6): " + RESET);
                            String vector = scanner.nextLine().trim();

                            try {
                                switch (vector) {
                                    case "1":
                                        System.out.println(GREEN_TEXT + "┌─────────────────────────────────────────────────────────────┐");
                                        System.out.printf("│ [BAL] CURRENT ACCOUNT CAPITALIZATION MARGIN: $%,-14.2f │\n", sessionAccount.getBalance());
                                        System.out.println("└─────────────────────────────────────────────────────────────┘" + RESET);
                                        break;
                                    case "2":
                                        System.out.print(YELLOW_TEXT + "Provide Value Parameter to Withdraw ($10 Multiples): $" + RESET);
                                        double wAmt = Double.parseDouble(scanner.nextLine());
                                        atmService.executeWithdrawal(sessionAccount, wAmt);
                                        break;
                                    case "3":
                                        System.out.print(YELLOW_TEXT + "Provide Allocation Reserves Value to Deposit: $" + RESET);
                                        double dAmt = Double.parseDouble(scanner.nextLine());
                                        atmService.executeDeposit(sessionAccount, dAmt);
                                        break;
                                    case "4":
                                        System.out.print(YELLOW_TEXT + "Enter Recipient Target Bank Account Registry Number: " + RESET);
                                        String recipient = scanner.nextLine().trim();
                                        System.out.print(YELLOW_TEXT + "Enter Wealth Volume Allocation to Dispatch: $" + RESET);
                                        double xferAmt = Double.parseDouble(scanner.nextLine());
                                        atmService.executeTransfer(sessionAccount, recipient, xferAmt);
                                        break;
                                    case "5":
                                        System.out.println("\n" + CYAN_BG + "┌─────────────────────────────────────────────────────────────┐" + RESET);
                                        System.out.println(CYAN_BG + "│             CORE RUNTIME TRACE TELEMETRY AUDIT              │" + RESET);
                                        System.out.println(CYAN_BG + "├─────────────────────────────────────────────────────────────┤" + RESET);
                                        for (int i = 0; i < 5; i++) {
                                            String trace = atmService.getAuditTrace(i);
                                            if (!trace.contains("EMPTY")) System.out.println(trace);
                                        }
                                        System.out.println(CYAN_BG + "└─────────────────────────────────────────────────────────────┘" + RESET + "\n");
                                        break;
                                    case "6":
                                        System.out.println(RED_TEXT + "┌─────────────────────────────────────────────────────────────┐");
                                        System.out.println("│ [TERMINATE] Active session trace safely destroyed. Logout.   │");
                                        System.out.println("└─────────────────────────────────────────────────────────────┘" + RESET);
                                        dashboardActive = false;
                                        break;
                                    default:
                                        System.out.println(RED_TEXT + "[DISCREPANCY] Operational vector out of range." + RESET);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(RED_TEXT + "[DISCREPANCY] Numeric analytical syntax processing failure." + RESET);
                            } catch (AtmException e) {
                                System.out.println(RED_TEXT + "[CORE_EXCEPTION] Vector Failure: " + e.getMessage() + RESET);
                            }
                        }
                    } catch (AtmException e) {
                        System.out.println(RED_TEXT + "[AUTH_EXCEPTION] Verification Matrix Lockout: " + e.getMessage() + RESET);
                    }
                    break;

                case "3": 
                    System.out.println(RED_TEXT + "SYSTEM DISCONNECTED. DISPATCH UTILITIES POWERED DOWN." + RESET);
                    shutdownTriggered = true;
                    break;

                default:
                    System.out.println(RED_TEXT + "Selection pattern evaluated outside main metrics bounds." + RESET);
            }
        }
        scanner.close();
    }
}