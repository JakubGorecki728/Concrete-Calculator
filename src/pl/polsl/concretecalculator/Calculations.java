package pl.polsl.concretecalculator;

import java.text.DecimalFormat;

public class Calculations {
	
	
	
	


	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getA1() {
		return a1;
	}

	public void setA1(double a1) {
		this.a1 = a1;
	}

	public double getFck() {
		return fck;
	}

	public void setFck(double fck) {
		this.fck = fck;
	}

	public double getFyk() {
		return fyk;
	}

	public void setFyk(double fyk) {
		this.fyk = fyk;
	}

	public double getEs() {
		return Es;
	}

	public void setEs(double es) {
		Es = es;
	}

	public double getYc() {
		return yc;
	}

	public void setYc(double yc) {
		this.yc = yc;
	}

	public double getYs() {
		return ys;
	}

	public void setYs(double ys) {
		this.ys = ys;
	}

	public double getmEd() {
		return mEd;
	}

	public void setmEd(double mEd) {
		this.mEd = mEd;
	}

	public double getFiM() {
		return fiM;
	}

	public void setFiM(double fiM) {
		this.fiM = fiM;
	}

	public double getFiS() {
		return fiS;
	}

	public void setFiS(double fiS) {
		this.fiS = fiS;
	}

	public double getFcd() {
		return fcd;
	}

	public void setFcd(double fcd) {
		this.fcd = fcd;
	}

	public double getFyd() {
		return fyd;
	}

	public void setFyd(double fyd) {
		this.fyd = fyd;
	}

	public double getFctm() {
		return fctm;
	}

	public void setFctm(double fctm) {
		this.fctm = fctm;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getDg() {
		return dg;
	}

	public void setDg(double dg) {
		this.dg = dg;
	}

	public double getCnom() {
		return cnom;
	}

	public void setCnom(double cnom) {
		this.cnom = cnom;
	}

	public void setNrz(double nrz) {
		this.nrz = nrz;
	}


	

	
	private double b;
	private double h;
	private double mEd;
	private double cnom;
	private double fck;
	private double fctm;
	private double yc;
	private double dg;
	private double fyk;
	private double Es;
	private double ys;
	private double fiM;
	private double fiS;
	
	private double a1;
	private double d;
	private double fcd;
	private double fyd;
	private double nrz;



private double calculateSc () {
	double sc = this.mEd / (0.8*this.fcd*this.b*this.d*this.d);
	return sc;
}

// poprawi�, to jest bez sensu

private double calculateKsiLim() {
	double ksiLim = 0.0035 / (0.0035 + this.fyd / this.Es);
	return ksiLim;
}



private double calculateRequiredReinforcementArea() {
double ksi = 1.25 - Math.sqrt(1.563 - 2.5*calculateSc());
double x = ksi*this.d;
double as1 = (0.8*this.fcd*this.b*x)/this.fyd;
return as1;
}
	
private double calculateAreaOfSingleMainRebar () {
	double fimA = (Math.pow(0.5*this.fiM, 2)*Math.PI);
	return fimA;
}

public double calculateActualReinforcementAmount () {
	if (this.nrz == 0.0) {
	double n = calculateRequiredReinforcementArea()/calculateAreaOfSingleMainRebar();
	double nrz = Math.ceil(n);
	this.nrz = nrz; }
	else if (this.nrz < 2) {
		this.nrz = 2;
	}
	return this.nrz;	
}

private double calculateActualReinforcementArea () {
	double as1rz = calculateActualReinforcementAmount()*calculateAreaOfSingleMainRebar();
	double as1rzmax = Math.max(as1rz, Math.max(0.26*(this.fctm/this.fyk)*this.b*this.d, 0.0013*this.b*this.d));
	return as1rzmax;
}

public double calculateMinimumDistanceBetweenRebars () {
	double sl = Math.max(this.fiM, Math.max(this.dg+0.005, 0.02));
	return sl;
}

public double calculateMaximumAmountOfRebarsInRow () {
	double nrzMaxInRow = Math.ceil((this.b-2*this.cnom-this.fiM-2*this.fiS)/(this.fiM+calculateMinimumDistanceBetweenRebars()));
	return nrzMaxInRow;
}

public double calculateActualDistanceBetweenRebarsInRow () {
	double n = calculateMaximumAmountOfRebarsInRow();
	double slrz = ((this.b - 2*this.cnom-2*this.fiS)-(n*this.fiM))/(n-1);
	return slrz;
}


public double calculateAmountOfRows () {
	double nrows = Math.ceil(calculateActualReinforcementAmount()/calculateMaximumAmountOfRebarsInRow ());
	return nrows;
}

private double calculateRatioOfReinforcement() {
	double crossSectionArea = this.b*h;
	double ps1 = (calculateActualReinforcementArea () / crossSectionArea)*100;
	return ps1;
}

private double calculateActualReinforcementMassCenter() {
	double fimA = calculateAreaOfSingleMainRebar ();
	double nRows = calculateAmountOfRows ();
	double nrz = calculateActualReinforcementAmount ();
	double a1 = this.a1;
	double aBetweenRows = calculateMinimumDistanceBetweenRebars ();
	double nMaxInRow = calculateMaximumAmountOfRebarsInRow ();
	double a1rz;
	double nInLastRow = nrz-((nRows-1)*nMaxInRow);
	
	double a1rzNumerator= a1*(nMaxInRow*fimA);
	double a1rzDenominator = (nMaxInRow*fimA); 
			
	if (nRows>1) {
		for (int i=2; i<nRows; i++) {
			a1rzNumerator = a1rzNumerator + ((i-1)*aBetweenRows+a1)*(nMaxInRow*fimA);
			a1rzDenominator = a1rzDenominator + (nMaxInRow*fimA);
		}
		a1rzNumerator = a1rzNumerator + (nInLastRow*fimA)*(a1+aBetweenRows*(nRows-1));
		a1rzDenominator = a1rzDenominator + (nInLastRow*fimA);
		a1rz = a1rzNumerator/a1rzDenominator;
	} else {a1rz = a1; 
	}
	return a1rz;
}

private double calculateDrz () {
	double drz = this.h-calculateActualReinforcementMassCenter();
	return drz;
}

public double calculateSGN () {
	double xrz = (fyd*calculateActualReinforcementArea ()) / (0.8*fcd*b);
	double xlimrz = calculateKsiLim() * calculateDrz();
	double x = Math.min(xrz, xlimrz);
	double mRd = 0.8*fcd*b*x*(calculateDrz ()- 0.4*x);
	return mRd;
}

public double checkSGN() {
	while (calculateSGN ()<this.mEd) {
		this.nrz = this.nrz + 1;
	}
	double result;
	if (calculateActualReinforcementMassCenter()>h || Double.isNaN(calculateSGN()) || calculateRatioOfReinforcement()>4 ) {
		result = 0;
		nrz = 0;
	} else {
		result = calculateSGN();
	}
	return result;
}

public double getNrz() {
	checkSGN();
	return nrz;
}



public String calculationsResults () {
	String infoResult;
	double mRd = checkSGN();
	DecimalFormat df0 = new DecimalFormat("#");
	DecimalFormat df1 = new DecimalFormat("#.0");
	DecimalFormat df2 = new DecimalFormat("#.##");
	
	if (checkSGN()==0) {
		infoResult = "Przekroczono maksymalny stopień zbrojenia. Należy zwiększyć przekrój.";
	} else {
		String infoSGN = "MEd = "+df1.format(mEd)+" [kNm]  <  MRd = "+df1.format(mRd)+" [kNm]\nwarunek nośności został spełniony ("+df1.format((mEd/mRd)*100)+" %)";
		String infoReinforcementRatio = "stopień zbrojenia:   ps = "+df2.format(calculateRatioOfReinforcement())+" %";
		String infoReinforcementAmount = "ilość potrzebnych prętów:   As1rz = "+df0.format(nrz)+" \u03C6 "+df0.format(fiM*1000);
		
		infoResult = infoSGN+"\n"+infoReinforcementRatio+"\n"+infoReinforcementAmount;
	}
	
	return infoResult;
	
	
}










}
