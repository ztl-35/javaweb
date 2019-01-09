package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import dao.contactDAO;
import dao.studentDAO;
import model.Contact;
import model.Student;

public class contactAction {
	private Contact contact;
	private contactDAO cDao = new contactDAO();
	private studentDAO sDao = new studentDAO();
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public String update() {
		cDao.updateContact(contact);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("STUDENT");
		student.setContact(contact);
		sDao.updateStudent(student);
		return "Main";
	}
}
