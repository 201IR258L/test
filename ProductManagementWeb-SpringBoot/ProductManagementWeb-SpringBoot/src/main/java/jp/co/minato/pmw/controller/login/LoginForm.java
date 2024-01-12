package jp.co.minato.pmw.controller.login;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String loginId;
	private String password;

}
