package com.skeleton.app.controllers;

import com.skeleton.app.dtos.AccountDTO;
import com.skeleton.app.exceptions.CustomException;
import com.skeleton.app.services.AccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  // GET
  @GetMapping()
  @ResponseBody
  public List<AccountDTO> getAccounts(@RequestHeader(required = false) HttpHeaders httpHeaders)
      throws CustomException {
    return this.accountService.getAccounts();
  }

  // GET
  @GetMapping("/{accountId}")
  @ResponseBody
  public AccountDTO getAccount(
      @RequestHeader(required = false) HttpHeaders httpHeaders,
      @PathVariable("accountId") String accountId)
      throws CustomException {
    return this.accountService.getAccount(accountId);
  }

  // POST
  @PostMapping("/sign-up")
  @ResponseBody
  public String signUp(
      @RequestHeader(required = false) HttpHeaders httpHeaders, @RequestBody AccountDTO account)
      throws CustomException {
    return this.accountService.create(account);
  }

  // POST
  @PostMapping("/sign-in")
  @ResponseBody
  public String signIn(
      @RequestHeader(required = false) HttpHeaders httpHeaders,
      @RequestParam("email") String email,
      @RequestParam("password") String password)
      throws CustomException {
    return this.accountService.signIn(email, password);
  }

  // DELETE
  @DeleteMapping("/{accountId}")
  @ResponseBody
  public String delete(
      @RequestHeader(required = false) HttpHeaders httpHeaders, @PathVariable String accountId)
      throws CustomException {
    return this.accountService.delete(accountId);
  }

  // PUT
  @PutMapping("/{accountId}")
  @ResponseBody
  public String updateUser(
      @RequestHeader(required = false) HttpHeaders httpHeaders,
      @PathVariable("accountId") String accountId,
      @RequestBody AccountDTO account)
      throws CustomException {
    return this.accountService.update(accountId, account);
  }
}
