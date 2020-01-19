package com.tanaka.rest.webservices.restfulwebservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		for (int i=1; i<=10;i++) {
			String encodeString = encoder.encode("123");
			System.out.println(encodeString);

		}
	}

}
