package com.knagmed.clinic.appuser.command;

import lombok.Value;

@Value
public class PasswordChangeCommand {
  String oldPassword;
  String newPassword;
}
