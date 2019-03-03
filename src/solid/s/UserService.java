package solid.s;

public class UserService {
    private EmailInfo emailInfo;
    private EmailService emailService;
    public void registerUser(User user) {
        emailInfo = new EmailInfo("Welcome " + user.getFirstName(), "Lorem ipsum dolor sit amet, erat laoreet, duis ac aut vel leo bibendum inventore.", user.getEmail());
        
        emailService = new EmailService();
        emailService.sendEmail(emailInfo);
        
    }
}
