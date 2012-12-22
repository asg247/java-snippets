package benblack86.pattern;

public class Strategy {
	public static void main(String[] args) {
		Person normal = new Person(10000000, new DefaultTaxStrategy<Person>());
		Person dodger = new Person(10000000, new DodgingTaxStrategy<Person>());
		Trust nonProfit = new Trust(10000000, true, new TrustTaxStrategy());
		Trust forPorift = new Trust(10000000, false, new TrustTaxStrategy());
		
		System.out.println(normal.computeTax());
		System.out.println(dodger.computeTax());
		System.out.println(nonProfit.computeTax());
		System.out.println(forPorift.computeTax());
	}
}

abstract class TaxPayer<P extends TaxPayer<P>> {
	private long income;
	private TaxStrategy<P> strategy;
	
	public TaxPayer(long income, TaxStrategy<P> strategy) {
		this.income = income;
		this.strategy = strategy;
	}
	
	protected abstract P getThis();
	
	public long getIncome() {
		return income;
	}
	
	public long computeTax() {
		return strategy.computeTax(getThis());
	}
}

interface TaxStrategy<P extends TaxPayer<P>> {
	public long computeTax(P person);
}

class Person extends TaxPayer<Person> {
	public Person(long income, TaxStrategy<Person> strategy) {
		super(income, strategy);
	}
	
	protected Person getThis() {
		return this;
	}
}

class Trust extends TaxPayer<Trust> {
	private boolean nonProfit;
	
	public Trust(long income, boolean nonProfit, TaxStrategy<Trust> strategy) {
		super(income, strategy);
		this.nonProfit = nonProfit;
	}
	
	protected Trust getThis() {
		return this;
	}
	
	public boolean isNonProfit() {
		return nonProfit;
	}
}

class DefaultTaxStrategy<P extends TaxPayer<P>> implements TaxStrategy<P> {
	private static final double RATE = 0.4;
	
	public long computeTax(P payer) {
		return Math.round(payer.getIncome()*RATE);
	}
}

class DodgingTaxStrategy<P extends TaxPayer<P>> implements TaxStrategy<P> {
	public long computeTax(P payer) {
		return 0;
	}
}

class TrustTaxStrategy extends DefaultTaxStrategy<Trust> {
	public long computeTax(Trust trust) {
		return trust.isNonProfit() ? 0 : super.computeTax(trust);
	}
}