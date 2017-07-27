import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Random;
public class Percolation {
    public static int n;
    public static int[] id;
    public static WeightedQuickUnionUF percol;
    public Percolation(int no) {
        n=no;
        id= new int [no*no+2];
        id[0]=id[no*no+1]=1;
        percol=new WeightedQuickUnionUF(no*no+2);
        }
  
    public int converter(int row, int column) {
        int a=(row-1)*n+column;
        return a;
    }
    public    void open(int row, int col)  {
        int b=converter(row, col);
        id[b]=1;
        if (b<=n) {
           percol.union(0,b);
            if (b!=1) {
                percol.union(b-1,b);
            } //out of efficiency
            if (b!=n) {
                percol.union(b+1,b); 
            }//out of neccesity
            percol.union(b+n,b);
        }
     else   if (b>n*n-n){
             percol.union(n*n+1,b);
            if (b!=n*n) {
                percol.union(b+1,b);
            }//out of efficiency
            if(b!=n*n-n+1){
            percol.union(b-1,b);
            }//out of neccesity
            percol.union(b-n,b);}
     else    {percol.union(b-n,b);
         percol.union(b+n,b);}
       if(!(col==n)){percol.union(b,b+1);}
       if(!(col==1)){percol.union(b,b-1);}
    }
    public boolean isOpen(int row, int col) {
        boolean r=(id[converter(row,col)]==1);
        return r; 
    }
    
    public boolean isFull(int row, int col) {
        int c=converter(row, col);
        boolean f=(percol.connected(c,0));
          return f;         }// is site (row, col) full?
   public     int numberOfOpenSites() {
       int j=0;
       for (int i=1;i <=n*n;i++) {
           if (id[i]==1) {j++;}
       }
       return j;
   }
   public boolean percolates()  {
       boolean perc=(percol.connected (0,n*n+1));
                     return perc;
                     }// does the system percolate?

   public static void main(String[] args) {
       int i=0;
       int n=StdIn.readInt();
       Percolation p=new Percolation(n);
       while(!p.percolates()) {
           Random rand = new Random();
           int r=1+rand.nextInt(50);
           int c=1+rand.nextInt(50);
           p.open(r,c);
           i++;
       }
       if(p.percolates()) {System.out.println("true");}
       System.out.println(i) ;
       
       
   }
   }