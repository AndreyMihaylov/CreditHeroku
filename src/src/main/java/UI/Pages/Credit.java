package UI.Pages;

public interface Credit <T>{

     T fillOutApr(String apr);
     T fillOutCredit(String limit);
     CreditPage submit();
     HomePage goBack();
}
