package com.skynet.placarSo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.placarSo.model.bean.Temas;
import com.skynet.placarSo.model.repository.TemaRepository;

@Service
public class TemaService {
	
	@Autowired
	private TemaRepository temaRepo;
	
	public List<Temas> getAllTemas() {
		List<Temas> temas = temaRepo.findAll();
		return temas;
	}
	

}
