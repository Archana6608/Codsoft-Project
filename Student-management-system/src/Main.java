import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        StudentService svc = new StudentService();
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\n\u001B[1;35m--- STUDENT MANAGEMENT SYSTEM ---\u001B[0m");
            System.out.println("[1] Add [2] Search [3] List [4] Sort [5] Remove [6] Exit");
            String choice = sc.nextLine();
            if(choice.equals("1")) {
                System.out.print("Enter (Name,Roll,Grade): ");
                String[] d = sc.nextLine().split(",");
                if(d.length == 3) svc.add(d[0], d[1], d[2]);
            } else if(choice.equals("2")) { System.out.print("Roll: "); svc.search(sc.nextLine()); }
            else if(choice.equals("3")) svc.showAll();
            else if(choice.equals("4")) svc.sortByName();
            else if(choice.equals("5")) { System.out.print("Roll to remove: "); svc.remove(sc.nextLine()); }
            else break;
        }
    }
}
