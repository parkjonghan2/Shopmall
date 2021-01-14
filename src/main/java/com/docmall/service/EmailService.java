package com.docmall.service;

import com.docmall.dto.EmailDTO;

public interface EmailService {
	
	public void sendMail(EmailDTO dto, String authcode);

}
