package com.vinastore.apis;

import com.vinastore.dto.ChangePasswordDTO;
import com.vinastore.service.ChangePasswordService;
import com.vinastore.service.EmailService;
import com.vinastore.utils.CookieUtils;
import com.vinastore.utils.PasswordUtils;
import com.vinastore.utils.SessionUtils;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.vinastore.entities.Accounts;
import com.vinastore.service.AccountsService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@CrossOrigin("*")
@RestController
@RequestMapping("public/api/v1/accounts")
public class AccountsApi {

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private ChangePasswordService changePasswordService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CookieUtils cookieUtils;

    @Autowired
    private SessionUtils sessionUtils;

    @GetMapping
    public ResponseEntity<?> getAllAccount(){
        ResponseEntity<?> result = accountsService.getAllAccount();
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable("id")Integer id){
        ResponseEntity<?> products = accountsService.getAccountById(id);
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       @RequestParam(value = "remember", required = false)Boolean remember){
        // mã hóa mật khẩu tại đây bằng SHA-256
        String hashpassword = PasswordUtils.hashPassword(password);
        Accounts accountsCheck = accountsService.login(email,hashpassword);
        if(accountsCheck != null){
            if(remember != null){
                cookieUtils.add("email",email,1);
                cookieUtils.add("password",password,1);
                cookieUtils.add("remember", Boolean.toString(remember),1);
                cookieUtils.add("checkID",accountsCheck.getId().toString(),1);
                sessionUtils.set("accountLogin", accountsCheck);
                ResponseBodyServer bodyServer =ResponseBodyServer.builder().statusCode(200).message("Login successfulluy")
                        .payload(accountsCheck).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                sessionUtils.set("accountsCheck", accountsCheck);
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Login successfulluy but not remember")
                        .payload(accountsCheck).build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }else {
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Account not found").build();
            return ResponseEntity.status(404).body(bodyServer);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> postRegister(@RequestParam("email") String email,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password){
        Accounts accountsCheck = accountsService.register(email, username, password);
        if(accountsCheck == null){
            return null;
        } else {
            // dùng bất đồng bộ để tăng hiệu năng của chức năng đăng ký
            ExecutorService mailExecutors = Executors.newSingleThreadExecutor();
            mailExecutors.execute(new Runnable() {
                @Override
                public void run()
                {
                    try {
                        String subject = "Đăng ký tài khoản thành công";
                        String content = "Chào " + username +
                                " Xin chân thành cảm ơn bạn đã đăng ký tài khoản tại cửa hàng của chúng tôi! Chúng tôi rất vui mừng được chào đón bạn vào cộng đồng của chúng tôi.\n"
                                +
                                "\n" +
                                "Tài khoản của bạn đã được tạo thành công, và bây giờ bạn có thể truy cập vào các dịch vụ và tiện ích đặc biệt dành riêng cho thành viên của chúng tôi. Chúng tôi hy vọng rằng bạn sẽ tận hưởng trải nghiệm mua sắm và các ưu đãi độc quyền mà chúng tôi mang lại.\n"
                                +
                                "\n" +
                                "Nếu bạn có bất kỳ câu hỏi, đề xuất hoặc cần hỗ trợ gì, hãy xin vui lòng liên hệ với chúng tôi. Đội ngũ chăm sóc khách hàng của chúng tôi luôn sẵn sàng giúp bạn.\n"
                                +
                                "\n" +
                                "Một lần nữa, xin chân thành cảm ơn bạn đã đăng ký tài khoản tại cửa hàng của chúng tôi. Rất mong được phục vụ bạn trong tương lai và hy vọng bạn có những trải nghiệm thú vị và đáng nhớ tại cửa hàng của chúng tôi.\n"
                                +
                                "\n" +
                                "Trân trọng,";
                        emailService.sendEmail(subject,email,content);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        ResponseBodyServer bodyServer =ResponseBodyServer.builder().statusCode(200).message("Registers successfully")
                .payload(accountsCheck).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable("id") Integer id,
                                           @RequestBody Accounts formAccount){
        ResponseEntity<?> products = accountsService.updateAccounts(formAccount,id);
        return ResponseEntity.status(200).body(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Integer id){
        ResponseEntity<?> accounts = accountsService.deleteAccountsById(id);
        return ResponseEntity.status(200).body(accounts);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        ResponseEntity<?> accounts = changePasswordService.isValidPasswordChange(changePasswordDTO);
        return ResponseEntity.status(200).body(accounts);
    }
}
