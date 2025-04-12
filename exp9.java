import java.util.Scanner;
class Stk {
    int top, msz;
    int[] arr;
    public Stk(int sze) {
        msz = sze;
        arr = new int[msz];
        top = -1;
    }
    public void pus(int itm) {
        if (top == msz - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = itm;
        System.out.println("Pushed: " + itm);
    }
    public int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return -1;
        }
        int itm = arr[top--];
        System.out.println("Popped element is: " + itm);
        return itm;
    }
    public void dsp() {
        if (top < 0) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("The elements in the stack are: ");
        for (int i = top; i >= 0; i--)
            System.out.println(arr[i]);
    }
}
class exp9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the stack: ");
        int siz = sc.nextInt();
        Stk stk = new Stk(siz);
        System.out.println("Stack Operations: ");
        System.out.println("1.Push");
        System.out.println("2.Pop");
        System.out.println("3.Display Stack");
        System.out.println("4.Exit");
        int opt;
        do {
        	System.out.println("Choose an option: ");
       		opt = sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.print("Enter value to be pushed: ");
                    int val = sc.nextInt();
                    stk.pus(val);
                    break;
                case 2:
                    stk.pop();
                    break;
                case 3:
                    stk.dsp();
                    break;
                case 4:
                    System.out.println("EXIT.");
                    break;
                default:
                    System.out.println("Please enter a valid choice: ");
            }
        } while(opt!=4);
    }
}
