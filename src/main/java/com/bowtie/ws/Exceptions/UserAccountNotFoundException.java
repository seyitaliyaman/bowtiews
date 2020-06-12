package com.bowtie.ws.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "Kullanıcı hesabı bulunamadı.")
public class UserAccountNotFoundException extends RuntimeException {
}
