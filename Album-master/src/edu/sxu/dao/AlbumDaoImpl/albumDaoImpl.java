package edu.sxu.dao.AlbumDaoImpl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import edu.sxu.dao.AlbumDao;
import edu.sxu.model.Album;
@Component(value="albumDao")
@Scope(value="singleton")
public class albumDaoImpl implements AlbumDao{
	HibernateTemplate hibernateTemplate = new HibernateTemplate();
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void save(Album album) {
		hibernateTemplate.merge(album);
	}
	
}
