class CustomBigInt {

	private final static int MAX_DIGITS = 9;
	
	private byte [] value; //value holder
	private String representation;
	private int size;
	
	
	public CustomBigInt(String number) {
		this.value =  convertStringToByteArray(number);
		this.size = this.value.length;
		this.representation = convertByteArrayToString(this.value);
	}
	
	private byte[] convertStringToByteArray(String number) {
		byte[] result = new byte[number.length()];
		for (int index = 0; index < number.length(); index++) {
			result[index] = (byte)(number.charAt(index) - '0');
		}
		return result;
	}
	
	private String convertByteArrayToString(byte[] number) {
		StringBuilder result = new StringBuilder();
		for (int index = 0; index < number.length; index++) {
			result.append(number[index]);
		}
		return removeLeadingZeroesOf( result.toString() );
	}
	
	
	private String removeLeadingZeroesOf(String s) {
		if (s.charAt(0) != '0') {
			return s;
		} 
		return removeLeadingZeroesOf(s.substring(1));
	}
		
	@Override
	public String toString() {
		return this.representation;
	}
	
	
	
	public CustomBigInt add (CustomBigInt other) {
	
		byte[] resultArr = new byte[ Math.max(this.size , other.size) + 1 ];
		
		byte sum = 0, carry = 0;
		
		int this_i = this.size- 1;
		int other_i = other.size - 1;
		
		int res_place = resultArr.length - 1;
		
		while ( (this_i >=0 || other_i >= 0) || carry > 0 ) {
		
			if (this_i < other_i && this_i < 0) {
				sum = (byte) (other.value[other_i] + carry);	
			} else if (other_i < this_i && other_i < 0) {
				sum = (byte) (this.value[this_i] + carry);
			} else if (other_i < 0 && this_i < 0) {
				sum = (byte) (carry);
			} else {
				sum = (byte) (this.value[this_i] + other.value[other_i]  + carry);
			}
			
			resultArr[res_place] = (byte) (sum % 10);
			carry = (byte) (sum / 10);
			this_i--;
			other_i--;
			sum  = 0;
			res_place--;
		}
		
		return new CustomBigInt( convertByteArrayToString(resultArr) );
	}
	
	
	
	public CustomBigInt multiply (CustomBigInt other) {
		return multiplyDigit( other.value[other.value.length-1] );
	}
	
	
	private CustomBigInt multiplyDigit (byte digit) {
		CustomBigInt result = this;
		for (int i=1; i < digit; i++) {
			result = result.add(this);
		}
		return result;
	}
	
}
























