package com.projeto.integrador.event;

import com.projeto.integrador.Entity.User;
import com.projeto.integrador.verificationenums.VerificationToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class TokenEvent extends ApplicationEvent {

    private User user;
    private VerificationToken verificationToken;
    private String applicationUrl;

    public TokenEvent(User user, VerificationToken verificationToken, String applicationUrl) {
        super(verificationToken);
        this.verificationToken = verificationToken;
        this.applicationUrl = applicationUrl;
        this.user = user;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VerificationToken getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(VerificationToken verificationToken) {
		this.verificationToken = verificationToken;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
}