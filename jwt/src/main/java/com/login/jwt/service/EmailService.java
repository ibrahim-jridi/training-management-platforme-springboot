package com.login.jwt.service;

import org.springframework.mail.SimpleMailMessage;


public interface EmailService {
	public void sendEmail(SimpleMailMessage email);
}
