package com.stacksimplify.restservices.springbootbuildingblocks.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservices.springbootbuildingblocks.services.dtos.UserMsDto;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mappings({
	@Mapping(source = ("email"), target = ("emailaddress")),
	@Mapping(source = "role", target = "rolename")})
	UserMsDto userToUserDto(User user);
	
	List<UserMsDto> usersToUserDto(List<User> user);
	
}
