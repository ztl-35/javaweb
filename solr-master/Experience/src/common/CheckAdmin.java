package common;

import javax.servlet.http.HttpSession;

public class CheckAdmin {
	public boolean checkAdmin(HttpSession session) {
		System.out.println("������Ա��"+session.getAttribute("ADMIN"));
		if (session.getAttribute("ADMIN")!=null) {
			return true;
		}else {
			return false;
		}
	}
}
