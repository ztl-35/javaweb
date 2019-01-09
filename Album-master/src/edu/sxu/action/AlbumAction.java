package edu.sxu.action;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import edu.sxu.model.Album;
import edu.sxu.model.User;
import edu.sxu.service.AlbumService;
import edu.sxu.service.UserService;

public class AlbumAction {
	private Album album;
	private File image;
	private String imageFileName; 
    private String imageContentType;
    private UserService userService;
    private AlbumService albumService;
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
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
	public UserService getUserService() {
		return userService;
	}
	@Resource(name="UserService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public AlbumService getAlbumService() {
		return albumService;
	}
	@Resource(name="albumService")
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}
    public String addAlbum() throws IOException {
    	String realPath = ServletActionContext.getServletContext().getRealPath("/images");
    	if (image!=null) {
			File myImageFile = new File(new File(realPath),imageFileName);
			if (!myImageFile.getParentFile().exists()) {
				myImageFile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(image, myImageFile);
		}
    	HttpServletRequest request = ServletActionContext.getRequest();
    	album.setCover(request.getContextPath()+"/images/"+imageFileName);
    	User user = (User) ServletActionContext.getContext().getSession().get("USER");
    	user = userService.getUser(user);
    	album.setUser(user);
    	albumService.addAlbum(album);
    	return null;
    }
	
}
