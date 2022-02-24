class IntTester {

	public static void main(String[] args) {
		CustomBigInt big1 = new CustomBigInt(args[0]);
		CustomBigInt big2 = new CustomBigInt(args[1]);
		System.out.println("Addition: " + big1.add(big2));
		System.out.println("Multiplication: " + big1.multiply(big2));
	}
}
