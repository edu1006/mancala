package br.com.petrim.lich.enums;

import java.util.Optional;
import java.util.stream.Stream;

import br.com.petrim.lich.util.ConstantsRoles;

public enum RoleAccessEnum {

	USER (FunctionalityEnum.USERS.getId(), ConstantsRoles.ROLE_USER),
	GROUP (FunctionalityEnum.GROUPS.getId(), ConstantsRoles.ROLE_GROUP);
	
	private String role;
	private Long idFunctionality;
	
	private RoleAccessEnum(Long idFunctionality, String role) {
		this.idFunctionality = idFunctionality;
		this.role = role;
	}
	
	public static Optional<RoleAccessEnum> valueOfFunctionaliry(Long idFunctionality) {
		return Stream.of(values())
			.filter(role -> role.getIdFunctionality().compareTo(idFunctionality) == 0)
			.findFirst();
	}
	
	public String getRole() {
		return role;
	}
	
	public Long getIdFunctionality() {
		return idFunctionality;
	}
}
