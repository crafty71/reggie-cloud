package com.reggie.user.userDto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserDto {

  @NonNull
  private String phone;

  @NonNull
  private String code;

}
