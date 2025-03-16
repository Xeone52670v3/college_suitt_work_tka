                import java.util.Scanner;

                public class tkachuk {

                    public static void main(String[] args) {
                        Scanner scn = new Scanner(System.in);
                        System.out.print("Enter true number: ");
                        int intVal = scn.nextInt();
                        System.out.print("Enter a floating point number: ");
                        double floatVal = scn.nextDouble();
                        scn.nextLine();  
                        System.out.print("Add line: ");
                        String strVal = scn.nextLine();
                        System.out.print("Enter 0 or 1 ");
                        boolean boolVal = scn.nextInt() == 1;
                        System.out.println("\nOld format style (String.format):");
                        System.out.println(String.format("Integer: %d, hexadecimal: %x, octal: %o", intVal, intVal, intVal));
                        System.out.println(String.format("Enter a floating point number: %.2f", floatVal));
                        System.out.println(String.format("Line: %.5s", strVal));
                        System.out.println(String.format("Logical value: %b", boolVal));
                        System.out.println("\nMethod System.out.printf:");
                        System.out.printf("Integer: %d, hexadecimal: %x, octal: %o%n", intVal, intVal, intVal);
                        System.out.printf("Floating point number: %.2f%n", floatVal);
                        System.out.printf("Line: %.10s%n", strVal);
                        System.out.printf("Logical value: %b%n", boolVal);
                        System.out.println("\nNormal concatenation:");
                        System.out.println("Integer: " + intVal + ", hexadecimal: " + Integer.toHexString(intVal) +
                                ", octal: " + Integer.toOctalString(intVal));
                        System.out.println("Floating point number: " + String.format("%.2f", floatVal));
                        System.out.println("Line: " + strVal.substring(0, Math.min(7, strVal.length()))); 
                        System.out.println("Logical value: " + boolVal);
                    }
                }
