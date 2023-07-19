package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.MovieCommentCreateRequestDto;
import com.bilgeadam.dto.request.UserRegisterRequestDto;
import com.bilgeadam.dto.request.UserUpdateRequestDto;
import com.bilgeadam.dto.response.UserLoginResponseDto;
import com.bilgeadam.entity.Comment;
import com.bilgeadam.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toUserRegisterDto(final UserRegisterRequestDto dto);

    UserLoginResponseDto toUserLoginDto(final User user);

    //@MappedTarget --> sizin dto'nuz ile entity'niz arasında bir mappleme yaparak veri güvenliği sağlar
    //ve veri kaybını önler
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateRequestDto dto, @MappingTarget User user);

    Comment toMovieComment(final MovieCommentCreateRequestDto dto);
}
