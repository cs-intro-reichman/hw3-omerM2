// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		// i need to create a function that creates a calculation of the end balance
		double balance;
		balance = loan;
		for(int i =0; i< n; i ++){
			balance = (balance - payment)*(1+0.01*rate);
		}
		// Replace the following statement with your code

		return balance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {

		double payment = loan / n;
		iterationCounter = 0;
		while (endBalance(loan, rate, n, payment)>0) {
			payment = payment + epsilon;
			iterationCounter +=1;
		}
		// Replace the following statement with your code
		return payment;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;
		double L = loan / n;
		double H = loan;
		double fL= endBalance(loan, rate, n, L);
		double fH = endBalance(loan, rate, n, H);
		while (fH>0) {
			H++;
			fH = endBalance(loan, rate, n, H);
		}
		
		double G = (L + H) / 2.0;
		while ((H-L) >= epsilon) {
			fL= endBalance(loan, rate, n, L);
			fH = endBalance(loan, rate, n, H);
			double fG = endBalance(loan, rate, n, G);

			if (fL*fG > 0){
				L = G ;
				G = (L + H) / 2 ;
				iterationCounter++;

			}else{
				H = G ;
				G = (L + H) / 2 ;
				iterationCounter++;
			}
			
		}
			
        // Replace the following statement with your code
		return G;
	}
}
