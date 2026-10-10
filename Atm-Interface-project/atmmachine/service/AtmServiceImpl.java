package atmmachine.service;

import atmmachine.model.BankAccount;
import atmmachine.exception.AtmException;
import atmmachine.util.ConsoleLogger;
import java.util.HashMap;
import java.util.Map;

public class AtmServiceImpl implements AtmService {
    private final Map<String, BankAccount> accountDatabase = new HashMap<>();
    private final ConsoleLogger logger = new ConsoleLogger();
    private int accountIdCounter = 1001;

    private static final String RESET = "\u001B[0m";
    private static final String GREEN_TEXT = "\u001B[32m";

    public AtmServiceImpl() {
        BankAccount sample = new BankAccount("1000", "Admin Premium", "1234", 10000.0);
        accountDatabase.put("1000", sample);
        logger.writeSystemLog("SYSTEM_INIT", "MODULAR SERVICE LAYER", "SUCCESS");
    }

    @Override
    public void registerAccount(String name, String pin, double initialBalance) {
        String generatedAccNum = String.valueOf(accountIdCounter++);
        BankAccount newAccount = new BankAccount(generatedAccNum, name, pin, initialBalance);
        accountDatabase.put(generatedAccNum, newAccount);
        
        System.out.println(GREEN_TEXT + "┌─────────────────────────────────────────────────────────────┐");
        System.out.printf("│ [SUCCESS] ACCOUNT REGISTERED: %-29s │\n", name);
        System.out.printf("│  YOUR SYSTEM ACCOUNT NUMBER IS: %-27s │\n", generatedAccNum);
        System.out.println("└─────────────────────────────────────────────────────────────┘" + RESET);
        logger.writeSystemLog("ACC_REGISTER", "ACC: " + generatedAccNum, "SUCCESS");
    }

    @Override
    public BankAccount login(String accountNumber, String pin) throws AtmException {
        BankAccount account = accountDatabase.get(accountNumber);
        if (account == null || !account.authenticate(pin)) {
            logger.writeSystemLog("AUTH_FAILURE", "ACC: " + accountNumber, "DENIED");
            throw new AtmException("Invalid Account Number or Secure PIN Match Matrix.");
        }
        logger.writeSystemLog("AUTH_SUCCESS", "ACC: " + accountNumber, "GRANTED");
        return account;
    }

    @Override
    public void executeWithdrawal(BankAccount account, double amount) throws AtmException {
        if (amount <= 0 || amount > account.getBalance() || amount % 10 != 0) {
            logger.writeSystemLog("TX_WITHDRAW_ERR", "ACC: " + account.getAccountNumber(), "REJECTED");
            throw new AtmException("Incompatible withdrawal rule matching balance profile bounds.");
        }

        account.debit(amount);
        logger.writeSystemLog("TX_WITHDRAW_OK", "DISPENSED: $" + amount, "COMPLETED");

        int parsingBuffer = (int) amount;
        int notes100 = parsingBuffer / 100; parsingBuffer %= 100;
        int notes50  = parsingBuffer / 50;  parsingBuffer %= 50;
        int notes10  = parsingBuffer / 10;

        System.out.println(GREEN_TEXT + "┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ [SUCCESS] TRANSACTION STRUCT COMPLETE: DISPENSING CASH      │");
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.printf("│  Allocation Core $100 Denom Counter : %-22d │\n", notes100);
        System.out.printf("│  Allocation Core $50  Denom Counter : %-22d │\n", notes50);
        System.out.printf("│  Allocation Core $10  Denom Counter : %-22d │\n", notes10);
        System.out.println("└─────────────────────────────────────────────────────────────┘" + RESET);
    }

    @Override
    public void executeDeposit(BankAccount account, double amount) throws AtmException {
        if (amount <= 0) {
            logger.writeSystemLog("TX_DEPOSIT_ERR", "ACC: " + account.getAccountNumber(), "REJECTED");
            throw new AtmException("Vault operational standards require deposit values over $0.");
        }
        account.credit(amount);
        logger.writeSystemLog("TX_DEPOSIT_OK", "INGESTED: $" + amount, "COMPLETED");
        System.out.println(GREEN_TEXT + "┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ [SUCCESS] ASSET DISPATCH RECORD SAVED TO CENTRAL ACCOUNT    │");
        System.out.println("└─────────────────────────────────────────────────────────────┘" + RESET);
    }

    @Override
    public void executeTransfer(BankAccount sourceAccount, String targetAccountNumber, double amount) throws AtmException {
        BankAccount destAccount = accountDatabase.get(targetAccountNumber);
        if (destAccount == null || amount <= 0 || amount > sourceAccount.getBalance()) {
            logger.writeSystemLog("TX_XFER_ERR", "TRANSFER VALIDATION", "REJECTED");
            throw new AtmException("Destination target invalid or insufficient available liquidity.");
        }

        sourceAccount.debit(amount);
        destAccount.credit(amount);
        logger.writeSystemLog("TX_XFER_OK", "SRC: " + sourceAccount.getAccountNumber() + " -> DST: " + targetAccountNumber, "COMPLETED");
        
        System.out.println(GREEN_TEXT + "┌─────────────────────────────────────────────────────────────┐");
        System.out.printf("│ [SUCCESS] REMITTANCE SETTLED TO: %-26s │\n", destAccount.getAccountHolderName());
        System.out.printf("│  VOLUME TRANSFERRED SUCCESSFULLY: $%-24.2f │\n", amount);
        System.out.println("└─────────────────────────────────────────────────────────────┘" + RESET);
    }

    @Override
    public String getAuditTrace(int index) { return logger.fetchTelemetryRecord(index); }
}