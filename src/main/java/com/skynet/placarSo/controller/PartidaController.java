package com.skynet.placarSo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skynet.placarSo.model.bean.Ganhador;
import com.skynet.placarSo.model.bean.Partidas;
import com.skynet.placarSo.model.bean.Temas;
import com.skynet.placarSo.model.service.PartidaService;
import com.skynet.placarSo.model.service.SessaoService;
import com.skynet.placarSo.model.service.TemaService;
import com.skynet.placarSo.utils.QRCodeGenerator;

@Controller
public class PartidaController {

	@Autowired
	private PartidaService partidaService;

	@Autowired
	private SessaoService sessaoServ;

	@Autowired
	private TemaService temaServ;

	@GetMapping("/partida/")
	public ModelAndView mostrarPartidas() {
		ModelAndView mv = new ModelAndView("partidas");
		List<Partidas> partidas = partidaService.getAllPartidas();
		List<Temas> temas = temaServ.getAllTemas();
		mv.addObject("temas", temas);
		mv.addObject("partida", partidas);
		mv.addObject(new Partidas());
		return mv;
	}

	@PostMapping("/partida/")
	public String criarPartida(Partidas partida) {
		try {
			System.out.println("Tema: " + partida.getTema().getNome());
			partidaService.cadastrarIniciarPartidas(partida);
			QRCodeGenerator.generateQRCodeImage(Long.toString(partida.getId()));
			return "redirect:/partida/iniciada/";

		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/partida/";
		}
	}

	@PostMapping("/partida/vencedores/")
	public ModelAndView vencedoresPartida(Partidas partida) {
		ModelAndView mv = new ModelAndView("vencedores");
		Partidas p = partidaService.encontrarPartida(partida.getId());
		List<Ganhador> ganhadores = sessaoServ.getWinners(p);
		mv.addObject("ganhadores", ganhadores);
		mv.addObject("partida", partida);
		return mv;
	}

	@PostMapping("/partida/finalizar/")
	public String finalizarPartida(Partidas partida) {
		try {
			partidaService.finalizarPartida(partida.getId());
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/partida/";
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
