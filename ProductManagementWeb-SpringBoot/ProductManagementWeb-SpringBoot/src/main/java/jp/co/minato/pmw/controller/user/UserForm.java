package jp.co.minato.pmw.controller.user;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String loginId;
	private String password;
	private String action;
}
