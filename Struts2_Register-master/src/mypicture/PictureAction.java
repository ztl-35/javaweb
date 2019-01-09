package mypicture;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class PictureAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Picture picture;
	ArrayList<Picture> pictures = new ArrayList<Picture>();
	
	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
	}
	String idType;
	
	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}
	//struts2上传文件的三个重要变量声明！！！
	File []image;
	String []imageFileName;
	String []imageContentType;
	
	pictureDAO dao = new pictureDAO();
	
	HttpServletResponse response = ServletActionContext.getResponse();
	
	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	
	public File []getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
	}

	public String[] getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String[] getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String add() throws IOException, SQLException {
		ServletContext application = ServletActionContext.getServletContext();
		String path = application.getRealPath("")+"/images";
		for (int i = 0; i < image.length; i++) {
			File myFile = new File(path, imageFileName[i]);
			if (!myFile.getParentFile().exists()) {
				myFile.getParentFile().mkdir();
			}
			FileUtils.copyFile(image[i], myFile);
			pictures.get(i).url = "images/"+imageFileName[i];
			dao.addPicture(pictures.get(i));
		}
		
		return "list";
	}
	public String getpic() throws SQLException, IOException {
		int id=0;
		if (idType.equals("user")) {
			id = picture.uid;
		}else {
			id = picture.id;
		}
		System.out.println("getpic:::id::"+id+" "+"idTyoe:::"+idType);
		ArrayList<Picture> list = dao.getPictures(id,idType);
		String contextPath = ServletActionContext.getRequest().getContextPath();
		String json = pictureService.getJson(list, contextPath);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
		return null;
	}
	public String getpicNum() throws SQLException, IOException {
		int num = dao.getpictureNumber(picture.uid);
		PrintWriter out = response.getWriter();
		out.print(num);
		return null;
	}
	public String delete() throws SQLException {
		String Url = dao.getURL(picture.id);
		ServletContext application = ServletActionContext.getServletContext();
		String absoluteURL = application.getRealPath("")+"/"+Url;
		File myFile = new File(absoluteURL);
		//删除文件夹下的照片系统不需要询问
		FileUtils.deleteQuietly(myFile);
		//删除数据库下对应的照片的记录
		dao.deletePicture(picture.id);
		return null;
	}
	
}
