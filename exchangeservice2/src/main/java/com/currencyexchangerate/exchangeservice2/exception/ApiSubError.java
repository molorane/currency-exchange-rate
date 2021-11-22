package com.currencyexchangerate.exchangeservice2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ApiSubError {
   private String object;
   private String field;
   private Object rejectedValue;
   private String message;

   public ApiSubError(String object, String message) {
       this.object = object;
       this.message = message;
   }
}