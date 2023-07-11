package com.projeto.integrador.event;

import com.projeto.integrador.Entity.User;
import com.projeto.integrador.verificationenums.PasswordResetToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class PasswordEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;
    private PasswordResetToken passwordResetToken;

    public PasswordEvent(User user, String applicationUrl, PasswordResetToken passwordResetToken) {
        super(passwordResetToken);
        this.user = user;
        this.applicationUrl = applicationUrl;
        this.passwordResetToken = passwordResetToken;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public PasswordResetToken getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}
}