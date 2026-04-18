import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        String vvod = vzVvod(scn);
        
        System.out.println("Choose function:");
        System.out.println("1. Flip all line");
        System.out.println("2. Flip every word");
        int vibor = scn.nextInt();
        scn.nextLine();
        
        if (vibor == 1) {
            System.out.println("Flipped line: " + obratnaStroka(vvod));
        } else if (vibor == 2) {
            System.out.println("Flipped words: " + obratniSlova(vvod));
        } else {
            System.out.println("Incorrect choose");
        }
        
        scn.close();
    }

    public static String vzVvod(Scanner scanner) {
        String vvod;
        while (true) {
            System.out.print("Enter line with 2 words");
            vvod = scanner.nextLine();
            String[] slova = vvod.split(" ");
            if (slova.length >= 2 && slovaCheck(slova)) {
                break;
            } else {
                System.out.println("Line needs at least 2 words");
            }
        }
        return vvod;
    }

    private static boolean slovaCheck(String[] slova) {
        for (String word : slova) {
            if (word.length() < 3) {
                return false;
            }
        }
        return true;
    }

    public static String obratnaStroka(String vvod) {
        char[] sym = vvod.toCharArray();
        int levo = 0, pravo = sym.length - 1;
        while (levo < pravo) {
            char temp = sym[levo];
            sym[levo] = sym[pravo];
            sym[pravo] = temp;
            levo++;
            pravo--;
        }
        return new String(sym);
    }

    public static String obratniSlova(String vvod) {
        String[] slova = vvod.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (String word : slova) {
            reversed.append(obratnaStroka(word)).append(" ");
        }
        return reversed.toString().trim();
    }
}
