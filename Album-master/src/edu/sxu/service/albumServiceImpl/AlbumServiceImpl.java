package edu.sxu.service.albumServiceImpl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.sxu.dao.AlbumDao;
import edu.sxu.model.Album;
import edu.sxu.service.AlbumService;


@Component(value="albumService")
@Scope(value="singleton")
public class AlbumServiceImpl implements AlbumService{
	AlbumDao albumDao;
	
	public AlbumDao getAlbumDao() {
		return albumDao;
	}
	@Resource(name="albumDao")
	public void setAlbumDao(AlbumDao albumDao) {
		this.albumDao = albumDao;
	}
	@Override
	public void addAlbum(Album album) {
		albumDao.save(album);
	}
}
