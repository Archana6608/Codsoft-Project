package atmmachine.ui;

import atmmachine.model.BankAccount;

public class AtmInterfaceScreen {
    private static final String RESET = "\u001B[0m";
    private static final String PURPLE_TEXT = "\u001B[35m";
    private static final String CYAN_TEXT = "\u001B[36m";

    public static void renderWelcomeMenu() {
        System.out.println(PURPLE_TEXT + "┌──────────────────────────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(CYAN_TEXT + "│                     APEX QUANTUM BANK CENTRAL PORTAL                         │" + RESET);
        System.out.println(PURPLE_TEXT + "├──────────────────────────────────────────────────────────────────────────────┤" + RESET);
        System.out.println("│  [1] Open / Register New Core Bank Account System                            │");
        System.out.println("│  [2] Access Existing Session Pipeline Account (Login)                         │");
        System.out.println("│  [3] Safe System Shutdown / Power Down Terminal Grid                         │");
        System.out.println(PURPLE_TEXT + "└──────────────────────────────────────────────────────────────────────────────┘" + RESET);
    }

    public static void renderDashboardLayout(BankAccount account) {
        String sessionInfo = account.getAccountHolderName() + " (" + account.getAccountNumber() + ")";
        
        System.out.println(PURPLE_TEXT + "┌──────────────────────────────────────────────────────────────────────────────┐" + RESET);
        System.out.printf(CYAN_TEXT + "│ CORE TERMINAL CONTROLLER | ACTIVE USER SESSION: %-28s │\n", sessionInfo + RESET);
        System.out.println(PURPLE_TEXT + "└──────────────────────────────────────────────────────────────────────────────┘" + RESET);
        
        System.out.println("┌────────────────────────────────────────┐ ┌────────────────────────────────────────┐");
        System.out.println("│ [1] Balance Inquiry Verification       │ │ [2] Asset Dispense (Withdraw Cash)     │");
        System.out.println("└────────────────────────────────────────┘ └────────────────────────────────────────┘");
        
        System.out.println("┌────────────────────────────────────────┐ ┌────────────────────────────────────────┐");
        System.out.println("│ [3] Vault Ingestion (Deposit Cash)     │ │ [4] Remittance Dispatch (Wire Transfer)│");
        System.out.println("└────────────────────────────────────────┘ └────────────────────────────────────────┘");
        
        System.out.println("┌────────────────────────────────────────┐ ┌────────────────────────────────────────┐");
        System.out.println("│ [5] Telemetry Log Audit Tracks         │ │ [6] Secure Terminal Logout Session      │");
        System.out.println("└────────────────────────────────────────┘ └────────────────────────────────────────┘");
    }
}