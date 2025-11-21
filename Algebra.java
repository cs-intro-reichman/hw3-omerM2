// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  
	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		int a = x1;
        int b = x2;

        if (b > 0) {
            while (b != 0) {
                a++;
                b--;
            }
        } else {
            while (b != 0) {
                a--;
                b++;
            }
        }
        return a;
    }


	// Returns x1 - x2
	public static int minus(int x1, int x2) {
        int t = x2;
        int neg = 0;

        if (t > 0) {
            while (t != 0) {
                neg--;
                t--;
            }
        } else {
            while (t != 0) {
                neg++;
                t++;
            }
        }

        return plus(x1, neg);
    }

	// Returns x1 * x2
	public static int times(int x1, int x2) {
        boolean neg = false;

        int a = x1;
        int b = x2;

        // abs(a)
        if (a < 0) {
            neg = !neg;
            int t = a;
            a = 0;
            while (t != 0) {
                a++;
                t++;
            }
        }

        // abs(b)
        if (b < 0) {
            neg = !neg;
            int t = b;
            b = 0;
            while (t != 0) {
                b++;
                t++;
            }
        }

        int res = 0;
        int i = 0;
        while (i < b) {
            res = plus(res, a);
            i++;
        }

        if (neg) {
            int t = res;
            res = 0;
            if (t > 0) {
                while (t != 0) {
                    res--;
                    t--;
                }
            } else {
                while (t != 0) {
                    res++;
                    t++;
                }
            }
        }

        return res;
    }


	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int sumPow = 1;
		for(int i = 0 ; i < n ; i++ ){
			sumPow = times(sumPow, x);
		}
		return sumPow;
	}

	public static int div(int x1, int x2) {
boolean neg = false;

        int a = x1;
        int b = x2;

        // abs(a)
        if (a < 0) {
            neg = !neg;
            int t = a;
            a = 0;
            while (t != 0) {
                a++;
                t++;
            }
        }

        // abs(b)
        if (b < 0) {
            neg = !neg;
            int t = b;
            b = 0;
            while (t != 0) {
                b++;
                t++;
            }
        }

        int q = 0;
        int rem = a;

        while (rem >= b) {
            rem = minus(rem, b);
            q++;
        }

        if (neg) {
            int t = q;
            q = 0;
            while (t != 0) {
                q--;
                t--;
            }
        }

        return q;
    }


	public static int mod(int x1, int x2) {
			
		return minus(x1,times(div(x1,x2),x2));

		}
		

	// Returns the integer part of sqrt(x) 
public static int sqrt(int x) {
 		int count = 0;
        int odd = 1;
        int acc = 0;

        while (true) {
            acc = plus(acc, odd);
            if (acc > x) break;
            count++;
            odd = plus(odd, 2);
        }

        return count;
}
  	  
}