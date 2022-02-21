import java.util.*;

public class Program1 {
	static void findTriplets(int a[], int n)
    {
		int y=0;
        for (int i=0;i<n-1;i++) {
        	HashSet<Integer> s = new HashSet<>();
        	for(int j=0;j<n;j++) {
        		int x = a[i] + a[j];
        		if(s.contains(x)) {
        			System.out.println("a[i]:"+a[i]+", a[j]:"+a[j]+", x:"+x);
        			y++;
        		}
        		else {
        			s.add(a[j]);
        		}
        	}
        }
        System.out.print("Number of triplets: "+y);
    }
    public static void main(String[] args)
    {
        int a[] = {1,5,3,2};
        int n = a.length;
        findTriplets(a, n);
    }
}
