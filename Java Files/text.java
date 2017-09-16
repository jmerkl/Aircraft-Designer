public class text {
	public static void main(String[] args) {
		SolarUAV suav = new SolarUAV();
		suav.setReynold(200000);
		suav.setSpan(8);
		suav.setCdo(18);

		suav.setSpeed(10);
		System.out.println(suav.calcWeight());
	}
}