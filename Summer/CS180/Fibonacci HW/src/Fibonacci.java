import java.util.Scanner;

/**
 * A program to generate Fibonacci numbers
 *
 * <p>Purdue University -- CS18000 -- Fall 2019 -- HW4</p>
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version July 4, 2019
 */

public class Fibonacci {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        do{
            System.out.println("Enter an Option:\n\t A for seq\n\t B for matrix\n\t C for fibonacci string");
            String input = s.nextLine();

            if(input.equals("A")){
                System.out.println("Enter 0 to exit\nEnter Sequence:");
                int num = s.nextInt();

                while(num>0){

                    int aux1 = 0;
                    int aux2 = 1;

                    for(int i=1;i<num;i++){
                        int temp = aux1;
                        aux1 += aux2;
                        aux2 = temp;
                    }

                    System.out.println(aux1);

                    num = s.nextInt();
                    s.nextLine();
                }

            }else if(input.equals("B")){
                System.out.println("Enter Dimensions:");
                int m = s.nextInt();
                int n = s.nextInt();
                s.nextLine();

                int sum = 0;
                int prev = 1;

                for(int i=0;i<m;i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(sum + " ");
                        int temp = sum;
                        sum += prev;
                        prev = temp;
                    }
                    System.out.println();
                }
            }else if(input.equals("C")){
                System.out.println("Enter Fibonacci String:");
                String in = s.nextLine();

                for(int i=in.length()-1;i>=0;i--){
                    if(in.charAt(i) =='0') System.out.print("1");
                    else System.out.print("0");
                }

                System.out.println();
            }
            else{
                System.out.println("Input not valid");
            }
            System.out.println("Press Y to run again");

            String cont = s.nextLine();

            if(cont.equals("Y")) continue;
            else break;

        }while (true);
    }
}

