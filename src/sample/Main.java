package sample;

public class Main {
    int counter = 1;
    static int N;

    public void printFibbonaciNumber()
    {
        synchronized (this)
        {
            int n1=0,n2=1,n3,i,count=10;
            System.out.print(n1+" "+n2);

            for(i=2;i<count;++i){
                n3=n1+n2;

                System.out.print(" "+n3);
                n1=n2;
                n2=n3;
                try {
                    wait();
                }
                catch (
                        InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(counter + " ");

            counter++;

            notify();
        }
    }


    public void printEvenNumber()
    {
        synchronized (this)
        { int limit = 50;

            System.out.println("Printing Even numbers between  " + limit);

            for(int i=1; i <= limit; i++){
                if( i % 2 == 0){
                    System.out.print(i + " ");
                    try {
                        wait();
                    }
                    catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(
                        counter + " ");
                counter++;
                notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {

        N = 10;
        Main mt = new Main();


        Thread t1 = new Thread(new Runnable() {
            public void run()
            {
                mt.printEvenNumber();
            }
        });


        Thread t2 = new Thread(new Runnable() {
            public void run()
            {
                mt.printFibbonaciNumber();
            }
        });
        t1.sleep(2000);
        t1.start();
        t2.sleep(3000);
        t2.start();

    }
}