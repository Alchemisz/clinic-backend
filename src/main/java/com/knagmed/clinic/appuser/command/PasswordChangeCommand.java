package com.knagmed.clinic.appuser.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordChangeCommand {
  String oldPassword;
  String newPassword;
}
