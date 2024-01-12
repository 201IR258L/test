package servlet;

public class AuthorityReviewServlet {
  public boolean review(String authorityNumber) {
	  if(!(authorityNumber.equals("1"))) {
		  return true;
		  
	  }else {return false;}
   
  }
}