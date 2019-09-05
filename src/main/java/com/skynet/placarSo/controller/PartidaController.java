package com.skynet.placarSo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skynet.placarSo.model.bean.Partidas;
import com.skynet.placarSo.model.service.PartidaService;

@Controller
public class PartidaController {

	@Autowired
	private PartidaService partidaService;

	@GetMapping("/partida/")
	public ModelAndView mostrarPartidas() {
		ModelAndView mv = new ModelAndView("partidas");
		List<Partidas> partida = partidaService.getAllPartidas();
		mv.addObject(new Partidas());
		mv.addObject("partida", partida);
		return mv;
	}

	@PostMapping("/partida/")
	public String criarPartida(Partidas partida) {
		try {
			partidaService.cadastrarIniciarPartidas(partida);
			return "redirect:/partida/iniciada/";

		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/partida/";
		}
	}

	@GetMapping("/partida/iniciada/")
	public ModelAndView partidaIniciada() {
		try {
			Partidas partida = partidaService.getPartidaIniciada();
			ModelAndView mv = new ModelAndView("partidas_iniciada");

			mv.addObject(new Partidas());
			mv.addObject("partida", partida);
			System.out.println(partida.getSessao());

			return mv;
		} catch (Exception e) {
			// Verificar qual a melhor forma para printar o StackTrace de erro
			System.out.println(e);
			return mostrarPartidas();
		}
	}

}