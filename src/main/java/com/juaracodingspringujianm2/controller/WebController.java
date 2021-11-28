package com.juaracodingspringujianm2.controller;

import java.util.List;

import com.juaracodingspringujianm2.model.Booking;
import com.juaracodingspringujianm2.model.Keberangkatan;
import com.juaracodingspringujianm2.model.KeberangkatanBus;
import com.juaracodingspringujianm2.model.Penumpang;
import com.juaracodingspringujianm2.repository.BookingRepository;
import com.juaracodingspringujianm2.repository.KeberangkatanRepository;
import com.juaracodingspringujianm2.repository.PenumpangRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class WebController {

	@Autowired
	PenumpangRepository penumpangRepo;

	@Autowired
	KeberangkatanRepository keberangkatanRepo;

	@Autowired
	BookingRepository bookingRepo;

    @GetMapping("/gagal")
	public String GetDora1() {
		return "kenihilan";
	}

	
    @GetMapping("/loginpenumpang")
	public String GetLoginPenumpang(Model model) {
		model.addAttribute("formCekData", new Penumpang());
		return "formlogin";
	}
	@PostMapping("/cekpenumpang")
	public String cekPenumpang(@ModelAttribute("formCekData") Penumpang formCekData, Model model){
		List<Penumpang> dataCekPenumpang = penumpangRepo.findByNik(formCekData.getNik()); 
		
		if (dataCekPenumpang.size() == 0) {
			return "kenihilan";
		} else {
			model.addAttribute("dataCekPenumpangList", dataCekPenumpang);
			return "detailpenumpang1";
		}
		
	}
   
    @GetMapping("/daftar")
	public String GetPenumpangBaru(Model model) {
		model.addAttribute("formData", new Penumpang());
		return "formpenumpangbaru";
	}

	@PostMapping("/createpenumpang")
	public String createPenumpang(@ModelAttribute("formData") Penumpang formData, Model model){
		penumpangRepo.save(formData);
		List<Penumpang> dataPenumpang = penumpangRepo.findByNik(formData.getNik()); // ini bisa pa
		model.addAttribute("dataPenumpangList", dataPenumpang);
		return "detailpenumpang";
	}




    @GetMapping("/carikeberangkatan")
	public String cariKeberangkatan(Model model) {
		model.addAttribute("formCekDataKeberangkatan", new Keberangkatan());
		return "carikeberangkatan";
	}

	
	@PostMapping("/cekkeberangkatan")
	public String cekDataKeberangkatan(@ModelAttribute("formCekDataKeberangkatan") Keberangkatan formCekDataKeberangkatan, Model model){
		List<KeberangkatanBus> dataCekKeberangkatan = keberangkatanRepo.findByTerminalAwalAndTanggal(formCekDataKeberangkatan.getId_jurusan().getTerminal_awal(), formCekDataKeberangkatan.getTanggal()); 
		
		
		if (dataCekKeberangkatan.size() == 0) {
			return "kenihilankeberangkatan";
		} else {
			model.addAttribute("dataCekKeberangkatanList", dataCekKeberangkatan);
			return "listdetailkeberangkatan";
		}
	}

	@GetMapping("/cancel")
	public String cancel(Model model) {
		
		model.addAttribute("formCancelBooking", new Booking());
		return "formcancel";
	}

	@PostMapping("/cancelbooking")
	public String cancelBooking(@ModelAttribute("formCancelBooking") Booking formCancelBooking, Model model){
		long angka = formCancelBooking.getId();
		bookingRepo.deleteByIdBooking(angka);
		//bookingRepo.deleteById(formCancelBooking.getId());
		
		return "cancelmessage";
		
	}


	@GetMapping("/listdetailkeberangkatan")
	public String GetDora5() {
		return "listdetailkeberangkatan";
	}
	@GetMapping("/gagalberangkat")
	public String GetDora6() {
		return "kenihilankeberangkatan";
	}
	@GetMapping("/booking")
	public String GetDora7() {
		return "formbooking";
	}
	@GetMapping("/berhasilbooking")
	public String GetDora8() {
		return "bookingdetail";
	}
	
	@GetMapping("/berhasilcancelbooking")
	public String GetDora10() {
		return "cancelmessage";
	}

}
