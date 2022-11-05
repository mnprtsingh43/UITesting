package UI_Testing;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class POMClass extends BaseClass {
	
	
	@FindBy(xpath="//a[contains(text(),'Sign up')]") WebElement signupbtn;
	@FindBy(xpath="//a[contains(text(),'Log In']") WebElement Loginbttn;
	@FindBy(xpath="//input[@id=\"email\"]") WebElement emailadrs;
	@FindBy(xpath="//input[@id=\"password\"]") WebElement password;
	@FindBy(xpath="//button[@type=\"submit\"]") WebElement submitbutton;
	@FindBy(xpath="//div[@id=\"email-error\"]") WebElement EmailError;
	@FindBy(xpath="//a[@class=\"text-blue-500 underline\"]") WebElement existindacnt;
	@FindBy(xpath="//button[@type=\"submit\"]") WebElement loginbutton;
	@FindBy(xpath="//input[@id=\"remember\"]") WebElement rememberme;
	@FindBy(xpath="//span[contains(text(),'Test Application')]") WebElement testapp;
	@FindBy(xpath="//div//a") WebElement Notes;
	@FindBy(xpath="//a[contains(text(),'Notes')]") WebElement notes;
	@FindBy(xpath="//button[@type=\"submit\"]") WebElement Logoutbtn;
	@FindBy(xpath="//a[@class=\"text-blue-500 underline\"]") WebElement crtnewnote;
	@FindBy(xpath="//a[@class=\"block p-4 text-xl text-blue-500\"]") WebElement Newnote;
	@FindBy(xpath="//p[@class=\"p-4\"]") WebElement nonotes;
	@FindBy(xpath="//input[@name=\"title\"]") WebElement title;
	@FindBy(xpath="//textarea[@name=\"body\"]") WebElement body;
	@FindBy(xpath="//div[@class=\"text-right\"]//button[@type=\"submit\"]") WebElement save;
	@FindBy(xpath="//div[@id=\"title-error\"]") WebElement notitle;
	@FindBy(xpath="//div[@id=\"title-error\"]") WebElement nobody;
	@FindBy(xpath="//button[@type=\"submit\" and contains(text(),'Delete')]") WebElement detelebutton;
	@FindBy(xpath="//a[@aria-current=\"page\"]") WebElement savednote;
	
	
	
	public POMClass() {

		PageFactory.initElements(driver, this);
		
	}
		
	public void signupbutton(String Email, String Password) {
		signupbtn.click();
		emailadrs.sendKeys(Email);
		password.sendKeys(Password);
		submitbutton.click();
		
		
	}
	
		
	public void login(String Email, String Password) throws InterruptedException {
		
		Loginbttn.click();;
		emailadrs.sendKeys(Email);
		password.sendKeys(Password);
		loginbutton.click();
		
		
	}
	
	public void existingAccount(String Email, String Password) {
		signupbtn.click();
		emailadrs.sendKeys(Email);
		password.sendKeys(Password);
		submitbutton.click();
		String abc =EmailError.getText();
		System.out.println(abc);
		
		
	}
	public void homepagemenu() {
		boolean c= testapp.isDisplayed();
		System.out.println(c);
		Notes.click();
		
	}
	
	public void noteswindow() {
	boolean a=	notes.isDisplayed();
	System.out.println(a);
	boolean b= 	nonotes.isDisplayed();
	System.out.println(a);
		
	}
	
	public void addingnote(String Title, String Body) {
		crtnewnote.click();
		title.sendKeys(Title);
		body.sendKeys(Body);
		save.click();
				
	}
	
	
	public void savenote() {
	boolean k=	savednote.isDisplayed();
	System.out.println(k);
		
	}
	
	public void deletenote() {
		detelebutton.click();
		
	}
	
	public void logout() {
		Logoutbtn.click();
		
	}
	
	public void remembermebutton(String Email, String Password) {
		Loginbttn.click();
		emailadrs.sendKeys(Email);
		password.sendKeys(Password);
		rememberme.click();
		loginbutton.click();
		Logoutbtn.click();
		
		
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
