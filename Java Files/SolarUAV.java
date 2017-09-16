import java.util.ArrayList;
import java.lang.Math;

public class SolarUAV {

	private double cdo;
	private double rho;
	private double e;
	private double aCell;
	private double mg;
	private double reynold;
	private double chord;
	private double kvs;
	private double span;
	private double speed;

	public SolarUAV() {
		e = 0.85;
		rho = 1.225;
		kvs = 0.0000157;
		aCell = 239;
	}

	public double calcSpan() {
		double chord = (reynold * kvs) / (speed);
		double spanCubed = 0.75 * Math.pow(speed, 4) * Math.PI * e * cdo * chord * Math.pow(rho / mg, 2);
		double spanAns = Math.pow(spanCubed, -0.3333);
		return spanAns * (39.6 / 12.0);
	}

	public double calcCruiseSpeed() {
		double speedCubed = 1.33 * (1.0 / (Math.PI * e * cdo * Math.pow(span, 3) * reynold * kvs)) * Math.pow(mg / rho, 2);
		double speedAns = Math.pow(speedCubed, 0.33333);
		return speedAns * 2.25;
	}

	public double calcWeight() {
		double weight = 0.75 * Math.pow(speed, 3) * Math.PI * e * cdo * Math.pow(span, 3) * reynold * kvs * Math.pow(rho, 2);
		weight = Math.pow(weight, 0.5);
		return weight * 0.224808942443; //convert to pounds for output
	}

	public double calcReynolds() {
		double chord = 1.33 * (1.0 / (Math.PI * e * cdo * Math.pow(span, 3))) * Math.pow(mg / rho, 2) * Math.pow(speed, -4);
		double reynoldsAns = (speed * chord) / kvs;
		return reynoldsAns;
	}

	public double calcDragCoeff() {
		double chord = (reynold * kvs) / speed;
		double cdoAns = 1.33 * (1.0 / (Math.PI * e * Math.pow(span, 3) * chord * Math.pow(speed, 4))) * Math.pow(mg / rho, 2);

		return cdoAns * 1000.0;
	}

	public double getCdo() {
		return cdo;
	}

	public double getRho() {
		return rho;
	}

	public double getOswald() {
		return e;
	}

	public double getAreaCell() {
		return aCell;
	}

	public double getWeight() {
		return mg;
	}

	public double getReynold() {
		return reynold;
	}

	public double getSpan() {
		return span;
	}

	public double getSpeed() {
		return speed;
	}

	public void setCdo(double val) {
		cdo = val * 0.001;
	}

	public void setRho(double val) {
		rho = val;
	}

	public void setOswald(double val) {
		e = val;
	}

	public void setAreaCell(double val) {
		aCell = val;
	}

	public void setWeight(double val) {
		mg = val * 0.453592 * 9.81;
	}

	public void setReynold(double val) {
		reynold = val;
	}

	public void setSpan(double val) {
		span = val * 0.303;
	}

	public void setSpeed(double val) {
		speed = val / 2.25;
	}
}