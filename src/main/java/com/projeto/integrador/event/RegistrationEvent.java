package com.projeto.integrador.event;

import com.projeto.integrador.Entity.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;

    public RegistrationEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
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
}