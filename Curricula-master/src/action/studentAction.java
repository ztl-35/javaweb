package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import dao.studentDAO;
import model.Student;

public class studentAction {
	public Student student;
	public File image;
	public String imageFileName;
	public String imageContentType;
	studentDAO studentdao = new studentDAO();
	HttpServletResponse response = ServletActionContext.getResponse();
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String checkOldPwd() throws IOException {
		String result="0";
		if(studentdao.checkOldPassword(student)) {
			result="1";
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		return null;
	}
	public String addStudent() {
		studentdao.updateStudent(student);
		return "Main";
	}
	@SuppressWarnings("unused")
	public String loginStudent() {
		Student student1 = studentdao.check(student);
		if (student1!=null) {
			ActionContext.getContext().getSession().put("STUDENT", student1);
			//方法二 获取session
//			HttpSession session = ServletActionContext.getRequest().getSession();
//			session.setAttribute("STUDENT", student1);
			return "Main";
		}else {
			return "Login";
		}
	}
	public String updateStudent() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(image!=null) {
			//如果用户不修改之前在数据库中的照片，就不用重新设置新照片的路径。用前端提交上来的之前照片的路径即可
			String fileName = "uploadFile";
			fileName = request.getServletContext().getRealPath("")+fileName;
			File file = new File(fileName);
			if (!file.exists()) {
				file.mkdir();
			}
			fileName = fileName+"\\";
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			fileName = fileName+year;
		    file = new File(fileName);
			if (!file.exists()) {
				file.mkdir();
			}
			fileName = fileName+"\\";
			int month = calendar.get(Calendar.MONTH)+1;
			if (month<10) {
				fileName = fileName+"0"+month;
			}else {
				fileName = fileName+month;
			}
		    file = new File(fileName);
			if (!file.exists()) {
				file.mkdir();
			}
			fileName = fileName+"\\";
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			if (day<10) {
				fileName = fileName+"0"+day;
			}else {
				fileName = fileName+day;
			}
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (hour<10) {
				fileName = fileName+"0"+hour;
			}else {
				fileName = fileName+hour;
			}
			int minute = calendar.get(Calendar.MINUTE);
			if (minute<10) {
				fileName = fileName+"0"+minute;
			}else {
				fileName = fileName+minute;
			}
			int seconds = calendar.get(Calendar.SECOND);
			if (seconds<10) {
				fileName = fileName+"0"+seconds;
			}else {
				fileName = fileName+seconds;
			}
			int mills = calendar.get(Calendar.MILLISECOND);
			if (mills<10) {
				fileName = fileName+"00"+mills;
			}else if(mills<100) {
				fileName = fileName+"0"+mills;
			}else {
				fileName = fileName+mills;
			}
			fileName = fileName+imageFileName.substring(imageFileName.indexOf("."));
			file = new File(fileName);
			FileUtils.copyFile(image, file);
			
			//为了让服务器中保存的数据减少，将以前用户上传的照片删除
			String oldFilePath = request.getServletContext().getRealPath("")+student.getPhoto();
			File oldFile = new File(oldFilePath);
			if (oldFile.exists()) {
				oldFile.delete();
			}
			
			//重新设置用户上传的新照片的路径
			student.setPhoto(fileName.substring(fileName.indexOf("uploadFile")));
		}
		studentdao.updateStudent(student);
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("STUDENT", student);
		return "Main";
	}
}
